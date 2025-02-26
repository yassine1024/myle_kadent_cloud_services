package com.healthcaredental.reception.rendezvous;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RendezvousDetailRepository extends JpaRepository<RendezvousDetail, RendezvousDetailId> {

    List<RendezvousDetail> findByIdRendezvous(Rendezvous rendezvous);

    List<RendezvousDetail> findByIdDate(LocalDate date);
}
