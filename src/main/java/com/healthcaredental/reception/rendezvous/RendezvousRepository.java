package com.healthcaredental.reception.rendezvous;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RendezvousRepository extends JpaRepository<Rendezvous,String> {

    public List<Rendezvous> findByPatientId(String patientId);
}
