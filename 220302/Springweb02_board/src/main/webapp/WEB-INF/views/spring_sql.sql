DROP TABLE board;

SELECT * FROM board;

CREATE TABLE board(
	num NUMBER(5) PRIMARY KEY,
	userid VARCHAR2(30),
	pass VARCHAR2(30),
	name VARCHAR2(30),
	email VARCHAR2(30),
	title VARCHAR2(50),
	content VARCHAR2(1000),
	readcount NUMBER(4) DEFAULT 0,
	writedate DATE DEFAULT sysdate,
	replycnt NUMBER(3) DEFAULT 0,
	imgfilename VARCHAR2(50)
);

CREATE SEQUENCE board_seq INCREMENT BY 1 START WITH 1 NOCACHE;


INSERT INTO board(num, userid, email, pass, title, content)
	VALUES(board_seq.nextVal, 'hong', 'abc@naver.com', '1234', '첫방문입니다', '가나다라마바사');
INSERT INTO board(num, userid, email, pass, title, content)
	VALUES(board_seq.nextVal, 'qwer', 'abc@naver.com', '1234', '첫방문입니다', '가나다라마바사');
INSERT INTO board(num, userid, email, pass, title, content)
	VALUES(board_seq.nextVal, 'asdf', 'abc@naver.com', '1234', '첫방문입니다', '가나다라마바사');
INSERT INTO board(num, userid, email, pass, title, content)
	VALUES(board_seq.nextVal, 'zxcv', 'abc@naver.com', '1234', '첫방문입니다', '가나다라마바사');
INSERT INTO board(num, userid, email, pass, title, content)
	VALUES(board_seq.nextVal, 'vbnm', 'abc@naver.com', '1234', '첫방문입니다', '가나다라마바사');
	
	
CREATE TABLE springMember(
	userid VARCHAR2(30) PRIMARY KEY,
	pass VARCHAR2(30),
	name VARCHAR2(30),
	email VARCHAR2(30),
	phone VARCHAR2(30),
	admin CHAR(1) DEFAULT 0, 
	zip_num VARCHAR2(30),
	address VARCHAR2(100)
);
	

SELECT * FROM springMember;

INSERT INTO springMember(userid, pass, name, email, phone)
	VALUES('hong', '1234', '홍길동', 'test@naver.com', '010-3333-3333');

CREATE TABLE springReply(
	num NUMBER(7) PRIMARY KEY,
	boardnum NUMBER(5) REFERENCES KEY,
	userid VARCHAR2(30),
	writedate DATE DEFAULT sysdate,
	content VARCHAR2(1000)
);
CREATE SEQUENCE springreply_seq INCREMENT BY 1 START WITH 1 NOCACHE;
SELECT * FROM springReply;






SELECT * FROM board;
SELECT * FROM springMember;
SELECT * FROM springReply;

	