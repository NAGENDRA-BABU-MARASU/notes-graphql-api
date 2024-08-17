CREATE TABLE tags(
    id uuid,
    name character varying(255) NOT NULL,
    CONSTRAINT "tags_id_PK" PRIMARY KEY (id),
    CONSTRAINT "tags_name_unique_CS" UNIQUE (name)
);