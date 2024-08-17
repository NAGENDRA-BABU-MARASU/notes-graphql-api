CREATE TABLE categories
(
    id          uuid,
    name        character varying(255) not null,
    user_id     uuid,
    description text,
    CONSTRAINT "categories_id_PK" PRIMARY KEY (id)
);

ALTER TABLE categories
    ADD CONSTRAINT "categories_user_FK" FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID;