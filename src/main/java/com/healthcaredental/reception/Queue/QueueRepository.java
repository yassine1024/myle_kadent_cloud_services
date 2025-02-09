package com.healthcaredental.reception.Queue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface QueueRepository extends JpaRepository<Queue, Long> {

    @Query("SELECT COUNT(q) > 0 " +
            "FROM Queue q " +
            "JOIN  q.rendezvous rdv " +
            "JOIN  rdv.medecin med " +
            "JOIN  med.cabinet cab " +
            "WHERE cab.id= :cabinetId AND rdv.date= :dateRdv")
    boolean checkCabinetQueueInitialized(@Param("cabinetId") String cabinetId, @Param("dateRdv") String dateRdv);

    @Modifying
    @Query("INSERT INTO Queue (rendezvousId, isArrive, arriveTime, isInside, isOutside, quitTime)  " +
            "SELECT rdv.id, false, '', false, true, '' FROM Rendezvous rdv " +
            "JOIN  rdv.medecin med " +
            "JOIN  med.cabinet cab " +
            "WHERE cab.id= :cabinetId AND rdv.date= :dateRdv")
    int initializeCabinetQueue(@Param("cabinetId") String cabinetId, @Param("dateRdv") String dateRdv);

    @Query("SELECT q " +
            "FROM Queue q " +
            "JOIN  q.rendezvous rdv " +
            "JOIN  rdv.medecin med " +
            "JOIN  med.cabinet cab " +
            "WHERE cab.id= :cabinetId")
    List<Queue> getAllQueuesByCabinet(@Param("cabinetId") String cabinetId);

    @Query("SELECT q FROM Queue q WHERE q.rendezvousId = :rendezvousId")
    Queue findByRendezvousId(@Param("rendezvousId") Long rendezvousId);

    @Query("SELECT q FROM Queue q WHERE q.isArrive = true AND q.isInside = false AND q.rendezvous.time LIKE :today ORDER BY q.arriveTime ASC")
    List<Queue> findByIsArriveTrueAndIsInsideFalse(@Param("today") String today);
}
