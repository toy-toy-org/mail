package com.wooteco.mail.presentation;

import com.wooteco.mail.business.MailService;
import com.wooteco.mail.dto.MailSendRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    public void sendMail(@RequestBody MailSendRequest mailSendRequest) {
        mailService.send(mailSendRequest);
    }
}
