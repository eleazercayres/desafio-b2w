-- DROP SCHEMA configurer_catalog;

CREATE SCHEMA configurer_catalog AUTHORIZATION postgres;

-- Drop table

-- DROP TABLE configurer_catalog."catalog"

CREATE TABLE configurer_catalog."catalog" (
	id int8 NOT NULL,
	"key" varchar(255) NULL,
	rule_id int8 NOT NULL,
	CONSTRAINT catalog_pkey PRIMARY KEY (id),
	CONSTRAINT uk_12p7x9lqfuhgb1hee624uqtk9 UNIQUE (key),
	CONSTRAINT fkd3r161j9wh7ylpwecfr7eddf5 FOREIGN KEY (rule_id) REFERENCES configurer_catalog.rule(id)
);

-- Drop table

-- DROP TABLE configurer_catalog.offer_view

CREATE TABLE configurer_catalog.offer_view (
	view_id int8 NOT NULL,
	offer_id int8 NOT NULL,
	CONSTRAINT fkco8j6xdr2ndxvp8qrhu4sqbr FOREIGN KEY (view_id) REFERENCES configurer_catalog.view(id)
);

-- Drop table

-- DROP TABLE configurer_catalog."rule"

CREATE TABLE configurer_catalog."rule" (
	id int8 NOT NULL,
	"type" varchar(255) NULL,
	value varchar(255) NULL,
	rule_dad_id int8 NULL,
	offer_id int8 NOT NULL,
	CONSTRAINT rule_pkey PRIMARY KEY (id),
	CONSTRAINT fkdw8uqfhl3kdg23pmsht5hxihn FOREIGN KEY (offer_id) REFERENCES configurer_catalog.view(id),
	CONSTRAINT fkiii50jqaxpxf8ithi7viha87b FOREIGN KEY (rule_dad_id) REFERENCES configurer_catalog.rule(id),
	CONSTRAINT fkiylx2drda2j9euv1hp5dqvyco FOREIGN KEY (id) REFERENCES configurer_catalog.rule(id)
);

-- Drop table

-- DROP TABLE configurer_catalog."view"

CREATE TABLE configurer_catalog."view" (
	id int8 NOT NULL,
	CONSTRAINT view_pkey PRIMARY KEY (id)
);

