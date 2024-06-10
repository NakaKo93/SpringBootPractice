CREATE TABLE IF NOT EXISTS instant.contacts(
    id INT AUTO_INCREMENT NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    zip_code VARCHAR(8) NOT NULL,
    address VARCHAR(255) NOT NULL,
    building_name VARCHAR(255) NOT NULL,
    contact_type VARCHAR(50) NOT NULL,
    body TEXT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);