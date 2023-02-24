#1
select * from Worker where year(joining_date)=2014 and month(joining_date)=2;

#2
select WORKER_TITLE,AFFECTED_FROM ,count(WORKER_TITLE) as counter from Title
group by WORKER_TITLE ,AFFECTED_FROM having count(WORKER_TITLE)>1 ;

#3
create table update_worker as select  * from Worker where Worker.WORKER_ID >3;
    INSERT INTO update_worker
(WORKER_ID, FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT) VALUES
                                                                         (009, 'Ali', 'Alavi', 110000, '15-02-20 09.00.00', 'HR'),
                                                                         (010, 'Ali', 'Akbari', 81000, '15-06-11 09.00.00', 'Admin'),
                                                                         (011, 'Hasan', 'Alavi', 310000, '15-02-20 09.00.00', 'HR'),
                                                                         (012, 'Reza', 'Akbari', 510000, '15-02-20 09.00.00', 'Admin'),
                                                                         (013, 'Hasan', 'Akbari', 510000, '15-06-11 09.00.00', 'Admin');

#4
select Worker.WORKER_ID,FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT from Worker where exists(select update_worker.WORKER_ID from update_worker where update_worker.WORKER_ID=Worker.WORKER_ID );

#5
select update_worker.WORKER_ID,FIRST_NAME, LAST_NAME, SALARY, JOINING_DATE, DEPARTMENT from update_worker where  not exists(select Worker.WORKER_ID WORKER_ID from Worker where Worker.WORKER_ID=update_worker.WORKER_ID );

#6
SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY SALARY DESC ) AS rownumber
from Worker) rT
WHERE rownumber =5;

#7
SELECT* from Worker  where  WORKER_ID between 0 and (select count(*)/2 from Worker);

#8
select DEPARTMENT, count(*) num from Worker group by DEPARTMENT having num<5;

#9
select DEPARTMENT, first_name, last_name, SALARY from Worker where SALARY in (select max(SALARY) from Worker group by DEPARTMENT ) ;
