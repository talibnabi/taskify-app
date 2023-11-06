package org.taskifyapp.model.dto.request;


import lombok.Data;

@Data
public class NotificationRequest {

    private String username;

    private String notificationCode;
}
