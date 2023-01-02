package com.skbroadband.doms.global.component;

import com.skbroadband.doms.global.dto.MailDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author : 안진갑
 * @Project : SKB_WEB
 * @Package : com.skbroadband.doms.global.component
 * @File : MailComponent
 * @Program :
 * @Date : 2022-12-14
 * @Comment :
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MailComponent {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    public void sendMail(MailDto mailDto) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

            mimeMessageHelper.setSubject(mailDto.getTitle());
            //mimeMessageHelper.setFrom(mailDto.getAddress()); username으로 설정
            mimeMessageHelper.setTo(mailDto.getAddress());

            Context context = new Context();
            context.setVariable("id", "");

            String html = templateEngine.process("emailTemplate", context);
            mimeMessageHelper.setText(html, true);
        } catch (MessagingException e) {
            log.error("mail send fail");
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMailMessage);
    }

    public boolean isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException e) {
            return false;
        }

        return true;
    }
}
