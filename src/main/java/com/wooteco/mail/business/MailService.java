package com.wooteco.mail.business;

import com.wooteco.mail.dto.MailSendRequest;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
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
            topics="sign-up",
            groupId = "mail",
            properties = {"enable.auto.commit:false", "auto.offset.reset:latest"}
    )
    public void listenSignUp(ConsumerRecord<Long, String> record) {
        MailSendRequest request = new MailSendRequest(record.value(), "HI", "BYE!");
        mailSender.send(request.toMailMessage());
    }
}
