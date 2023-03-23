/* pour reexecuter la BD
connect system/mypass
Drop user Enseignant cascade;
Drop user Etudiant cascade;
create user Etudiant identified by TPEtudiant;
create user Enseignant identified by TPEnseignant;
grant select on Etudiant to Etudiant;
grant select , insert on Enseignant to Enseignant;
grant create session to Enseignant;
grant create session to Etudiant;
@C:\TPBDD\TPscriptEtudiant.sql
*/


----QST-------1--ET--2--ET--3----------TACHE-------1------------------------------
Create USER BDDAdmin identified by TPAdmin; 

Grant all privileges to BDDAdmin ;  

---On connecter avec l'utilisateur "BDDAdmin"
connect BDDAdmin/TPAdmin
---pour cree la table section on doit supprimer la table etudiant donc il faut aussi 
---supprimer la table Etudiant et alors aussi il faut supprimer la table EtudiantUnite et unite
drop table Etudiantunite;
drop table Unite;
drop table Etudiant;
drop table Enseignant;


----QST-------4----------TACHE-------1------CREATION DES TABLE--------------------
---CREATION TABLE Section********************************************************
DROP TABLE  Section;
create table Section(
ID_section  number(30)   NOT NULL,
Nom_section varchar(5)   Not NULL,
Specialite  varchar(5)   Not NULL,
Niveau      varchar(10)  Not NULL,
constraint pk_sec primary key(ID_section)
);
--===============AFFICHAGE TABLE Section==================
desc Section;
--==============================================================


---CREATION TABLE Etudiant********************************************************
drop table  Etudiant;
create table Etudiant(
matricule_etu  number(10)   NOT NULL,
nom_etu        varchar(10)  NOT NULL,
prenom_etu     varchar(10)  NOT NULL,
date_naissance varchar(30)  NOT NULL,
Adresse        varchar(100) NOT NULL,  
ID_section     NUMBER(30)   NOT NULL,
constraint pk_Etud primary key(matricule_etu)
);
-------ajouter la constraine cle etrangere (id_section)
alter table Etudiant add constraint fk_idsec foreign key (ID_section ) references Section(ID_section);
--======afficher la table Enseignant=================
desc Etudiant;
--===================================================



--CREATION TABLE Enseignant**********************************************************
create table Enseignant(
matricule_ens  number(10)   NOT NULL,
nom_ens        varchar(10)  NOT NULL,
prenom_ens     varchar(10)  NOT NULL,
age            number(2)    NOT NULL,
constraint pk_Ens primary key(matricule_ens)
);
--==========AFFICHAGE TABLE Enseignant========================
desc Enseignant;
--=================================================================



--CREATION TABLE unité***************************************************************
create table Unite(
code_Unite    varchar(10)  NOT NULL,
libelle       varchar(30)  NOT NULl,
nbr_heures    number(2)    NOT NULL,
matricule_ens number(10)   NOT NULL,
constraint pk_Unite primary key (code_Unite),
constraint fk_mat foreign key (matricule_ens)
references Enseignant (matricule_ens)
);
--==================AFFICHAGE TABLE Unité====================
desc Unite;a
--===========================================================


--CREATION TABLE EtudiantUnité********************************************************** 
create table EtudiantUnite(
matricule_etu number(10)   NOT NULL,
code_Unite    varchar(10)  NOT NULL,
note_cc       number(4,2), check ( note_cc between 0 and 20),
note_TP       number(4,2), check ( note_TP between 0 and 20),
note_examen   number(4,2), check ( note_examen between 0 and 20),
constraint fk_matetu foreign key (matricule_etu) references Etudiant (matricule_etu),
constraint fk_codeUnite foreign key (code_Unite) references Unite (code_Unite),
constraint pk_etudUnite primary key(matricule_etu,code_Unite)
);
--================AFFICHAGE TABLE EtudiantUnité=======================
desc EtudiantUnite;
--=============================================================



---MODIFICATION  varchar(10)----> varchar(15)
ALTER TABLE Enseignant  DROP  COLUMN age ; 
ALTER TABLE Etudiant    ADD   CONSTRAINT VERIFY_MATRICULE CHECK (matricule_etu > 20190000 AND matricule_etu < 20199999); 
ALTER TABLE Etudiant    MODIFY   prenom_etu  VARCHAR(15) ;
------------------------------------------------------------------------------------------------
desc Etudiant;
------------------------------------------------------------------------------------------------

----QST-------1-------TACHE--------4-------------------------------------------
---remplissage la table Section avec les tuples donnée
insert into Section values(1,'A','L2','ACAD');
insert into Section values(2,'B','L2','ACAD');
insert into Section values(3,'A','L2','ISIL');
insert into Section values(4,'B','L2','ISIL');
insert into Section values(5,'A','M1','SII');
insert into Section values(6,'A','M1','SSI');
--------------------------------------------------------------------------------------
---REMPLIR TABLE Etudiant
insert into Etudiant values(20190001,'BOUSSAI','MOHAMED','12-01-2000','Alger',1);
insert into Etudiant values(20190002,'CHAID','LAMIA','01-10-1999','Batna',2);
insert into Etudiant values(20190003,'BRAHIMI','SOUAD','18-11-2000','Setif',1);
insert into Etudiant values(20190004,'LAMA','SAID','23-05-1999','Oran',2);
--------------------------------------------------------------------------------------
---REMPLIR TABLE Enseignant

