BEGIN TRANSACTION;

CREATE TABLE IF NOT EXISTS "Adoptante" (
	"adoptante_id"	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	"nombre"	TEXT NOT NULL,
	"primerApellido"	TEXT NOT NULL,
	"segundoApellido"	TEXT NOT NULL,
	"ciOPasaporte"	TEXT NOT NULL UNIQUE,
	"genero"	INTEGER,
	"callePrincipal"	TEXT NOT NULL,
	"entreCalle"	TEXT,
	"yCalle"	TEXT,
	"no"	TEXT NOT NULL,
	"localidad"	TEXT NOT NULL,
	"municipio"	TEXT NOT NULL,
	"provincia"	TEXT NOT NULL
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
	FOREIGN KEY("adoptante_id") REFERENCES "Adoptante"("adoptante_id") ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO "Adoptante" ("adoptante_id","nombre","primerApellido","segundoApellido","ciOPasaporte","genero","callePrincipal","entreCalle","yCalle","no","localidad","municipio","provincia") VALUES 
(1,'Harold','Muñoz','Garcés','97082209601',1,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana'),
 (3,'Denia','Toledo','Fredal','97022112345',0,'123','234','345',456,'1ro de Mayo','Consolación del Sur','Pinar del Rio'),
 (4,'Yamile','Garcés','Carrazana','76012712345',0,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana'),
 (5,'Alina','Carrazana','Cedeño','56021012345',0,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana'),
 (6,'Terencio','Garcés','Ramírez','53101512345',1,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana'),
 (8,'Amaury','Ochil','Sosa','73121412345',1,'178','180','Final',17804,'1ro de Mayo','Boyeros','La Habana');

 INSERT INTO "Mascota" ("mascota_id","adoptante_id","nombre","raza","color","edad","genero","peso","ultimaDesparacitacion","ultimaVacunacion","fechaAdopcion") VALUES 
 (1,1,'Jade','Pastor Aleman','Blanco-Gris',15,0,10.2,'2019-12-20','2019-12-18','2010-10-09'),
 (2,5,'Lucas','Cruce','Blanco',10,1,23.4,'2020-03-11','2020-03-11','2020-03-11');

 CREATE INDEX IF NOT EXISTS "adoptante_id" ON "Adoptante" (
	"adoptante_id"	DESC
);
CREATE INDEX IF NOT EXISTS "adoptante_ci" ON "Adoptante" (
	"ciOPasaporte"
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
CREATE VIEW Adopcion(ID, Mascota, Adoptante, Fecha) as 
SELECT Mascota.mascota_id as ID, 
Mascota.nombre as Mascota, 
Adoptante.nombre || ' ' || Adoptante.primerApellido || ' ' || Adoptante.segundoApellido as Adoptante, 
Mascota.fechaAdopcion as Fecha 
FROM Adoptante INNER JOIN Mascota USING(adoptante_id) 
ORDER BY Mascota.adoptante_id;
COMMIT;
