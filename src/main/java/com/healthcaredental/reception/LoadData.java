package com.healthcaredental.reception;


import com.github.javafaker.Faker;
import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientRepository;
import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.cabinet.CabinetRepository;
import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.cabinet.CabinetVisitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    private final CabinetRepository cabinetRepository;
    private final PatientRepository patientRepository;
    private final CabinetVisitRepository cabinetVisitRepository;

    public LoadData(CabinetRepository cabinetRepository, PatientRepository patientRepository, CabinetVisitRepository cabinetVisitRepository) {
        this.cabinetRepository = cabinetRepository;
        this.patientRepository = patientRepository;
        this.cabinetVisitRepository = cabinetVisitRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        // Create two cabinets
        Cabinet cabinet1 = new Cabinet();
        cabinet1.setId("1");
        cabinet1.setName("Cabinet 1");
        cabinet1.setField("Dentistry");
        cabinet1.setAddress(faker.address().streetAddress());
        cabinet1.setPhoneNumber(faker.phoneNumber().phoneNumber());
        cabinetRepository.save(cabinet1);

        Cabinet cabinet2 = new Cabinet();
        cabinet2.setId("2");
        cabinet2.setName("Cabinet 2");
        cabinet2.setField("Orthodontics");
        cabinet2.setAddress(faker.address().streetAddress());
        cabinet2.setPhoneNumber(faker.phoneNumber().phoneNumber());
        cabinetRepository.save(cabinet2);

        // Create 3 patients for cabinet 1
        for (int i = 0; i < 3; i++) {
            Patient patient = new Patient();
            patient.setId(faker.idNumber().valid());
            patient.setFirstName(faker.name().firstName());
            patient.setLastName(faker.name().lastName());
            patient.setPhoneNumber(faker.phoneNumber().phoneNumber());
            patient.setAddress(faker.address().streetAddress());
            patient.setAge(String.valueOf(faker.number().numberBetween(5, 100)));
            patient.setGender(faker.demographic().sex());
            patient.setJob(faker.job().title());
            patientRepository.save(patient);

            CabinetVisit visit = new CabinetVisit();
            visit.setCabinet(cabinet1);
            visit.setPatient(patient);
            cabinetVisitRepository.save(visit);
        }

        // Create 10 patients for cabinet 2
        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setId(faker.idNumber().valid());
            patient.setFirstName(faker.name().firstName());
            patient.setLastName(faker.name().lastName());
            patient.setPhoneNumber(faker.phoneNumber().phoneNumber());
            patient.setAddress(faker.address().streetAddress());
            patient.setAge(String.valueOf(faker.number().numberBetween(5, 100)));
            patient.setGender(faker.demographic().sex());
            patient.setJob(faker.job().title());
            patientRepository.save(patient);

            CabinetVisit visit = new CabinetVisit();
            visit.setCabinet(cabinet2);
            visit.setPatient(patient);
            cabinetVisitRepository.save(visit);
        }
    }
}