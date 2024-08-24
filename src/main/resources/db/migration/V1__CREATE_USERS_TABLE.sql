CREATE TABLE users
(
    id            uuid                     NOT NULL,
    username      character varying(255) NOT NULL,
    first_name     character varying(255),
    last_name     character varying(255),
    email         character varying(255)   NOT NULL,
    password_hash character varying(255)   NOT NULL,
    created_at    timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "users_id_PK" PRIMARY KEY (id),
    CONSTRAINT email UNIQUE ("email")
);