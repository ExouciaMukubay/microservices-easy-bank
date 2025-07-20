CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` int AUTO_INCREMENT  PRIMARY KEY,
    `customer_account_number` int NOT NULL,
    `surname` varchar(100) NOT NULL,
    `lastname` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL,
    `mobile_number` varchar(20) NOT NULL,
    `customer_type` varchar(100) NOT NULL,
    `address` varchar(255) NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `accounts` (
    `account_id` int AUTO_INCREMENT PRIMARY KEY,
    `account_number` int NOT NULL,
    `account_type` varchar(100) NOT NULL,
    `customer_id` int NOT NULL,
    `created_at` date NOT NULL,
    `created_by` varchar(20) NOT NULL,
    `updated_at` date DEFAULT NULL,
    `updated_by` varchar(20) DEFAULT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
    );