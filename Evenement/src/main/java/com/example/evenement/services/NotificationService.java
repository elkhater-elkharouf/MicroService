package com.example.evenement.services;

import com.example.evenement.configuration.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    TwilioConfig twilioConfig;

    public void sendNotification(String phoneNumber, String message) {
        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(twilioConfig.getTrialNumber()),
                message
        ).create();
    }
}
