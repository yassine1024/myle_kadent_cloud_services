package com.healthcaredental.reception.rendezvous;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rendezvous-detail")
@RequiredArgsConstructor
public class RendezvousDetailController {

    private final RendezvousDetailService rendezvousDetailService;

    @GetMapping("")
    public ResponseEntity<List<RendezvousDetail>> getConfirmedPatientsList(@RequestParam String date) {

        return ResponseEntity.ok(this.rendezvousDetailService.getConfirmedPatientsList(date));
    }

    @PostMapping
    public ResponseEntity<Boolean> addListConfirmedAppointments(@RequestBody List<Rendezvous> confirmedRendezvous) {

        return ResponseEntity.ok(this.rendezvousDetailService.addListConfirmedAppointments(confirmedRendezvous));
    }
}
