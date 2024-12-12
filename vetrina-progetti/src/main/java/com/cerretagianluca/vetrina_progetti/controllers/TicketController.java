package com.cerretagianluca.vetrina_progetti.controllers;


import com.cerretagianluca.vetrina_progetti.dtos.TicketDTO;
import com.cerretagianluca.vetrina_progetti.entites.User;
import com.cerretagianluca.vetrina_progetti.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public void createTicket(@AuthenticationPrincipal User currentAuthenticatedUser, @RequestBody TicketDTO ticketDTO) {
        ticketService.create(currentAuthenticatedUser, ticketDTO);
    }
}