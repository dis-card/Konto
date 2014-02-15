
CREATE TABLE  Customer  (
   firstName  VARCHAR(50),
   lastName  VARCHAR(50),
   dateOfBirth  DATE,
   streetName  VARCHAR(25),
   streetNumber  INTEGER,
   city  VARCHAR(25),
   pin  CHAR(6),
   id  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  PRIMARY KEY  ( id )
);



CREATE TABLE  City  (
   acronym  VARCHAR(4) NOT NULL,
   name  VARCHAR(20),
  PRIMARY KEY  ( acronym )
);

CREATE TABLE  RegularSavingsAccount  (
   customerId  INTEGER,
   accountNo  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   cityAcronym  VARCHAR(4),
   initialDeposit  DOUBLE,
   openingDate  DATE,
  PRIMARY KEY  ( accountNo )
);

CREATE TABLE  SalarySavingsAccount  (
   customerId  INTEGER,
   accountNo  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   cityAcronym  VARCHAR(4),
   initialDeposit  DOUBLE,
   openingDate  DATE,
  PRIMARY KEY  ( accountNo )
);


ALTER TABLE  RegularSavingsAccount  ADD CONSTRAINT  RegularSavingsAccount_fk1  FOREIGN KEY ( customerId ) REFERENCES Customer( id );
ALTER TABLE  RegularSavingsAccount  ADD CONSTRAINT  RegularSavingsAccount_fk2  FOREIGN KEY ( cityAcronym ) REFERENCES City( acronym );
ALTER TABLE  SalarySavingsAccount  ADD CONSTRAINT  SalarySavingsAccount_fk1  FOREIGN KEY ( customerId ) REFERENCES Customer( id );
ALTER TABLE  SalarySavingsAccount  ADD CONSTRAINT  SalarySavingsAccount_fk2  FOREIGN KEY ( cityAcronym ) REFERENCES City( acronym );

insert into vikash.city values ('BLR','Bangalore');
insert into vikash.city values ('MUM','Mumbai');
