package org.taskifyapp.notification;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.taskifyapp.model.entity.User;

import static org.taskifyapp.util.JavaMailSenderConstants.*;


@Component
@RequiredArgsConstructor
public class EmailSending {

    private final JavaMailSender javaMailSender;

    @SneakyThrows
    public void sendToUser(User user, String adminUsername, String organizationName) {
        String replacedContent = SEND_TO_USER_CONTENT.replace(USER_KEY, user.getUsername());
        replacedContent = replacedContent.replace(ADMIN_KEY, adminUsername);
        replacedContent = replacedContent.replace(ORGANIZATION_KEY, organizationName);
        sendEmail(user.getEmail(), replacedContent, SEND_TO_USER_SUBJECT);
    }

    @SneakyThrows
    private void sendEmail(String toAddress, String content, String subject) {
        MimeMessage message = message();
        mimeMessageHelper(message, toAddress, content, subject);
        javaMailSender.send(message);
    }

    private MimeMessage message() {
        return javaMailSender.createMimeMessage();
    }

    @SneakyThrows
    private void mimeMessageHelper(MimeMessage message, String toAddress, String content, String subject) {
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(SENDER_ADDRESS, SENDER_NAME);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
    }
}
