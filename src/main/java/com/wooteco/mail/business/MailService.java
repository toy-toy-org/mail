package com.wooteco.mail.business;

import com.wooteco.mail.dto.MailSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void send(MailSendRequest mailSendRequest) {
        mailSender.send(mailSendRequest.toMailMessage());
    }
}
