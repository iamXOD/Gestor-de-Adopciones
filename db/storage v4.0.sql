BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS "Direccion" (
	"direccion_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"callePrincipal"	TEXT NOT NULL,
	"entreCalle"	TEXT,
	"yCalle"	TEXT,
	"no"	INTEGER NOT NULL,
	"localidad"	TEXT NOT NULL,
	"municipio"	TEXT NOT NULL,
	"provincia"	TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS "Adoptante" (
	"adoptante_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"nombre"	TEXT NOT NULL,
	"primerApellido"	TEXT NOT NULL,
	"segundoApellido"	TEXT NOT NULL,
	"ciOPasaporte"	TEXT NOT NULL UNIQUE,
	"genero"	INTEGER,
	"direccion_id"	INTEGER,
	FOREIGN KEY("direccion_id") REFERENCES "Direccion"("direccion_id") ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS "Mascota" (
	"mascota_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	"adoptante_id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"raza"	TEXT,
	"color"	TEXT,
	"edad"	INTEGER,
	"genero"	INTEGER,
	"peso"	REAL,
	"ultimaDesparacitacion"	TEXT,
	"ultimaVacunacion"	TEXT,
	"fechaAdopcion"	TEXT,
	FOREIGN KEY("adoptante_id") REFERENCES "Adoptante"("adoptante_id") ON UPDATE CASCADE
);

INSERT INTO "Direccion" ("direccion_id","callePrincipal","entreCalle","yCalle","no","localidad","municipio","provincia") VALUES (1,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana'),
INSERT INTO "Adoptante" ("adoptante_id","nombre","primerApellido","segundoApellido","ciOPasaporte","genero","direccion_id") VALUES (1,'Harold','Muñoz','Garcés','97082209601',1,1);
INSERT INTO "Mascota" ("mascota_id","adoptante_id","nombre","raza","color","edad","genero","peso","ultimaDesparacitacion","ultimaVacunacion","fechaAdopcion") VALUES (1,1,'Jade','Pastor Aleman','Blanco-Gris',15,0,10.2,'2019-12-20','2019-12-18','2010-10-09');
 (3,'160','349','176',1234,'1ro de Mayo','Boyeros','La Habana');

 CREATE INDEX IF NOT EXISTS "adoptante_id" ON "Adoptante" (
	"adoptante_id"	DESC
);

CREATE INDEX IF NOT EXISTS "adoptante_ci" ON "Adoptante" (
	"ciOPasaporte"
);

CREATE INDEX IF NOT EXISTS "adoptante_direccion" ON "Adoptante" (
	"direccion_id"	DESC
);

CREATE INDEX IF NOT EXISTS "direccion_id" ON "Direccion" (
	"direccion_id"	DESC
);

CREATE INDEX IF NOT EXISTS "direccion_no" ON "Direccion" (
	"KEYWORDASCOLUMNNAME"
);

CREATE INDEX IF NOT EXISTS "mascota_id" ON "Mascota" (
	"mascota_id"	DESC
);

CREATE INDEX IF NOT EXISTS "mascota_adoptante" ON "Mascota" (
	"adoptante_id"	DESC
);

CREATE INDEX IF NOT EXISTS "mascota_raza" ON "Mascota" (
	"raza"
);

CREATE INDEX IF NOT EXISTS "mascota_fechaAdopcion" ON "Mascota" (
	"fechaAdopcion"
);

CREATE VIEW Adopcion(ID, Mascota, Adoptante, Fecha) as SELECT Mascota.mascota_id as ID, Mascota.nombre as Mascota, Adoptante.nombre || ' ' || Adoptante.primerApellido || ' ' || Adoptante.segundoApellido as Adoptante, Mascota.fechaAdopcion as Fecha FROM Adoptante INNER JOIN Mascota USING(adoptante_id) ORDER BY Mascota.adoptante_id;

COMMIT;
