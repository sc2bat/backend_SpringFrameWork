DROP TABLE board;

SELECT * FROM springBoard;

CREATE TABLE springBoard(
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

CREATE SEQUENCE sb_seq INCREMENT BY 1 START WITH 1 NOCACHE;


INSERT INTO springBoard(num, userid, email, pass, title, content)
	VALUES(sb_seq.nextVal, 'hong', 'abc@naver.com', '1234', '보쌈 쌉맛집 발견', '상대방에 대한 배려는 네티켓의 기본입니다.게시물에 상관없는 답글이나 추천유도성 답글을 달지 마세요.');
INSERT INTO springBoard(num, userid, email, pass, title, content)
	VALUES(sb_seq.nextVal, 'qwer', 'abc@naver.com', '1234', '중국집', '상대방에 대한 배려는 네티켓의 기본입니다.게시물에 상관없는 답글이나 추천유도성 답글을 달지 마세요.');
INSERT INTO springBoard(num, userid, email, pass, title, content)
	VALUES(sb_seq.nextVal, 'asdf', 'abc@naver.com', '1234', '오징어 뽁음', '상대방에 대한 배려는 네티켓의 기본입니다.게시물에 상관없는 답글이나 추천유도성 답글을 달지 마세요.');
INSERT INTO springBoard(num, userid, email, pass, title, content)
	VALUES(sb_seq.nextVal, 'zxcv', 'abc@naver.com', '1234', '고기빵', '상대방에 대한 배려는 네티켓의 기본입니다.게시물에 상관없는 답글이나 추천유도성 답글을 달지 마세요.');
INSERT INTO springBoard(num, userid, email, pass, title, content)
	VALUES(sb_seq.nextVal, 'vbnm', 'abc@naver.com', '1234', '처음가본 투다리', '상대방에 대한 배려는 네티켓의 기본입니다.게시물에 상관없는 답글이나 추천유도성 답글을 달지 마세요.');
	
	
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
	boardnum NUMBER(5),
	userid VARCHAR2(30),
	writedate DATE DEFAULT sysdate,
	content VARCHAR2(1000)
);
CREATE SEQUENCE sr_seq INCREMENT BY 1 START WITH 1 NOCACHE;
SELECT * FROM springReply;






SELECT * FROM springBoard;
SELECT * FROM springMember;
SELECT * FROM springReply;

	