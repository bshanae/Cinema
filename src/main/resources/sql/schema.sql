create table halls
(
    serial_number   INT PRIMARY KEY,
    number_of_seats INT NOT NULL
);

create table messages
(
    id              INT PRIMARY KEY,
    film_id         INT,
    send_time       BIGINT,
    sender_id       VARCHAR,
    body            VARCHAR
);