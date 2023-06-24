package com.wooteco.mail.business;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class MobileService {

    @Value("${twilio.phone.number}")
    private String phoneNumber;

    @KafkaListener(
            topics="follow",
            groupId = "mobile",
            properties = {"enable.auto.commit:false", "auto.offset.reset:latest"}
    )
    public void listenFollow(ConsumerRecord<Long, String> record) {
        Message.creator(new PhoneNumber(record.value()),
                new PhoneNumber(phoneNumber),
                "new follower!").create();
    }
}
