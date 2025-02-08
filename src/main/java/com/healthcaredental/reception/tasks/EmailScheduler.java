package com.healthcaredental.reception.tasks;

import com.healthcaredental.reception.Queue.Queue;
import com.healthcaredental.reception.Queue.QueueRepository;
import com.healthcaredental.reception.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final QueueRepository queueRepository;
    private final MailService mailService;

    @Scheduled(fixedRate = 120000) // 2400000 milliseconds = 40 minutes
    public void sendReminderEmails() {
        //Yassine you should send email to only the patients who are waiting in the queue and have not been seen by the doctor yet for today,
        // due to could a patient came but sudenlly go outside and didn't come back without postponed his appointment.
        List<Queue> queues = queueRepository.findByIsArriveTrueAndIsInsideFalse();
        int order = 1;
        for (Queue queue : queues) {
            String email = queue.getRendezvous().getPatient().getEmail();
            if(email == null) {
                order++;
                continue;
            }
            String subject = "Reminder: Your appointment is waiting";
            String text = "Dear " + queue.getRendezvous().getPatient().getFirstName() + ",\n\n" +
                    "You have arrived at the clinic but have not yet been seen by the doctor. " +
                    "Your new order in the queue is " + order + ". Please be patient, and we will attend to you shortly.\n\n" +
                    "Thank you.";
            mailService.sendEmail(email, subject, text);
            order++;
        }
    }
}
