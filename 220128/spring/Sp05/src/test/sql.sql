-- Oracle 11 Spring-Oracle xe

CREATE TABLE student(
	sNum VARCHAR2(20),
	sId VARCHAR2(20),
	sPw VARCHAR2(20),
	sName VARCHAR2(20),
	sAge NUMBER(3),
	sGender VARCHAR2(20),
	sMajor VARCHAR2(80)
);

SELECT * FROM student;