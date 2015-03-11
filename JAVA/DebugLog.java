/*
 * Copyright (c) Andrew LLC.
 * All Rights Reserved.
 *
 * This software is the confidential and proprietary information of
 * Andrew LLC. You shall not disclose such confidential
 * information and shall use it only in accordance with the terms
 * of the license agreement you entered into with Andrew LLC.
 */

package com.nortelnetworks.wls.common.base.debug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import java.text.DateFormat;

import java.util.Date;

import com.nortelnetworks.wls.common.base.util.WLSSimpleDateFormat;

/**
 * The <code>DebugLog</code> class provides the assertion based Debug Message facility.
 * The class is designed to be used only in debug mode, and will normally be initialized
 * by the ApplicationLoader. If the class is not initialized, or is used in standard mode,
 * the class will silently discard all debug output.
 * <p>
 * The class methods used to print debug messages always return a value of <code>true</code>
 * so that they can be used in assert statements.
 * <p>
 * The use of assert statements to print debug messages allows for the production of the messages
 * to be removed at runtime.
 * <p>
 * The debug messages are written to a file with a standard header for each message.
 * The header has the following format:<br>
 * <code>verbosity-tag:timestamp:thread-name</code><br>
 * <p>
 * The verbosity-tag is selected by using a particular method to print the debug message.<br>
 * The timestamp is recorded in milliseconds.
 * <p>
 * Optionally the header can be extended to include the names of the calling class and method:<br>
 * <code>verbosity-tag:timestamp:thread-name:class-name:method-name</code><br>
 * <p>
 * <b>NOTE</b><br>
 * The single instance of the class could be replaced by making all methods static, but it is
 * more flexible to have an instance.
 */
public class DebugLog
{
    /** The verbosity tags for the message header. */
    private static final char VERBOSITY_TAG_BRIEF = 'B';
    private static final char VERBOSITY_TAG_MODERATE = 'M';
    private static final char VERBOSITY_TAG_VERBOSE = 'V';
    private static final char VERBOSITY_TAG_TRACE = 'T';

    /** The common prefix for MLC packages. */
    private static final String MLC_PACKAGE_PREFIX = "com.nortelnetworks.wls.";

    /** The suffix for the output file. */
    private static final String OUTPUT_FILE_SUFFIX = ".dbg";

    /** The single instance of the class. */
    private static DebugLog theInstance = new DebugLog();

    /** The file being written to. */
    private static PrintWriter output;

    /** The flag indicating if the class and method names should be included. */
    private static boolean includeNames;

    /** The Throwable used to determine the callers class and method names. */
    private static Throwable throwable = new Throwable();

    /**
     * Default Constructor, made private for the singleton pattern.
     */
    private DebugLog()
    {
        // NOP
    }

    /**
     * Initializes the debug output file for the class.
     * <p>
     * This method should only be used by the Application Loader.
     *
     * @param filename the output filename
     * @return true if the file is initialized, false otherwise
     */
    public static boolean initOutputFile(String filename)
    {
        if (DebugLog.output != null)
        {
            DebugLog.closeOutputFile();
        }

        if (GlobalDebugSettings.getInstance() == null)
        {
            return DebugLog.theInstance.openLogFile(filename);
        }

        return DebugLog.theInstance.openLogFile(filename +
                GlobalDebugSettings.getInstance().getApplicationInstanceNumber());
    }

    /**
     * Gets the current setting for the flag controlling the
     * automatic inclusion of the class and method names in the output.
     *
     * @return true if the names are included, false otherwise
     */
    public static boolean isIncludeClassMethodNames()
    {
        return DebugLog.includeNames;
    }

    /**
     * Sets the flag controlling the automatic inclusion of the class and method names in the output.
     *
     * @param _includeNames true to include the names in the output, false otherwise
     */
    public static void includeClassMethodNames(boolean _includeNames)
    {
        DebugLog.includeNames = _includeNames;
    }

