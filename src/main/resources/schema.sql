CREATE TABLE IF NOT EXISTS customer
(
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile_number VARCHAR(20) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATE NOT NULL,
    updated_by VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS accounts
(
    customer_id INT NOT NULL,
    account_number int auto_increment primary key,
    account_type VARCHAR(255) NOT NULL,
    branch_address VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATE NOT NULL,
    updated_by VARCHAR(255) NOT NULL
)