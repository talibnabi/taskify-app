package org.taskifyapp.util;

public class JavaMailSenderConstants {

    private JavaMailSenderConstants() {

    }

    public static final String SEND_TO_USER_CONTENT = "Hi,user [[USER_KEY]],<br>"
            + "You have been registered by admin.<br>"
            + "Your admin is: [[ADMIN_KEY]].<br>"
            + "Your organization is: [[ORGANIZATION_KEY]]<br>"
            + "Please,ask the credentials from your admin.<br>"
            + "Thank you,<br>";

    public static final String SEND_TO_USER_SUBJECT = "Welcome!";

    public static final String USER_KEY = "[[USER_KEY]]";

    public static final String ADMIN_KEY = "[[ADMIN_KEY]]";

    public static final String ORGANIZATION_KEY = "[[ORGANIZATION_KEY]]";

    /*Write your email here
    For example,i write my personal email here:
    * */
    public static final String SENDER_ADDRESS = "talibnabiyev@gmail.com";

    public static final String SENDER_NAME = "Taskify Application";

}
