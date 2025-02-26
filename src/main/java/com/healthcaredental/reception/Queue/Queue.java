package com.healthcaredental.reception.Queue;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;


@Entity
@DynamicInsert
@Data
public class Queue {

    @Id
    private Long rendezvousId;
    @JsonProperty("isArrive")
    private  boolean isArrive;
    // time with this format HH:MM:SS
    private  String arriveTime;
    //Inside means that the patient is taking medical care
    @JsonProperty("isInside")
    private boolean isInside;
    private String insideTime;
    @JsonProperty("isOutside")
    private boolean isOutside;
    private String quitTime;

    @OneToOne
    @MapsId
    private Rendezvous rendezvous;

    public Queue(){}
    public Queue(Long rendezvousId){
        this.rendezvousId=rendezvousId;
    }
}
