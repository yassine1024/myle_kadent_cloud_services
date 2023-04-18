package com.healthcaredental.reception.Queue;

import com.healthcaredental.reception.rendezvous.Rendezvous;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
@DynamicInsert
@Data
public class Queue {

    @Id
    private Long rendezvousId;

    private  boolean isArrive;
    // time with this format HH:MM:SS
    private  String arriveTime;
    //Inside means that the patient is taking medical care
    private boolean isInside;
    private boolean isOutside;
    private String quitTime;

    @OneToOne
    @MapsId
    private Rendezvous rendezvous;
}
