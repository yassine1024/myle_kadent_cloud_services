package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class RendezvousPostPoned {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oldDate;
    private String oldTime;
    private String newDate;
    private String newTime;
    @ManyToOne
    private Rendezvous rendezvous;
    @CreationTimestamp
    protected LocalDateTime createdAt;
    @UpdateTimestamp
    protected LocalDateTime updatedAt;
}
