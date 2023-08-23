package com.healthcaredental.reception.rendezvous;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface RendezvousRepository extends JpaRepository<Rendezvous, Long> {

    public List<Rendezvous> findByPatientId(String patientId);
    @Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :date")
    int getNbrRendezvousByDate(@Param("date") String date);

    @Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :#{#rendezvous.date}" +
            " AND HOUR(rdv.time) = HOUR(:#{#rendezvous.time})")
    int getNbrRendezvousByDateAndHour(@Param("rendezvous") Rendezvous rendezvous);

    /*@Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :date" +
            " AND HOUR(rdv.time) = HOUR(:time)")
    int getNbrRendezvousByDateAndHour(@Param("date") String date, @Param("time") String time);*/

    @Query("SELECT CASE WHEN COUNT(rdv) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Rendezvous rdv WHERE rdv.date = :#{#rendezvous.date} " +
            "AND rdv.patient = :#{#rendezvous.patient} " +
            "AND rdv.medecin = :#{#rendezvous.medecin}")
    boolean ifPatientAlreadyAdded(@Param("rendezvous") Rendezvous rendezvous);
}
