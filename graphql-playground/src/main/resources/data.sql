
DROP TABLE IF EXISTS customer;

CREATE TABLE customer(
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name VARCHAR(50),
    age INT,
    city VARCHAR(50)
);

insert into customer(name, age, city)
values
    ('sam', 10, 'atlanta'),
    ('mike', 15, 'houston'),
    ('jake', 20, 'miami'),
    ('john', 30, 'las vegas');