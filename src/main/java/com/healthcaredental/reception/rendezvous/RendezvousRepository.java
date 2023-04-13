package com.healthcaredental.reception.rendezvous;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RendezvousRepository extends JpaRepository<Rendezvous,String> {

    public List<Rendezvous> findByPatientId(String patientId);
    @Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :date")
    int getNbrRendezvousByDate(@Param("date") String date);
}