insert into Enseignant values(20000001,'HAROUNI','AMINE'); 
insert into Enseignant values(19990011,'FATHI','OMAR'); 
insert into Enseignant values(19980078,'BOUZIDANE','FARAH'); 
insert into Enseignant values(20170015,'ARABI','ZOUBIDA' );
-----------------------------------------------------------------------
---REMPLIR TABLE Unité
insert into Unite values('FEI0001','POO',6,20000001); 
insert into Unite values('FEI0002','BDD',6,19990011); 
insert into Unite values('FEI0003','RESEAU',3,20170015); 
insert into Unite values('FEI0004','SYSTEME',6,19980078);
------------------------------------------------------------------------
---REMPLIR TABLE EtudiantUnité
insert into EtudiantUnite values(20190001,'FEI0001',10,15,9);
insert into EtudiantUnite values(20190002,'FEI0001',20,13,10);
insert into EtudiantUnite values(20190004,'FEI0001',13,17,16); 
insert into EtudiantUnite values(20190002,'FEI0002',10,16,17); 
insert into EtudiantUnite values(20190003,'FEI0002',9,8,15);
insert into EtudiantUnite values(20190004,'FEI0002',15,9,20); 
insert into EtudiantUnite values(20190002,'FEI0004',12,18,14);
insert into EtudiantUnite values(20190003,'FEI0004',17,12,15);
insert into EtudiantUnite values(20190004,'FEI0004',12,13,20);

--==================AFFICHAGE APRES REMPLISSAGE=============================    
-----------------------------TABLE Etudiant------------------------------------------------
SELECT * from Etudiant;
-----------------------------TABLE Enseignant----------------------------------------------
SELECT * from Enseignant;
-----------------------------TABLE EtudiantUnite-------------------------------------------
SELECT * from EtudiantUnite;
-----------------------------TABLE Unite---------------------------------------------------
SELECT * from Unite;
-----------------------------TABLE Section-------------------------------------------------
SELECT * from Section;



-------------------------------------------------------------------------------------------
--=============================AJOUT ATTRIBUT age
alter table Enseignant add age number(2);
select * from Enseignant;
-------------------------------------------------------------------------------------------

----QST-------2-------TACHE--------4-----------------------------------
--=====================Augmenter la note_CC de 2 pour tous les étudiants dont le nom commence par 'B' 
update EtudiantUnite
set note_cc = note_cc + 2
where matricule_etu 
IN (select matricule_etu 
    from Etudiant 
    where nom_etu like'B%');
-----------------------------------------------------------------------------------------
---=====================afficher les notes_cc des etudiants dont le nom commance par B apres l'ajout de 2 a leurs note de cc
select E.nom_etu , EU.note_cc
from Etudiant E , EtudiantUnite EU
where E.matricule_etu=EU.matricule_etu       AND      nom_etu like 'B%';
---------------------------------------------------------------------------------

----QST-------3-------TACHE--------4-------------------------------------
--==================Remettre toutes les notes d'examen de l'unité "SYSTEME" à 0 pour tous les étudiants.
UPDATE EtudiantUnite set note_examen=0 
where code_Unite 
IN (select code_Unite 
    from Unite 
    where libelle='SYSTEME');
------------------------------------------------------------------------------
--==================afficher les note d'examen au etudians qui ont inscrit a "SYSTEME"
select U.libelle , EU.note_examen 
from Unite U ,EtudiantUnite EU 
where
EU.code_Unite=U.code_Unite       AND      libelle='SYSTEME';

--Tâche 5
----QST-------1-------TACHE--------5-------------------------------------
--Afficher les noms et prénoms des étudiants ayant obtenus des notes d'examens égales à 20. 
select nom_etu,prenom_etu from Etudiant where matricule_etu 
not IN (select matricule_etu 
        from EtudiantUnite 
        where note_examen=20);
-----------------------------------------------------------------------------------
----QST-------2-------TACHE--------5---------------------------------------
--Afficher les noms et prénoms des étudiants qui ne sont pas inscrits dans l'unité « POO »
SELECT nom_etu, prenom_etu
FROM Etudiant
WHERE matricule_etu  NOT IN 
(SELECT  EU.matricule_etu 
 FROM EtudiantUnite EU, Unite U 
 WHERE EU.code_unite = U.code_Unite AND libelle='POO');
-------------------------------------------------------------------------------------------
----QST-------3-------TACHE--------5-------------------------------------
--Afficher les libellés des unités d'enseignement dont aucun étudiant n'est inscrit
SELECT libelle FROM Unite 
MINUS 
SELECT libelle FROM Unite 
WHERE code_Unite IN (SELECT code_Unite FROM EtudiantUnite);
--------------------------------------------------------------------------------------------
----QST-------4-------TACHE--------5---------------------------------------
--Afficher pour chaque étudiant, son nom, son prénom sa moyenne par unité d'enseignement ainsi que le libellé de l'unité
SELECT E.nom_etu,  E.prenom_etu,  U.libelle,((EU.note_cc + EU.note_TP + EU.note_examen)/3) 
fROM Etudiant E, Unite U, EtudiantUnite EU 
WHERE E.matricule_etu = EU.matricule_etu      AND     EU.code_Unite = U.code_Unite;



------------------------------------------------------------------------------
------------------TACHE--------3----------------------------------------------
--create user Etudiant identified by TPEtudiant;
GRANT SELECT ON BDDAdmin.Etudiant to Etudiant;
--create user Enseignant identified by TPEnseignat;
GRANT SELECT,insert ON BDDAdmin.Enseignant to Enseignant;
------------------TACHE--------3---------------------------------------------- 
--create session pour qu'on peut connecter
--create et donner á l'user  enseignant le privilege 
grant create session to Enseignant;
--create et donner á l'user Etudiant le privilege 
grant create session to Etudiant;