desc emaillist;

-- insert
insert into emaillist values(null, '길동', '홍', 'example@exmaple.com');
insert into emaillist values(null, '길동', '가', 'example1@exmaple.com');
insert into emaillist values(null, '길동', '나', 'example2@exmaple.com');
insert into emaillist values(null, '길동', '다', 'example3@exmaple.com');
insert into emaillist values(null, '길동', '라', 'example4@exmaple.com');

-- list
select no, first_name, last_nguestbookame, email from emaillist order by no desc;


-- delete
delete from emaillist where no = 9;