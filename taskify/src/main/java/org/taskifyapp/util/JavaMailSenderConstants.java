package org.taskifyapp.util;

public class JavaMailSenderConstants {
    private JavaMailSenderConstants() {

    }

    public static final String SEND_REGISTRATION_CONTENT = "Hi, [[NAME]],<br>"
            + "Your code to verify your registration:<br>"
            + "[[CODE]]<br>"
            + "Thank you,<br>"
            + "Good Luck, taskify corporation.";
    public static final String SEND_TO_USER_CONTENT = "Hi,user [[USER_KEY]],<br>"
            + "You have been registered by admin.<br>"
            + "Your admin is: [[ADMIN_KEY]].<br>"
            + "Your organization is: [[ORGANIZATION_KEY]]<br>"
            + "Please,ask the credentials from your admin.<br>"
            + "Thank you,<br>";
    public static final String SEND_REGISTRATION_SUBJECT = "Verify your registration";
    public static final String SEND_TO_USER_SUBJECT = "Welcome!";
    public static final String NAME = "[[NAME]]";
    public static final String CODE = "[[CODE]]";
    public static final String USER_KEY = "[[USER_KEY]]";
    public static final String ADMIN_KEY = "[[ADMIN_KEY]]";
    public static final String ORGANIZATION_KEY = "[[ORGANIZATION_KEY]]";
    public static final Integer SENDING_CODE_LENGTH = 7;
    public static final String SENDER_ADDRESS = "";
    public static final String SENDER_NAME = "";

}
