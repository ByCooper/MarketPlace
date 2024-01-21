-- liquibase formatted sql

-- changeset ByCooper:1
CREATE TABLE "users"
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    username    VARCHAR NOT NULL UNIQUE,
    password    VARCHAR NOT NULL,
    email       VARCHAR NOT NULL UNIQUE,
    phone       VARCHAR NOT NULL UNIQUE,
    avatar_path VARCHAR
);

CREATE TABLE "ads"
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title       VARCHAR          NOT NULL,
    description VARCHAR          NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    image_path  VARCHAR,
    user_id     BIGINT           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
CREATE TABLE "comments"
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    text          VARCHAR NOT NULL,
    creation_time BIGINT  NOT NULL,
    ad_id         BIGINT  NOT NULL,
    FOREIGN KEY (ad_id) REFERENCES ads (id)
);

-- changeset ByCooper:2
CREATE TABLE "roles"
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE
);

-- changeset ByCooper:3
CREATE TABLE "users_roles"
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- changeset ByCooper:4
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO roles (name) VALUES ('ROLE_ADMIN');