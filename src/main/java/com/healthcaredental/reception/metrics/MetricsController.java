package com.healthcaredental.reception.metrics;

import com.healthcaredental.reception.rendezvous.Rendezvous;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cabinets/{id}/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final MetricsService metricsService;

    @GetMapping("/totalPatients")
    public long getTotalPatients(@PathVariable String id) {
        return metricsService.getTotalPatients(id);
    }

    @GetMapping("/totalAppointments")
    public long getTotalAppointments(@PathVariable String id) {
        return metricsService.getTotalAppointments(id);
    }

    @GetMapping("/totalDoctors")
    public long getTotalDoctors(@PathVariable String id) {
        return metricsService.getTotalDoctors(id);
    }

    @GetMapping("/upcomingAppointments")
    public List<Rendezvous> fetchUpcomingAppointments(@PathVariable String id) {
        return metricsService.fetchUpcomingAppointments(id);
    }

    @GetMapping("/notifications")
    public List<String> fetchNotifications(@PathVariable String id) {
        return metricsService.fetchNotifications(id);
    }
}
