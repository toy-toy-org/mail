package com.wooteco.mail.business;

import com.wooteco.mail.dto.MailSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;

    public void send(MailSendRequest mailSendRequest) {
        mailSender.send(mailSendRequest.toMailMessage());
    }

    @KafkaListener(
            id="mail-send",
            topics="sign-up",
            clientIdPrefix = "clientId",
            properties = {"enable.auto.commit:false", "auto.offset.reset:latest"}
    )
    public void listen(String email) {
        MailSendRequest request = new MailSendRequest(email, "HI", "BYE!");
        mailSender.send(request.toMailMessage());
    }
}
