-- ----------------------------
-- Table: customer
-- ----------------------------

CREATE TABLE customer
(
    customer_id             BIGSERIAL PRIMARY KEY,
    customer_account_number BIGINT UNIQUE NOT NULL,
    surname                 VARCHAR(255),
    lastname                VARCHAR(255),
    email                   VARCHAR(255),
    mobile_number           VARCHAR(50) UNIQUE,
    customer_type           VARCHAR(50),
    address                 VARCHAR(255),
    created_at              TIMESTAMP,
    created_by              VARCHAR(255),
    updated_at              TIMESTAMP,
    updated_by              VARCHAR(255)
);

-- ----------------------------
-- Table: accounts
-- ----------------------------

CREATE TABLE accounts
(
    account_id     BIGSERIAL PRIMARY KEY,
    account_number BIGINT UNIQUE NOT NULL,
    account_type   VARCHAR(50),
    customer_id    BIGINT        NOT NULL,
    created_at     TIMESTAMP,
    created_by     VARCHAR(255),
    updated_at     TIMESTAMP,
    updated_by     VARCHAR(255),
    CONSTRAINT fk_accounts_customer FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
        ON DELETE CASCADE
);
