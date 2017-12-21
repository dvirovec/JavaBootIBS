DROP TABLE erp."Greeting";

CREATE TABLE erp."Greeting"
(
  id SERIAL ,
  text character varying(100),
  CONSTRAINT "PKey" PRIMARY KEY ("id")
)
WITH (
  OIDS=FALSE
);
ALTER TABLE erp."Greeting"
  OWNER TO postgres;
---------------------------------
CREATE TABLE base.company
(
  id serial NOT NULL,
  name character(100),
  address character varying(100),
  "CID" character(40),
  CONSTRAINT company_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE base.company
  OWNER TO postgres;
---------------------------------
CREATE TABLE base.department
(
  id serial NOT NULL,
  name character(100),
  address character varying(100),
  parent_id bigint,
  company_id bigint REFERENCES base.company(id),
  CONSTRAINT department_pkey PRIMARY KEY (id)

)
WITH (
  OIDS=FALSE
);
ALTER TABLE base.department
  OWNER TO postgres;
---------------------------------