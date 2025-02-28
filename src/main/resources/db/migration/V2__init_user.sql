-- V2__init_user.sql

-- Create table for the User entity.
-- Note: "user" is a reserved word in MySQL so we enclose it in backticks.
CREATE TABLE `user` (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    employee_id VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE KEY unique_username (username),  -- enforce uniqueness of username
    CONSTRAINT fk_user_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);

-- Create table for storing the roles (element collection).
-- The default naming by JPA is typically USER_ROLES; adjust if needed.
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    roles VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id, roles),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES `user`(id)
);

-- Create the pointage table that stores one record per employee per day.
CREATE TABLE pointage (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          employee_id VARCHAR(255),
                          pointage_date DATE NOT NULL,
                          arrival_time TIME NOT NULL,
                          departure_time TIME,
                          device_name VARCHAR(255) NOT NULL,
                          PRIMARY KEY (id),
                          CONSTRAINT unique_employee_date UNIQUE (employee_id, pointage_date),
                          CONSTRAINT fk_pointage_employee FOREIGN KEY (employee_id) REFERENCES employee(id)
);