    /**
     * Outputs a debug message with a verbosity tag of "brief".
     * <p>
     * The method always returns true so it can be used in an expression in an assert statement.
     *
     * @param message the text of the message to output
     *
     * @return true
     */
    public static boolean brief(String message)
    {
        return DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_BRIEF, message);
    }

    /**
     * Outputs a debug message with a verbosity tag of "moderate".
     * <p>
     * The method always returns true so it can be used in an expression in an assert statement.
     *
     * @param message the text of the message to output
     *
     * @return true
     */
    public static boolean moderate(String message)
    {
        return DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_MODERATE, message);
    }

    /**
     * Outputs a debug message with a verbosity tag of "verbose".
     * <p>
     * The method always returns true so it can be used in an expression in an assert statement.
     *
     * @param message the text of the message to output
     *
     * @return true
     */
    public static boolean verbose(String message)
    {
        return DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_VERBOSE, message);
    }

    /**
     * Outputs a trace message with a verbosity tag of "trace".
     * <p>
     * The method always returns true so it can be used in an expression in an assert statement.
     *
     * @param message the text of the message to output
     *
     * @return true
     */
    public static boolean trace(String message)
    {
        return DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_TRACE, message);
    }

    /**
     * Outputs a debug message and a stack trace for an exception.
     * The message will be output with a verbosity tag of "brief".
     * The information is also output to standard out so that unexpected
     * exceptions can be more easily detected while running in non-debug mode.
     *
     * @param message the text of the message to output
     * @param exception the exception instance
     * @return Always true so that it can be used in an assert statement.
     */
    public static boolean logException(String message, Throwable exception)
    {
        System.out.println("\n" + new WLSSimpleDateFormat(DateFormat.FULL).format(new Date()));
        System.out.println(message);

        String stackString = StackTraceConverter.stackTraceToString(exception);

        System.out.println(stackString);

        if (DebugLog.output != null)
        {
            DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_BRIEF, message);
            DebugLog.theInstance.outputMessage(DebugLog.VERBOSITY_TAG_BRIEF, stackString);
        }

        return true;
    }

    /**
     * Outputs a byte array as hex characters.
     *
     * @param buffer byte array
     * @param start offset of first byte to output
     * @param length number of bytes to output
     */
    public static void hexDump(byte[] buffer, int start, int length)
    {
        if (DebugLog.output != null)
        {
            if (length > 0)
            {
                synchronized (DebugLog.output)
                {
                    HexDump.dumpHexData(DebugLog.output, buffer, start, length);
                    DebugLog.output.println();
                    DebugLog.output.flush();
                }
            }
            else
            {
                synchronized (DebugLog.output)
                {
                    DebugLog.output.println("<0 length>");
                    DebugLog.output.flush();
                }
            }
        }
    }

    /**
     * Terminates the application after printing a message indicating a fatal condition.
     * <p>
     * The empty string is returned to allow the method to be used
     * as the 2nd expression in an assert statement.
     *
     * @param condition the text describing the cause of death
     * @param message the text of the message to output
     * @return empty string
     */
    public static String die(String condition, String message)
    {
        DebugLog.theInstance.terminateApplication(condition, message, null);
        return "";
    }

    /**
     * Closes the debug output file.
     * <p>
     * The method should only be used by classes in the package.
     *
     * @return true for use with asserts
     */
    public static boolean closeOutputFile()
    {
        if (DebugLog.output != null)
        {
            synchronized (DebugLog.output)
            {
                DebugLog.output.close();
                DebugLog.output = null;
            }
        }

        return true;
    }

    /**
     * Outputs a message using a specified verbosity tag.
     * <p>
     * The method always returns true to allow the static calling methods
     * to easily return true so they can be used in assert statements.
     * <p>
     * The method performs operations inline to give the best performance
     * by avoiding method calls wherever possible.
     *
     * @param verbosityTag the char representing the verbosity tag
     * @param message the text of the message to output
     *
     * @return true
     */
    private boolean outputMessage(char verbosityTag, String message)
    {
        if (DebugLog.output == null)
        {
            return true;
        }

        String threadName = Thread.currentThread().getName();

        if (threadName.startsWith(DebugLog.MLC_PACKAGE_PREFIX))
        {
            int pos = threadName.indexOf('@');

            if (pos != -1)
            {
                pos = threadName.substring(0, pos).lastIndexOf('.') + 1;
            }
            else
            {
                pos = DebugLog.MLC_PACKAGE_PREFIX.length();
            }

            threadName = threadName.substring(pos);
        }

        String className = null;
        StackTraceElement callerStackElement = null;

        if (DebugLog.includeNames)
        {
            DebugLog.throwable.fillInStackTrace();
            callerStackElement = DebugLog.throwable.getStackTrace()[2];
            className = callerStackElement.getClassName();

            if (className.startsWith(DebugLog.MLC_PACKAGE_PREFIX))
            {
                className = className.substring(DebugLog.MLC_PACKAGE_PREFIX.length());
            }
        }

        synchronized (DebugLog.output)
        {
            DebugLog.output.print(verbosityTag);
            DebugLog.output.print(':');
            DebugLog.output.print(System.currentTimeMillis());
            DebugLog.output.print(':');
            DebugLog.output.print(threadName);
            DebugLog.output.print(':');

            if (className != null)
            {
                DebugLog.output.print(className);
                DebugLog.output.print(':');
                DebugLog.output.print(callerStackElement.getMethodName());
                DebugLog.output.print(':');
                DebugLog.output.print(callerStackElement.getLineNumber());
                DebugLog.output.print(':');
            }

            DebugLog.output.println(message);
            DebugLog.output.flush();
        }

        return true;
    }

    /**
     * Terminates the application after printing a message indicating a fatal condition.
     *
     * @param errorMessage text of the message indicating the fatal condition
     */
    public static void fatalError(String errorMessage)
    {
        DebugLog.fatalError(errorMessage, null);
    }

    /**
     * Terminates the application after printing a message indicating a fatal condition.
     *
     * @param errorMessage text of the message indicating the fatal condition
     * @param source the source exception that caused the fatal condition
     */
    public static void fatalError(String errorMessage, Exception source)
    {
        DebugLog.theInstance.terminateApplication("Fatal Error", errorMessage, source);
    }

    /**
     * Abruptly terminates the application after printing out details of the reason
     * for the abrupt termination.
     */
    private void terminateApplication(String condition, String message, Exception source)
    {
        outputTerminationMessage(condition, message);

        if (source != null)
        {
            DebugLog.theInstance.outputStackTrace(source, 0);
        }
        else
        {
            Exception theException = new Exception("Application terminated from:");

            DebugLog.theInstance.outputStackTrace(theException, 1);
        }

        // Any connections being used by this application use internal threads
        // for writing to the connection. So allow some time for pending writes to complete.
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            // This is not expected, but if it happens we will just exit a little sooner.
        }

        MasterThreadGroup.terminateApplication(2);
    }

    /**
     * Writes the error message for the fatal condition encountered.
     * <p>
     * The message is written to both the debug log and stderr.
     *
     * @param errorMessage text of the message indicating the fatal condition
     */
    private void outputTerminationMessage(String condition, String errorMessage)
    {
        SysLog syslog = new SysLog();

        syslog.log(errorMessage);
        System.err.println(errorMessage);

        if (DebugLog.output != null)
        {
            synchronized (DebugLog.output)
            {
                DebugLog.output.println("**********************************************");
                DebugLog.output.println(condition);
                DebugLog.output.println("**********************************************");
                DebugLog.output.println(errorMessage);
                DebugLog.output.println("**********************************************");
            }
        }
    }

    /**
     * Outputs a stack trace, starting with a specified entry.
     *
     * @param exception the exception with the stack trace to be printed
     * @param firstEntry the first entry in the stack trace to be printed
     */
    private void outputStackTrace(Throwable exception, int firstEntry)
    {
        if (DebugLog.output != null)
        {
            StackTraceElement[] stackElements = exception.getStackTrace();
            int countElements = stackElements.length;

            synchronized (DebugLog.output)
            {
                DebugLog.output.println(exception.getMessage());

                for (int i = firstEntry; i < countElements; ++i)
                {
                    String anEntry = stackElements[i].toString();

                    DebugLog.output.println(anEntry);
                }

                DebugLog.output.flush();
            }
        }
    }

    /**
     * Opens the log file to receive the debug messages.
     * If a file with the specified name exists, then the new messages are appended to the file.
     *
     * @param filename the name of the file to be opened
     */
    private boolean openLogFile(String filename)
    {
        boolean fileOpened = false;

        if (!filename.endsWith(DebugLog.OUTPUT_FILE_SUFFIX))
        {
            filename = filename + DebugLog.OUTPUT_FILE_SUFFIX;
        }

        File file = new File(System.getProperty("common.base.DebugLog.outputDir", "../dbg"));

        if (!file.exists())
        {
            if (!file.mkdirs())
            {
                System.err.println("Failed to create output dir '" + file.getAbsolutePath() + "'");
            }
        }

        file = new File(file, filename);

        try
        {
            DebugLog.output = new PrintWriter(new FileOutputStream(file, true));
            fileOpened = true;
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }

        return fileOpened;
    }

    /**
     * Returns a stack trace for the calling method.
     *
     * @return the string containing the trace details
     */
    public static String getCurrentStackTrace()
    {
        DebugLog.throwable.fillInStackTrace();

        StackTraceElement[] elements = DebugLog.throwable.getStackTrace();
        StringBuffer traceDetails = new StringBuffer();
        int elementCount = elements.length;

        traceDetails.append("current stack trace:\n");

        for (int i = 1; i < elementCount; ++i)
        {
            traceDetails.append("  ");
            traceDetails.append(elements[i].toString());
            traceDetails.append("\n");
        }

        return traceDetails.toString();
    }
}
