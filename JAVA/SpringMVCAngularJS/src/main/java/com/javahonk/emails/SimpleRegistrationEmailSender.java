package com.javahonk.emails;

import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.javahonk.model.Constants;
import com.javahonk.model.StudentTest;


public class SimpleRegistrationEmailSender {
	private JavaMailSender javaMailSender;

    public void register(StudentTest user) {
        sendConfirmationEmail(user);
    }

    
    
    /**
     * Send confirmation email to registed user
     * @param user
     */
    private void sendConfirmationEmail(final StudentTest user) {
    	final String fromEmail = "binhtt@evolableasia.vn";
    	final String toEmail = user.getEmail();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				mimeMessageHelper.setTo(toEmail);
				mimeMessageHelper.setFrom(fromEmail); // could be parameterized...
				mimeMessageHelper.setSubject(Constants.JINZA_EMAIL_PREFIX + "Test send Registration email using SimpleRegistrationEmailSender class"); 
				
                String text = EmailTemplateReader.getMailBodyForRegistration(user);
                
                
                // Add attachment files
                String attachmentImageFile = "PieChart.jpeg";
                String attachmentFile = "attachmentFile.txt";
                String encodedAttachmentFile = "encoded_attachment_file.txt";
                String csvAttachmentFile = "csvAttachmentFile.csv";
                FileSystemResource textFile = new FileSystemResource(Constants.RESOURCE_PATH + attachmentFile);
                FileSystemResource encodedTextFile = new FileSystemResource(Constants.RESOURCE_PATH + encodedAttachmentFile);
                FileSystemResource csvFile = new FileSystemResource(Constants.RESOURCE_PATH + csvAttachmentFile);
                FileSystemResource imageFile = new FileSystemResource(Constants.RESOURCE_PATH + attachmentImageFile);
                mimeMessageHelper.addAttachment(textFile.getFilename(), textFile);
                mimeMessageHelper.addAttachment(encodedTextFile.getFilename(), encodedTextFile);
                mimeMessageHelper.addAttachment(csvFile.getFilename(), csvFile);
                mimeMessageHelper.addAttachment(imageFile.getFilename(), imageFile);

                
                System.out.println("Begin sending Registration Form email to '" + toEmail + "' ..." );
                System.out.println("Sent email content: " + text); 
                mimeMessageHelper.setText(text);                
                System.out.println("End sending Registration Form email to '" + toEmail + "' !!!" );
            }
        };
        
        this.javaMailSender.send(preparator);
    }

    
    
    /**
     * Send email to many people
     * @param users
     */
    private void sendConfirmationEmailToGroup(final List<StudentTest> users) {
    	final String fromEmail = "binhtt@evolableasia.vn";
    	final String recipients = getRecipientEmailList(users);
    	
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
            	String emailSubject = Constants.JINZA_EMAIL_PREFIX + "Test send email to multi-recipient";
            	
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                
				String[] recipientList = recipients.split(Constants.COMMA);
				InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				int counter = 0;
				for (String recipient : recipientList) {
				    recipientAddress[counter] = new InternetAddress(recipient.trim());
				    counter++;
				}
				mimeMessageHelper.setTo(recipientAddress); 
				
				
				mimeMessageHelper.setFrom(fromEmail); // could be parameterized...
				mimeMessageHelper.setSubject(emailSubject);
				
				// Add attachment files
                String attachmentImageFile = "PieChart.jpeg";
                String attachmentFile = "attachmentFile.txt";
                String encodedAttachmentFile = "encoded_attachment_file.txt";
                String csvAttachmentFile = "csvAttachmentFile.csv";
                FileSystemResource textFile = new FileSystemResource(Constants.RESOURCE_PATH + attachmentFile);
                FileSystemResource encodedTextFile = new FileSystemResource(Constants.RESOURCE_PATH + encodedAttachmentFile);
                FileSystemResource csvFile = new FileSystemResource(Constants.RESOURCE_PATH + csvAttachmentFile);
                FileSystemResource imageFile = new FileSystemResource(Constants.RESOURCE_PATH + attachmentImageFile);
                mimeMessageHelper.addAttachment(textFile.getFilename(), textFile);
                mimeMessageHelper.addAttachment(encodedTextFile.getFilename(), encodedTextFile);
                mimeMessageHelper.addAttachment(csvFile.getFilename(), csvFile);
                mimeMessageHelper.addAttachment(imageFile.getFilename(), imageFile);
				
                String text = EmailTemplateReader.getMailBodyForRegistration(users.get(0));
                
                System.out.println("Begin sending Registration Form email to '" + recipients + "' ..." );
                mimeMessageHelper.setText(text);
                System.out.println("End sending Registration Form email to '" + recipients + "' !!!" );
            }
        };
        
        this.javaMailSender.send(preparator);
    }
	
	
	
	
	/**
	 * @param list
	 * @return
	 */
	private String getRecipientEmailList(List<StudentTest> list) {
		String emailList = "";
		int count = 0;
		int emailSize = list.size();
		for (int index = 0; index < emailSize; index++) {
			emailList += list.get(index).getEmail();
			if (count + 1 < emailSize) {
				emailList += Constants.COMMA;
				count++;
			}
		}
		return emailList;
	}


	/**
	 * @param javaMailSender the javaMailSender to set
	 */
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	
	/**
	 * @param list
	 */
	public void register(List<StudentTest> list) {
		sendConfirmationEmailToGroup(list);
	}

}

