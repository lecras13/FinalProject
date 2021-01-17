CREATE TABLE IF NOT EXISTS users
(
    id           BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    login        VARCHAR(20)  NOT NULL UNIQUE,
	password     VARCHAR(255)  NOT NULL,
    first_name   VARCHAR(15)  NOT NULL,
    last_name    VARCHAR(15)  NOT NULL,
    role         ENUM ('ADMIN', 'USER') DEFAULT 'USER',
    total_amount DOUBLE,
    status_block BIT     DEFAULT 0,
    traffic      BIGINT  DEFAULT 0,
    discount TINYINT  DEFAULT 0,
   
	tariff_id BIGINT,
    promotion_id BIGINT,
    FOREIGN KEY (tariff_id) REFERENCES tariff_plans (id),
    FOREIGN KEY (promotion_id) REFERENCES promotions (id)
);

CREATE TABLE IF NOT EXISTS tariff_plans (
   id    BIGINT              NOT NULL PRIMARY KEY AUTO_INCREMENT,
   tariff_name  VARCHAR(15)  NOT NULL,
   price DOUBLE              NOT NULL,
   description  TEXT(500)   NOT NULL 
 );

CREATE TABLE IF NOT EXISTS payments (
   id     BIGINT     NOT NULL PRIMARY KEY AUTO_INCREMENT,
   amount DOUBLE     NOT NULL,
   payment_date   DATE       NOT NULL,
   user_id BIGINT,
   FOREIGN KEY (user_id) REFERENCES users(id)
 );
 
 CREATE TABLE IF NOT EXISTS promotions (
   id     BIGINT                NOT NULL PRIMARY KEY AUTO_INCREMENT,
   promotion_name  VARCHAR(55)  NOT NULL,
   start_date   DATE            NOT NULL,
   end_date   DATE              NOT NULL,
   description  TEXT(500)      NOT NULL,
   
   tariff_id BIGINT NOT NULL,
   new_price               DOUBLE              NOT NULL,
   FOREIGN KEY (tariff_id) REFERENCES tariff_plans (id)
 );