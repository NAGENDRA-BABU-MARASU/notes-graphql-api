CREATE TABLE notes(
    id uuid,
    content text,
    title character varying(255),
    user_id uuid,
    created_at timestamp without time zone,
    updated_at timestamp without time zone,
    category_id uuid,
    CONSTRAINT "notes_id_PK" PRIMARY KEY (id)
);

ALTER TABLE notes
    ADD CONSTRAINT "notes_user_id_FK" FOREIGN KEY (user_id)
    REFERENCES users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;

