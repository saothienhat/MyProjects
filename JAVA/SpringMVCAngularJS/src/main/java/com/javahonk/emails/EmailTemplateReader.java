package com.javahonk.emails;

import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.javahonk.model.StudentTest;

import freemarker.template.Template;

/**
 * @author binhtt
 * ---Template Invoker - which fills the data in Email template
 */
public class EmailTemplateReader {
	public static final String REGISTRATION_EMAIL_TEMPLATE = "registration_email_template.ftl";

	/**
	 * @param student
	 * @return
	 * @throws Exception
	 */
	public static String getMailBodyForRegistration(StudentTest student) throws Exception {
		Template template = JinzaFreeMarkerConfig.getInstance().getTemplateByName(REGISTRATION_EMAIL_TEMPLATE);

		/* Create a data model */
		Map root = new HashMap();
		root.put("student", student);

		/* Merge data model with template */
		return getProcessedTemplate(root, template);
	}

	
	
	/**
	 * @param root
	 * @param template
	 * @return
	 */
	public static String getProcessedTemplate(Map root, Template template) {
		try {
			StringWriter textWriter = new StringWriter();
			template.process(root, textWriter);
			
			ByteBuffer encode = Charset.forName("UTF-8").encode(textWriter.toString());
			
			return textWriter.toString();
		} catch (Exception e) {
			throw new RuntimeException("Exception in processing template:" + e.getMessage());
		}

	}

}
