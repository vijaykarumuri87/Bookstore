# Bookstore
This bookstore java application allows users to add, update, remove and fetching books. Additionally users should be able to filter books based on certain conditions <br />


Here DB script used <br/>

-- Table: public.book

-- DROP TABLE IF EXISTS public.book;

CREATE TABLE IF NOT EXISTS public.book
(
    id integer NOT NULL DEFAULT nextval('book_id_seq'::regclass),
    title character varying(255) COLLATE pg_catalog."default" NOT NULL,
    format text COLLATE pg_catalog."default" NOT NULL,
    volumes integer NOT NULL,
    genre character varying(255) COLLATE pg_catalog."default" NOT NULL,
    author character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT book_pkey PRIMARY KEY (id),
    CONSTRAINT book_format_check CHECK (format = ANY (ARRAY['ebook'::text, 'paperback'::text, 'hardcover'::text]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book
    OWNER to postgres;
