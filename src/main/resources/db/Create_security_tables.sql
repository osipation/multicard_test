CREATE TABLE users
(
    id       bigint UNIQUE  not null,
    username text UNIQUE NOT NULL,
    password text        NOT NULL,
    name text not null,
    lastname text not null,
    age int not null check(age > 0),
    enabled  boolean     NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO users
VALUES (1, 'user1', '{noop}123', 'Nik', 'Osipatov', 27, true),
       (2, 'user2', '{noop}123', 'Roger', 'Federer', 39, true),
	   (3, 'user3', '{noop}123', 'Mike', 'Wazovski', 18, true);

CREATE TABLE authorities
(
    user_id   bigint NOT NULL,
    authority text NOT NULL,

    CONSTRAINT authorities_idx UNIQUE (user_id, authority),

    CONSTRAINT authorities_ibfk_1
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);

INSERT INTO authorities
VALUES (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');