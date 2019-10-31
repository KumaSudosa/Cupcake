DROP DATABASE IF EXISTS cupcakeshop;
CREATE DATABASE cupcakeshop;
USE cupcakeshop;


CREATE TABLE test (
	testName VARCHAR (30)
);
INSERT INTO test (testName) VALUES (
    'Test1'
);
INSERT INTO test (testName) VALUES (
    'TestB'
);
INSERT INTO test (testName) VALUES (
    'TestThree'
);


CREATE TABLE users (
	username VARCHAR (30),
    login VARCHAR (30),
    email VARCHAR (40),
    balance DOUBLE,
    role VARCHAR(2),
    PRIMARY KEY (email),
    UNIQUE KEY `email_UNIQUE` (`email`)
);
INSERT INTO users (username, login, email, balance, role) VALUES ('Marcus', 'Marcus123', 'Marcus@Marcus.dk', 0, 'a');
INSERT INTO users (username, login, email, balance, role) VALUES ('Andreas', 'Andreas123', 'Andreas@Andreas.dk', 0, 'a');
INSERT INTO users (username, login, email, balance, role) VALUES ('Michael', 'Michael123', 'Michael@Michael.dk', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Cahit', 'Cahit123', 'Cahit@Cahit.dk', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('James', 'Ja1234', 'James@gmail.com', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Mohammed', 'Mo1234', 'Mohammed@gmail.com', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Sergio', 'Se1234', 'Sergio@gmail.com', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Vladimir', 'Vl1234', 'Vladimir@gmail.com', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Haruki', 'Ha1234', 'Haruki@gmail.com', 50, 'c');
INSERT INTO users (username, login, email, balance, role) VALUES ('Dembele', 'De1234', 'Dembele@gmail.com', 50, 'c');


CREATE TABLE bottoms (
	bottom VARCHAR (15),
    price DOUBLE,
    id int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);
INSERT INTO bottoms (bottom, price) VALUES ('Chocolate', 5.00);
INSERT INTO bottoms (bottom, price) VALUES ('Vanilla', 5.00);
INSERT INTO bottoms (bottom, price) VALUES ('Nutmeg', 5.00);
INSERT INTO bottoms (bottom, price) VALUES ('Pistachio', 6.00);
INSERT INTO bottoms (bottom, price) VALUES ('Almond', 7.00);


CREATE TABLE toppings (
	topping VARCHAR (15),
    price DOUBLE,
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
);
INSERT INTO toppings (topping, price) VALUES ('Chocolate', 5.00);
INSERT INTO toppings (topping, price) VALUES ('Blueberry', 5.00);
INSERT INTO toppings (topping, price) VALUES ('Raspberry', 5.00);
INSERT INTO toppings (topping, price) VALUES ('Crispy', 6.00);
INSERT INTO toppings (topping, price) VALUES ('Strawberry', 6.00);
INSERT INTO toppings (topping, price) VALUES ('Rum/Raisin', 7.00);
INSERT INTO toppings (topping, price) VALUES ('Orange', 8.00);
INSERT INTO toppings (topping, price) VALUES ('Lemon', 8.00);
INSERT INTO toppings (topping, price) VALUES ('Blue cheese', 9.00);


CREATE TABLE invoice (
	id_invoice INT NOT NULL AUTO_INCREMENT,
    email VARCHAR (30),
    order_date VARCHAR (20),
    PRIMARY KEY (id_invoice),
	FOREIGN KEY (email) REFERENCES users (email) ON DELETE NO ACTION ON UPDATE NO ACTION
);
	ALTER TABLE invoice AUTO_INCREMENT = 100001;
    INSERT INTO invoice (email, order_date) VALUES ('James@gmail.com', '24-10-2019');
    INSERT INTO invoice (email, order_date) VALUES ('Michael@Michael.dk', '04-10-2019');
    INSERT INTO invoice (email, order_date) VALUES ('James@gmail.com', '16-10-2019');
    INSERT INTO invoice (email, order_date) VALUES ('Vladimir@gmail.com', '01-11-2019');
    INSERT INTO invoice (email, order_date) VALUES ('Haruki@gmail.com', '28-10-2019');


CREATE TABLE lineItems (
	id_invoice INT,
	id_top INT,
    id_bot INT,
    amount INT,
    FOREIGN KEY (id_top) REFERENCES toppings (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (id_bot) REFERENCES bottoms (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (id_invoice) REFERENCES invoice (id_invoice) ON DELETE NO ACTION ON UPDATE NO ACTION,
    PRIMARY KEY (id_top, id_bot, id_invoice)
);
	INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100001, 1, 1, 5);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100002, 2, 2, 2);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100002, 1, 2, 2);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100003, 4, 5, 4);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100004, 6, 4, 3);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100002, 3, 3, 3);
    INSERT INTO lineItems (id_invoice, id_top, id_bot, amount) VALUES (100002, 1, 5, 6);