CREATE TABLE note_tags
(
    note_id uuid,
    tag_id  uuid,
    CONSTRAINT "note_tags_PK" PRIMARY KEY (note_id, tag_id)
);

ALTER TABLE note_tags
    ADD CONSTRAINT "note_tags_tag_id_FK" FOREIGN KEY (tag_id)
        REFERENCES tags (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID;

ALTER TABLE note_tags
    ADD CONSTRAINT "note_tags_note_id_FK" FOREIGN KEY (note_id)
        REFERENCES notes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
        NOT VALID;