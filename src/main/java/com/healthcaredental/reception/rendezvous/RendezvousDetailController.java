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

 /*   @GetMapping("/{id}")
    public Patient getPatient(@PathVariable("id") String id) {


    }*/

    @PostMapping
    public ResponseEntity<Boolean> addListConfirmedAppointments(@RequestBody List<Rendezvous> confirmedRendezvous) {

        return ResponseEntity.ok(this.rendezvousDetailService.addListConfirmedAppointments(confirmedRendezvous));
    }
}
