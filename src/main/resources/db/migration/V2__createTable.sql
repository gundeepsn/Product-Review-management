create table Customer (
    ID int not null auto_increment,
    NAME varchar(50) not null,
    EMAIL varchar(50),
    PRIMARY KEY (ID)
);

create table Product (
    ID int not null auto_increment,
    NAME varchar(50) not null,
    PRIMARY KEY (ID)
);

create table Review (
    ID int not null auto_increment,
    name TEXT not null,
    product_id int not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (product_id) REFERENCES Product(ID)
    ON DELETE CASCADE
);
create table Comment(
    ID int not null auto_increment,
    review_id int not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (review_id) REFERENCES Review(ID)
    ON DELETE CASCADE
);