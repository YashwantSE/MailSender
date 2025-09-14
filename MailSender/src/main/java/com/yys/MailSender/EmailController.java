package com.yys.MailSender;

import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController

public class EmailController {
     public final JavaMailSender javaMailSender;

    public EmailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @RequestMapping("/send-mail")
     public String sendeEmail(){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("email@gmail.com");
            message.setTo("email8080@gmail.com");
            message.setSubject("Simple Text email Subject");
            message.setText("This is a sample email text body for my first eamil");
            javaMailSender.send(message);

            return "Success";

        }catch (MailException e){
            return e.getMessage();
        }
     }

    @RequestMapping("/send-mail-with-attachment")
    public String sendeEmailwithattachment(){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("email@gmail.com");
            helper.setTo("email@gmail.com");
            helper.setSubject("Java email with attachment ");
            helper.setText("Please find the attachment document below");
            helper.addAttachment("Dhoni", new File("C:\\Users\\yashw\\OneDrive\\Pictures\\OIP.jpg"));

            javaMailSender.send(message);

            return "Success";

          }catch (Exception e){
            return e.getMessage();

        }
    }

}
