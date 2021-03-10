desc guestbook;

-- insert
insert into guestbook values(null, '관리자', '1234', '공지사항입니다', now());

-- select
select no, name, date_format(reg_date, '%Y-%m-%d') as date, contents from guestbook order by reg_date desc;

-- delete
delete from guestbook where no = 1 and password = '1234';

-- drop table guestbook;
