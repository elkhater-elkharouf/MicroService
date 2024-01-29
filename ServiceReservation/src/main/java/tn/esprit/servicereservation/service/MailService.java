package tn.esprit.servicereservation.service;

import tn.esprit.servicereservation.entity.MailDetail;

public interface MailService {
    String sendMail(MailDetail mailDetail);
    String sendMailWithAttachment(MailDetail mailDetail);
}
