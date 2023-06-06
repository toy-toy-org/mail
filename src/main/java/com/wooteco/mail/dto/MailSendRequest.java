package com.wooteco.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.mail.SimpleMailMessage;

@Getter
@AllArgsConstructor
public class MailSendRequest {

    private String toAddress;
    private String title;
    private String content;

    public SimpleMailMessage toMailMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);

        return simpleMailMessage;
    }
}
