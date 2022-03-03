select * from board;

DROP TABLE board;
create table board(
	num number(5) primary key,
	userid VARCHAR2(30),
	pass varchar2(30), 
	name varchar2(30),
	email varchar2(30),
	title varchar2(50),
	content varchar2(1000),
	readcount number(4) default 0,  
	writedate date default sysdate, 
	replycnt number(3) default 0,
	imgfilename varchar2(50)
);

insert into board(num, userid, email, pass, title, content)
values( board_seq.nextVal, 'hong', 'abc@naver.com', '1234', '泥ル갑臾몄엯�땲�떎', '諛섍컩�뒿�땲�떎. �븵�쑝濡� 留롮쑝 寃⑸젮�� 吏��룄�렪�떖 遺��긽�뱶由쎈땲�떎.' );
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'somi', 'adddnaver.com', '1234', '寃뚯떆�뙋 媛쒖꽕', '異뺥븯�뱶由쎈땲�떎.  臾닿턿�븳 諛쒖쟾�쓣 湲곗썝�븷猿섏슂');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'light', 'bnbn@naver.com', '1234', '�뤌吏�怨⑤쭏�쓣', '�뤌吏� �궪寃뱀궡�씠 留쏆엳�뒿�땲�떎');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hana', 'hana@daum.net', '1234', '2022�뀈 寃⑥슱' , '紐몄떆 異붿슱爰� 媛숈븘�슂... �떎�뱾 嫄닿컯 �쑀�쓽 �븯�꽭�슂....');
insert into board(num, userid, email, pass, title, content)
values(board_seq.nextVal, 'hana', 'hana@daum.net', '1234', '肄붾줈�굹諛붿씠�윭�뒪' ,	'�궗�쉶�쟻 嫄곕━�몢湲�4�떒怨� .... 諛깆떊�젒醫� �벑�벑�벑');


select * from member;
create table member(
	name varchar2(30),
	userid varchar2(30) primary key,
	pwd varchar2(30),   -- 寃뚯떆臾쇱쓽 �닔�젙 �궘�젣瑜� �쐞�븳 鍮꾨�踰덊샇
	email varchar2(30),
	phone varchar2(30),
	admin char(1) default 0,
	zip_num varchar2(10),
	address varchar2(100)
);


select * from reply;
create table reply(
	num number(7) primary key,    -- �뙎湲� �닚踰�
	boardnum number(5),                --  �뙎湲��쓽 �빐�떦 寃뚯떆臾� 踰덊샇
	userid varchar2(20),                   -- �뙎援� �벐�땲
	writedate date default sysdate, -- �옉�꽦�씪
	content varchar2(1000)               -- �옉�꽦 �궡�슜
);

create sequence board_seq start with 1 increment by 1;
create sequence reply_seq start with 1 increment by 1;

SELECT * FROM reply;

