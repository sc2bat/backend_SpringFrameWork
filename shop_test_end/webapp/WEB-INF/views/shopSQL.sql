DROP TABLE shopMem CASCADE CONSTRAINTS;
DROP TABLE shopCart CASCADE CONSTRAINTS;
DROP TABLE shopAdmin CASCADE CONSTRAINTS;

SELECT * FROM shopMem; -- member table
SELECT * FROM shopproduct; -- product table
SELECT * FROM shopCart; -- cart table
SELECT * FROM shopOrder; -- order table
SELECT * FROM shopOrderDetail; -- orderDetail table
SELECT * FROM shopQna; -- qna table
SELECT * FROM shopAdmin; -- admin table
SELECT * FROM address; -- address table
SELECT * FROM sc_view; -- cart view
SELECT * FROM so_view; -- order view

SELECT Pseq.nextVal, Pseq.currVal FROM dual;-- 63번부터시작
SELECT Cseq.nextVal, Cseq.currVal FROM dual;
SELECT Oseq.nextVal, Oseq.currVal FROM dual;
SELECT Odseq.nextVal, Odseq.currVal FROM dual;
SELECT Qseq.nextVal, Qseq.currVal FROM dual;

CREATE TABLE shopMem(
	mid VARCHAR2(50) NOT NULL PRIMARY KEY,
	pwd VARCHAR2(50),
	name VARCHAR2(50),
	email VARCHAR2(50),
	phone VARCHAR2(50),
	znum VARCHAR2(50),
	addr1 VARCHAR2(100),
	addr2 VARCHAR2(100),
	useyn CHAR(1) DEFAULT 'y',
	mIndate DATE DEFAULT sysdate
);
SELECT * FROM shopMem;

CREATE TABLE product(
	pseq number(5) NOT NULL,
	name varchar2(100) NOT NULL,
	kind char(1) NOT NULL,
	price1 number(7),   -- 원가
	price2 number(7),   -- 판매가
	price3 number(7),   -- 마진 
	content varchar2(1000),
	image varchar2(50),
	useyn char(1) DEFAULT 'y',     -- 상품 판매 유효 여부
	bestyn char(1) DEFAULT 'n',   -- 베스트상품 진열 여부
	indate date DEFAULT sysdate,    -- 등록일
	PRIMARY KEY (pseq)
);
SELECT * FROM shopproduct;

DROP SEQUENCE Pseq;
CREATE SEQUENCE Pseq INCREMENT BY 1 START WITH 63 NOCACHE;

CREATE TABLE shopCart(
	cseq NUMBER(10) PRIMARY KEY,
	cid VARCHAR2(50) REFERENCES shopMem(mid),
	pseq NUMBER(10) REFERENCES shopproduct(pseq),
	quantity NUMBER(10) DEFAULT 1,
	cResult CHAR(1) DEFAULT '1',
	cIndate DATE DEFAULT sysdate
);
SELECT * FROM shopCart;

DROP SEQUENCE Cseq;
CREATE SEQUENCE Cseq INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE shopOrder(
	oseq NUMBER(10) PRIMARY KEY,
	oid VARCHAR2(50) REFERENCES shopMem(mid),
	oIndate DATE DEFAULT sysdate
);
SELECT * FROM shopOrder;

DROP SEQUENCE Oseq;
CREATE SEQUENCE Oseq INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE shopOrderDetail(
	odseq NUMBER(10) PRIMARY KEY,
	oseq NUMBER(10) REFERENCES shopOrder(oseq),
	pseq NUMBER(10) REFERENCES shopproduct(pseq),
	quantity NUMBER(10) DEFAULT 1,
	odResult CHAR(1) DEFAULT '1'
);
SELECT * FROM shopOrderDetail;

DROP SEQUENCE Odseq;
CREATE SEQUENCE Odseq INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE shopQna(
	qseq NUMBER(10) PRIMARY KEY,
	qSubject VARCHAR2(500),
	qContent VARCHAR2(1000),
	reply VARCHAR2(1000),
	qid VARCHAR2(50) REFERENCES shopMem(mid),
	qResult CHAR(1) DEFAULT '1',
	qIndate DATE DEFAULT sysdate
	
); 
SELECT * FROM shopQna;

DROP SEQUENCE Qseq;
CREATE SEQUENCE Qseq INCREMENT BY 1 START WITH 1 NOCACHE;

CREATE TABLE shopAdmin(
	adminId VARCHAR2(50) NOT NULL PRIMARY KEY,
	adminPwd VARCHAR2(50) NOT NULL,
	adminName VARCHAR2(50) NOT NULL,
	adminPhone  VARCHAR2(50),
	adminEmail VARCHAR2(50)
);
SELECT * FROM shopAdmin;

CREATE TABLE address (
	zip_num VARCHAR2(7) NOT NULL,
	sido VARCHAR2(30) NOT NULL,
	gugun VARCHAR2(30) NOT NULL,
	dong VARCHAR2(100) NOT NULL,
	zip_code VARCHAR2(30),
	bunji VARCHAR2(30) 
);
SELECT * FROM address; 

CREATE OR REPLACE VIEW sc_view
AS SELECT c.cseq, c.cid, m.name AS mname, p.pseq, p.name AS pname, 
	c.quantity, p.price2, c.cResult, c.cIndate
FROM shopCart c, shopMem m, shopproduct p
WHERE c.cid = m.mid AND c.pseq = p.pseq;
SELECT * FROM sc_view; 

CREATE OR REPLACE VIEW so_view
AS SELECT od.odseq, o.oseq, o.oIndate, m.mid, m.name AS mname, m.znum, m.addr1, m.addr2, m.phone, 
	p.pseq, p.name AS pname, p.price2, od.quantity, od.odResult
FROM shopOrderDetail od, shopOrder o, shopMem m, shopproduct p
WHERE o.oseq = od.oseq AND o.oid = m.mid AND od.pseq = p.pseq;
SELECT * FROM so_view; 

CREATE OR REPLACE VIEW newP_view
AS SELECT * FROM (
	SELECT * FROM shopproduct WHERE useyn='y' ORDER BY indate DESC) WHERE ROWNUM <=4;
SELECT * FROM newP_view; 

CREATE OR REPLACE VIEW bestP_view
AS SELECT * FROM (
	SELECT * FROM shopproduct WHERE bestyn='y' ORDER BY indate DESC) WHERE ROWNUM <=4;
SELECT * FROM bestP_view; 
	

INSERT INTO shopAdmin(adminId, adminPwd, adminName, adminPhone, adminEmail)
	VALUES('admin', '1234', 'admin1', '010-1111-1111', 'admin@naver.com');
SELECT * FROM (
SELECT * FROM (
SELECT ROWNUM AS rn, p.* FROM shopproduct p ORDER BY pseq DESC
) WHERE rn >= 1
) WHERE rn <= 30;

SELECT * FROM shopMem; -- member table
SELECT * FROM shopproduct; -- product table
SELECT * FROM shopCart; -- cart table
SELECT * FROM shopOrder; -- order table
SELECT * FROM shopOrderDetail; -- orderDetail table
SELECT * FROM shopQna; -- qna table
SELECT * FROM shopAdmin; -- admin table
SELECT * FROM address; -- address table
SELECT * FROM sc_view; -- cart view
SELECT * FROM so_view; -- order view
SELECT * FROM newP_view; -- new product 4 view
SELECT * FROM bestP_view; -- best product 4 view

DELETE FROM shopOrder;
DELETE FROM shopOrderDetail;