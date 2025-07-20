CREATE TABLE IF NOT EXISTS `loans` (
  `loan_id` int AUTO_INCREMENT PRIMARY KEY,
  `loan_number` varchar(100) NOT NULL,
    `account_number` varchar(100) NOT NULL,
    `customer_account_number` int NOT NULL,
  `loan_type` varchar(100) NOT NULL,
  `total_loan` int NOT NULL,
  `amount_paid` int NOT NULL,
  `outstanding_amount` int NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
    );