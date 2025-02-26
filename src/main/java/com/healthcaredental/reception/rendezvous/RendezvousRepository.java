package com.healthcaredental.reception.rendezvous;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface RendezvousRepository extends JpaRepository<Rendezvous, Long> {

   public List<Rendezvous> findByPatientId(String patientId);
    /*  @Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :date")
     int getNbrRendezvousByDate(@Param("date") String date);*/
   @Query("SELECT COALESCE(count(*), 0) FROM Rendezvous rdv WHERE rdv.date = :date")
   int getNbrRendezvousByDate(@Param("date") LocalDate date);

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

    public List<Rendezvous> findByMedecinId(String medecinId);
    public List<Rendezvous> findByDateOrderByTimeAsc(LocalDate date);


    @Query("SELECT COUNT(r) FROM Rendezvous r WHERE r.patient.id IN (SELECT p.id FROM Patient p JOIN p.visits v WHERE v.cabinet.id = :cabinetId)")
    long countByCabinetId(@Param("cabinetId") String cabinetId);

    @Query("SELECT r FROM Rendezvous r WHERE r.patient.id IN (SELECT p.id FROM Patient p JOIN p.visits v WHERE v.cabinet.id = :cabinetId)" +
            " AND r.medecin IS NULL AND r.time >= :date ORDER BY r.date ASC, r.time ASC")
    List<Rendezvous> findByCabinetIdAndDateAfter(@Param("cabinetId") String cabinetId, @Param("date") String date);
}
