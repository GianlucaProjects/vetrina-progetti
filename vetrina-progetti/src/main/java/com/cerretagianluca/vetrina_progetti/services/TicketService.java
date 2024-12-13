package com.cerretagianluca.vetrina_progetti.services;

// `vetrina-progetti/src/main/java/com/cerretagianluca/vetrina_progetti/services/TicketService.java`

import com.cerretagianluca.vetrina_progetti.dtos.TicketDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    @Autowired
    private MailgunSender mailgunSender;

    public void create(User recipient, TicketDTO ticketDTO) {
        // Send email notification
        mailgunSender.sendTicketCreationEmail(recipient, ticketDTO);
    }
}
