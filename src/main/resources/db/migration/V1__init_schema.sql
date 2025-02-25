-- V1__init_schema.sql

-- 1. Table: cabinet
CREATE TABLE cabinet (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    field VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(255),
    PRIMARY KEY (id)
);

-- 2. Table: patient
CREATE TABLE patient (
    id VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    phone_number VARCHAR(255),
    address VARCHAR(255),
    gender VARCHAR(255),
    job VARCHAR(255),
    age VARCHAR(255),
    birth_date VARCHAR(255),
    email VARCHAR(255),
    PRIMARY KEY (id)
);

-- 3. Table: employee
-- (Using SINGLE_TABLE inheritance for Employee and its subclasses: Assistant, HouseMaid, Medecin, Other)
CREATE TABLE employee (
    id VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address VARCHAR(255),
    phone_number VARCHAR(255),
    photo VARCHAR(255),
    document_folder VARCHAR(255),
    cabinet_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    -- Fields specific to Medecin (nullable for other types)
    salary FLOAT,
    recette FLOAT,
    pourcentage FLOAT,
    -- Discriminator column to distinguish subtypes
    dtype VARCHAR(31) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_employee_cabinet FOREIGN KEY (cabinet_id) REFERENCES cabinet(id)
);

-- 4. Table: cabinet_visit
CREATE TABLE cabinet_visit (
    id BIGINT NOT NULL AUTO_INCREMENT,
    cabinet_id VARCHAR(255),
    patient_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_cabinet_visit_cabinet FOREIGN KEY (cabinet_id) REFERENCES cabinet(id),
    CONSTRAINT fk_cabinet_visit_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

-- 5. Many-to-Many Join Table: patient_treat
-- (Between Medecin (employee) and Patient)
CREATE TABLE patient_treat (
    medecin_id VARCHAR(255) NOT NULL,
    patient_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (medecin_id, patient_id),
    CONSTRAINT fk_patient_treat_medecin FOREIGN KEY (medecin_id) REFERENCES employee(id),
    CONSTRAINT fk_patient_treat_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
);

-- 6. Table: rendezvous
CREATE TABLE rendezvous (
    id BIGINT NOT NULL AUTO_INCREMENT,
    date VARCHAR(255),
    time VARCHAR(255),
    complaint VARCHAR(255),
    acte_to_perform VARCHAR(255),
    patient_id VARCHAR(255),
    medecin_id VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_rendezvous_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
    CONSTRAINT fk_rendezvous_medecin FOREIGN KEY (medecin_id) REFERENCES employee(id)
);

-- 7. Table: queue
-- (Primary key is also a foreign key to rendezvous via @MapsId)
CREATE TABLE queue (
    rendezvous_id BIGINT NOT NULL,
    is_arrive BOOLEAN,
    arrive_time TIME,
    is_inside BOOLEAN,
    inside_time TIME,
    is_outside BOOLEAN,
    quit_time TIME,
    PRIMARY KEY (rendezvous_id),
    CONSTRAINT fk_queue_rendezvous FOREIGN KEY (rendezvous_id) REFERENCES rendezvous(id)
);

-- 8. Table: rendezvous_detail
-- (Composite primary key made of rendezvous_id and date)
CREATE TABLE rendezvous_detail (
    rendezvous_id BIGINT NOT NULL,
    date VARCHAR(255) NOT NULL,
    medecin_id VARCHAR(255),
    arrive_time VARCHAR(255),
    enter_time VARCHAR(255),
    exit_time VARCHAR(255),
    PRIMARY KEY (rendezvous_id, date),
    CONSTRAINT fk_rendezvous_detail_rendezvous FOREIGN KEY (rendezvous_id) REFERENCES rendezvous(id),
    CONSTRAINT fk_rendezvous_detail_medecin FOREIGN KEY (medecin_id) REFERENCES employee(id)
);

-- 9. Table: rendezvous_postponed
CREATE TABLE rendezvous_postponed (
    id BIGINT NOT NULL AUTO_INCREMENT,
    reason VARCHAR(255),
    rendezvous_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_rendezvous_postponed_rendezvous FOREIGN KEY (rendezvous_id) REFERENCES rendezvous(id)
);
