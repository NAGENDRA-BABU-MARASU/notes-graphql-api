ALTER TABLE notes
    ADD CONSTRAINT "notes_categories_id_FK" FOREIGN KEY (category_id)
        REFERENCES categories (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE SET NULL
        NOT VALID;