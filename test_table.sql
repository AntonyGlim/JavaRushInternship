USE test;

DROP TABLE IF EXISTS products;
CREATE TABLE products
(
    id    BIGINT(20) NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NULL,
    cost  INT(4) NULL,
    PRIMARY KEY (id)
);
-- \d products;
INSERT INTO products (title, cost) VALUES
('milk', 32),
('bread', 18),
('cheese', 99),
('tomato', 47),
('lime', 32),
('butter', 87),
('apple', 98),
('asparagus', 18),
('eggs', 60),
('banana', 44),
('beef', 55),
('beet', 78),
('berry', 200),
('biscuits', 120),
('bream', 150),
('cabbage', 60),
('cake', 35),
('carrot', 78),
('cherry', 250),
('sold', 12);