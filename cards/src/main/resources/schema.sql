CREATE TABLE IF NOT EXISTS `cards` (
  `card_id` int AUTO_INCREMENT PRIMARY KEY,
  `account_number` varchar(100) NOT NULL,
  `customer_account_number` int NOT NULL,
  `card_number` varchar(100) NOT NULL,
  `card_type` varchar(100) NOT NULL,
  `total_limit` int NOT NULL,
  `amount_used` int NOT NULL,
  `available_amount` int NOT NULL,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL);