package com.justBrokkoly.flightreservation.Utils;

import com.justBrokkoly.flightreservation.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender sender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);

    @Value("${com.justBrokkoly.flightreservation.itinerary.email.subject}")
    private String EMAIL_SUBJECT;

    @Value("${com.justBrokkoly.flightreservation.itinerary.email.body}")
    private String EMAIL_BODY;

    public void sendItinerary(String toAddress,String filePath){
        LOGGER.info("inside sendItinerary()");

       MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(EMAIL_SUBJECT);
            messageHelper.setText(EMAIL_BODY);
            messageHelper.addAttachment("Itinerary",new File(filePath));
            sender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("Exception inside endItinerary()"+e);
        }
    }
}
