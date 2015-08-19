package com.javahonk.emails;

import java.util.List;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.javahonk.model.Constants;
import com.javahonk.model.StudentTest;

public class SimpleOrderEmailSender {
	private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    
    
    /**
     * @param student
     */
	public void sendSimpleEmail(StudentTest student) {
		String toEmail = "binhtt@evolableasia.vn";
		try {
			String emailContent = EmailTemplateReader.getMailBodyForRegistration(student);
			String emailSubject = Constants.JINZA_EMAIL_PREFIX + "Test send Registration email using SimpleOrderEmailSender class";
			
			/*
			 *  Create a thread safe "copy" of the template message and customize it
			 */
			SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);
			message.setTo(toEmail);
			message.setText(emailContent);
			message.setSubject(emailSubject);
            
			System.out.println("Begin using SimpleRegistrationEmailSender class to '" + toEmail + "' ...");
			this.mailSender.send(message);
			System.out.println("Sent using SimpleRegistrationEmailSender class to '" + toEmail + "' !!");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception e) {

		}
	}

	
	
	/**
	 * Send email to many reciptients
	 * @param list
	 */
	public void sendSimpleEmailToRecipients(List<StudentTest> list) {
		try {
			String emailContent = EmailTemplateReader.getMailBodyForRegistration(list.get(0)); 
			String emailSubject = Constants.JINZA_EMAIL_PREFIX + "Test send Registration email using SimpleOrderEmailSender class";
			String toEmails[] = new String[list.size()];
			for (int index = 0; index < list.size(); index++) {
				toEmails[index] = list.get(index).getEmail();
			}
			
			/*
			 *  Create a thread safe "copy" of the template message and customize it
			 */
			SimpleMailMessage message = new SimpleMailMessage(this.templateMessage);
			message.setTo(toEmails);
			message.setText(emailContent);
			message.setSubject(emailSubject);
            
			System.out.println("Begin using SimpleRegistrationEmailSender class to '" + list.get(0).getEmail() + "' ...");
			this.mailSender.send(message);
			System.out.println("Sent using SimpleRegistrationEmailSender class to '" + list.get(0).getEmail() + "' !!");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		} catch (Exception e) {

		}
	
	}

}
