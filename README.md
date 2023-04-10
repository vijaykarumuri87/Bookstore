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


API should be able to handle following CRUD operations

1) Add new Book
   curl --location 'http://localhost:8081/api/v1/books/addBook' \
   --header 'Content-Type: application/json' \
   --data '{
   "title": "MyBook4",
   "author": "Author2",
   "price": 1300,
   "format": "ebook",
   "volumes": 60,
   "genre": "other"
   }'

2) Update existing book
   curl --location --request PUT 'http://localhost:8081/api/v1/books/updateBook' \
   --header 'Content-Type: application/json' \
   --data '{
   "title": "MyBook",
   "author": "Author2",
   "price": 1200,
   "format": "paperback",
   "volumes": 1,
   "genre": "test",
   "id": 17
   }'


3) Delete a book
   curl --location --request DELETE 'http://localhost:8081/api/v1/books/deleteBookById/20' \
   --data ''

4) Fetch all books
   curl --location 'http://localhost:8081/api/v1/books/getAllBooks'

we can also filter books with pagination
curl --location 'http://localhost:8081/api/v1/books/getAllBooks?page=1&size=1'

API should be able to handle the following search requests:

1)  All eBooks
    curl --location 'http://localhost:8081/api/v1/books/fetchAlleBooks'

2)  Books with more than 4 volumes/series
    curl --location 'http://localhost:8081/books/fetchVolumesGreaterThanFour'

3)  Books with particular author but not specific genre.
    curl --location 'http://localhost:8081/api/v1/books/fetchByAuthorAndExcludeGenre?author=Author2&excludeGenre=test'

