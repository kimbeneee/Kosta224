create table mvc_member(
	id varchar2(100) primary key,
	password varchar2(100) not null,
	name varchar2(100) not null,
	address varchar2(100) not null,
	birthday date not null,
	regdate date not null

)

drop table mvc_member;
select * from mvc_member;

create sequence mvc_member_seq;
drop sequence mvc_member_seq;
insert into mvc_member(id,password,name,address,birthday,regdate) values('java','1234','김윤구','오리',TO_DATE('1994.02.02','YYYY.MM.DD'),sysdate);
insert into mvc_member(id,password,name,address,birthday,regdate) values('java2','1','강하늘','오리',TO_DATE('1995.02.02','YYYY.MM.DD'),sysdate);
insert into mvc_member(id,password,name,address,birthday,regdate) values('java3','12','이강인','오리',TO_DATE('1996.02.02','YYYY.MM.DD'),sysdate);
insert into mvc_member(id,password,name,address,birthday,regdate) values('java4','123','아이유','오리',TO_DATE('1997.02.02','YYYY.MM.DD'),sysdate);

-- findMemberById 
select id,name,address,to_char(birthday,'YYYY.MM.DD'),to_char(regdate,'YYYY.MM.DD HH24:MM:SS') from mvc_member where id='java';

-- login sql : id와 password가 일치할때만 조회가 된다
select name,address,to_char(birthday,'YYYY.MM.DD') from mvc_member where id='java' and password='1234';

-- findMemberListByAddress
select id,name,to_char(birthday,'YYYY.MM.DD') from mvc_member where address='오리';

update mvc_member SET password='12',name='손흥민',address='용인',birthday=to_date('2021.10.22','YYYY.MM.DD') where id='java';


-- id 중복확인 , 존재하면 1 , 없으면 0
select count(*) from mvc_member where id='java';
select count(*) from mvc_member where id='kkkk';
















