package org.taskifyapp.util.notification;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.taskifyapp.model.entity.User;


@Component
@RequiredArgsConstructor
public class EmailSending {

    private final JavaMailSender javaMailSender;

    @SneakyThrows
    public void sendRegistration(User user) {
        String content = "Hi, [[NAME]],<br>"
                + "Your code to verify your registration:<br>"
                + "[[CODE]]<br>"
                + "Thank you,<br>"
                + "Good Luck, taskify corporation.";
        String subject = "Verify your registration";

        content = content.replace("[[NAME]]", user.getUsername());
        content = content.replace("[[CODE]]", String.valueOf(user.getCodeForMailSending()));
        sendEmail(user.getEmail(), content, subject);
    }

    @SneakyThrows
    public void sendToUser(User user, String adminUsername, String organizationName) {
        String content = "Hi,user [[USER_KEY]],<br>"
                + "You have been registered by admin.<br>"
                + "Your admin is: [[ADMIN_KEY]].<br>"
                + "Your organization is: [[ORGANIZATION_KEY]]<br>"
                + "Please,ask the credentials from your admin.<br>"
                + "Thank you,<br>";
        String subject = "Welcome!";

        content = content.replace("[[USER_KEY]]", user.getUsername());
        content = content.replace("[[ADMIN_KEY]]", adminUsername);
        content = content.replace("[[ORGANIZATION_KEY]]", organizationName);
        sendEmail(user.getEmail(), content, subject);
    }

    @SneakyThrows
    private void sendEmail(String toAddress, String content, String subject) {
        String fromAddress = "talibnabiyev@gmail.com";
        String senderName = "taskify corporation";
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        javaMailSender.send(message);
    }
}
