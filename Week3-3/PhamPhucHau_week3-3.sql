--cAU 1 
SELECT * FROM DEPARTMENTS;
CREATE OR REPLACE PROCEDURE dept_info (id1 IN NUMBER,dept out departments%Rowtype  ) 
IS
BEGIN 
SELECT * into dept   FROM DEPARTMENTS WHERE DEPARTMENTS.department_id=id1;
dbms_output.put_line(dept.department_name);
EXCEPTION
WHEN NO_DATA_FOUND 
THEN 
dbms_output.put_line('Không có d? li?u tr? v?');
WHEN OTHERS THEN
dbms_output.put_line('?ã có l?i x?y ra');
END;
DECLARE 
dept  departments%Rowtype;
BEGIN
 dept_info(10,dept);   
dbms_output.Put_line('Department Name '||dept.department_Name||' ID: ' || dept.department_id);
END;
--cAU 2 
CREATE OR REPLACE PROCEDURE add_job (id IN VARCHAR2, job_name IN VARCHAR2)
AS 
BEGIN 
insert into JOBS values (id, job_name,'0','0');
dbms_output.put_line('Insert job v?i ID: '||id||' và name: '||job_name||'Thành công');
EXCEPTION
WHEN DUP_VAL_ON_INDEX THEN
BEGIN
dbms_output.put_line('ID B? TRÙNG');
ROLLBACK;
END;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END ;
-- Test
/*
BEGIN 
ADD_JOB('2','DEV');
END;
select * from jobs;*/
--cAU 3 
/*
SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID=145;
update employees 
set commission_pct=(commission_pct*(2))
where Employee_id=145;
*/
CREATE OR REPLACE PROCEDURE UPDATE_COMM (EMPLOYEE_ID_IN IN NUMBER)
AS 
BEGIN 
update employees 
set commission_pct=(commission_pct*(1.05))
where Employee_id=EMPLOYEE_ID_IN;
dbms_output.put_line('UPDATE HOA H?NG THÀNH CÔNG CÓ EMPLOYEE V?I ID: '||EMPLOYEE_ID_IN);
EXCEPTION
WHEN NO_DATA_FOUND 
THEN 
dbms_output.put_line('Không có EMPLOYEE V?I ID: '||EMPLOYEE_ID_IN);
WHEN OTHERS
THEN
dbms_output.put_line('L?I KHÔNG XÁC ??NH');
END;
-- Test
/*
BEGIN
UPDATE_COMM(145);
END;*/
--CAU 4
--insert into  employees values ()
CREATE OR REPLACE PROCEDURE add_emp (EMPLOYEE_ID in EMPLOYEES.EMPLOYEE_ID%TYPE, first_name in EMPLOYEES.first_name%TYPE, last_name in EMPLOYEES.last_name%TYPE, email in EMPLOYEES.email%TYPE, phone_number IN EMPLOYEES.phone_number%TYPE, hire_date IN EMPLOYEES.hire_date%TYPE,job_id IN  EMPLOYEES.job_id%TYPE,salary IN EMPLOYEES.SALARY%TYPE,commission_pct IN EMPLOYEES.COMMISSION_PCT%TYPE,manager_id IN EMPLOYEES.MANAGER_ID%TYPE,department_id IN EMPLOYEES.DEPARTMENT_ID%TYPE)
AS 
BEGIN 
INSERT INTO EMPLOYEES VALUES(EMPLOYEE_ID,first_name,last_name,email,phone_number,hire_date,job_id,salary,commission_pct,manager_id,department_id);
DBMS_OUTPUT.PUT_LINE('THÊM EMPLOYEE THÀNH CÔNG V?I ID: '||EMPLOYEE_ID);
EXCEPTION 
WHEN DUP_VAL_ON_INDEX THEN
BEGIN
dbms_output.put_line('ID B? TRÙNG');
ROLLBACK;
END;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END;
--TEST
/*
SELECT * FROM EMPLOYEES WHERE EMPLOYEES.EMPLOYEE_ID=208;
BEGIN
ADD_EMP('207',	'John',	'Russell',	'JRUSSEL@GMAIL.COM',	'011.44.1344.429268',	'01-OCT-04',	'SA_MAN',	'14000',	'0.8',	'100',	'80');
END;*/
--CAU 5
-- delete employees where employee_id
CREATE OR REPLACE PROCEDURE DELETE_EMP(ID IN EMPLOYEES.EMPLOYEE_ID%TYPE)
IS
 NO_ROW_FOUND EXCEPTION;
BEGIN 
DELETE EMPLOYEES WHERE EMPLOYEE_ID=ID;

IF SQL%NOTFOUND =TRUE
THEN 
RAISE NO_ROW_FOUND;
END IF;
dbms_output.put_line('XÓA THÀNH CÔNG EMPLOYEE V?I ID: '||ID);
EXCEPTION 
WHEN NO_ROW_FOUND THEN
BEGIN
dbms_output.put_line('KHÔNG TÌM TH?Y ID: '||ID);
ROLLBACK;
END;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END;
-- TEST
/*
BEGIN
DELETE_EMP(207);
END;*/
--CAU 6
CREATE OR REPLACE PROCEDURE find_emp (LISTEMP OUT EMPLOYEES%ROWTYPE )
IS
BEGIN 
SELECT * INTO LISTEMP FROM EMPLOYEES JOIN JOBS ON EMPLOYEES.JOB_ID=JOBS.JOB_ID  WHERE SALARY>JOBS.min_salary and SALARY <JOBS.max_salary;
EXCEPTION
WHEN NO_DATA_FOUND
THEN
DBMS_OUTPUT.PUT_LINE('Không có d? li?u tr? v?');
WHEN OTHERS
THEN
DBMS_OUTPUT.PUT_LINE('?Ã CÓ L?I X?Y RA ');
END;
/*
DECLARE 
LISTEMP EMPLOYEES%ROWTYPE;
BEGIN
find_emp(LISTEMP);

DBMS_OUTPUT.PUT_LINE(LISTEMP.FIRST_NAME||LISTEMP.EMAIL);
END;
*/
--cAU 7


CREATE OR REPLACE PROCEDURE update_comm
AS 
BEGIN 
update employees 
set salary=salary + 200
where employee_id in(select employee_id from employees where (Sysdate - HIRE_DATE)>=730 );
update employees 
set salary=salary + 100
where employee_id in(select employee_id from employees where (Sysdate - HIRE_DATE)<730 and (Sysdate - HIRE_DATE)>365  );
update employees 
set salary=salary + 50
where employee_id in(select employee_id from employees where (Sysdate - HIRE_DATE)=365  );
END;
--cAU 8
--select h.* from job_history h join employees e on h.employee_id=e.employee_id where h.employee_id=102 ;
CREATE OR REPLACE PROCEDURE job_his (id IN JOB_HISTORY.EMPLOYEE_ID%TYPE)
AS 
CURSOR C1 
IS 
select h.* from job_history h join employees e on h.employee_id=e.employee_id where h.employee_id=id ;
BEGIN 
FOR HISTORY_FOUND IN C1
LOOP
dbms_output.put_line(HISTORY_FOUND.EMPLOYEE_ID || 'START DATE' ||'START DATE: '||  HISTORY_FOUND.START_DATE||' END DATE: '||HISTORY_FOUND.END_DATE||'JOBID: '||HISTORY_FOUND.JOB_ID||'DEPARTMENTID: '||HISTORY_FOUND.DEPARTMENT_ID );
  END LOOP;
END;
-- TEST
/*
BEGIN
job_his(101);
END;
SELECT * FROM JOB_HISTORY;
*/
-- Bai 2 FUNCTION
-- Cau 1 
CREATE OR REPLACE FUNCTION  SUM_SALARY(ID IN EMPLOYEES.DEPARTMENT_ID%TYPE )
RETURN NUMBER
IS 
SUM_SALARY NUMBER;
BEGIN
select   SUM(salary) INTO SUM_SALARY from employees WHERE DEPARTMENT_ID=ID group by DEPARTMENT_ID;
RETURN SUM_SALARY;
END;
-- TEST
/*
SELECT * FROM DEPARTMENTS;
DECLARE 
SUM NUMBER;
BEGIN
DBMS_OUTPUT.PUT_LINE(SUM_SALARY('60'));
END;
*/
-- Cau 2 

CREATE OR REPLACE FUNCTION  NAME_COM(ID IN COUNTRIES.COUNTRY_ID%TYPE )
RETURN VARCHAR2
IS 
NAME VARCHAR2(40);
BEGIN
select  COUNTRY_NAME  INTO NAME from COUNTRIES WHERE COUNTRY_ID=ID;
RETURN NAME;
EXCEPTION 
WHEN NO_DATA_FOUND
THEN
DBMS_OUTPUT.PUT_LINE('KhÔNG TÌM TH?Y COUNTRY V?I MÃ');
WHEN OTHERS
THEN
DBMS_OUTPUT.PUT_LINE('?Ã CÓ L?I X?Y RA ');
END;
-- TEST
/*
SELECT * FROM COUNTRIES;
BEGIN 
DBMS_OUTPUT.PUT_LINE(NAME_COM('US'));
END;*/
-- Cau 3 
--select employee_id, ((salary*12)+ (salary*12*commission_pct)) as luong from employees;
CREATE OR REPLACE FUNCTION  ANNUAL_COMP(SALARY IN NUMBER, commission_pct IN NUMBER)
RETURN NUMBER
IS 
ANNUAL_COMP_OUT NUMBER;
BEGIN
select  ((salary*12)+ (salary*12*commission_pct)) INTO ANNUAL_COMP_OUT FROM DUAL;
RETURN ANNUAL_COMP_OUT;
END;
-- TEST
--DECLARE 
--SUM NUMBER;
-- BEGIN
DBMS_OUTPUT.PUT_LINE(AVG_SALARY('60'));
END;
select * from employees;
-- Cau 4
-- select department_id,avg(salary) from employees group by department_id;
CREATE OR REPLACE FUNCTION  AVG_SALARY(ID IN EMPLOYEES.DEPARTMENT_ID%TYPE )
RETURN NUMBER
IS 
AVG_SALARY NUMBER;
BEGIN
select   AVG(salary) INTO AVG_SALARY from employees WHERE DEPARTMENT_ID=ID group by DEPARTMENT_ID;
RETURN AVG_SALARY;
END;
-- Cau 5 
--select MONTHS_BETWEEN(sysdate,hire_date) from employees;
CREATE OR REPLACE FUNCTION  TIME_WORK(ID IN EMPLOYEES.EMPLOYEE_ID%TYPE )
RETURN NUMBER
IS 
 TIME_WORK_OUT NUMBER;
BEGIN
select MONTHS_BETWEEN(sysdate,hire_date) INTO TIME_WORK_OUT  from employees WHERE EMPLOYEES.EMPLOYEE_ID=ID ;
RETURN TIME_WORK_OUT;
END;
-- TEST
/*
DECLARE 
BEGIN
DBMS_OUTPUT.PUT_LINE(TIME_WORK('206'));
END;
select * from employees;
*/
-- Bai 3 
-- Cau 1 
CREATE OR REPLACE TRIGGER HIRE_DAY_INSERT
BEFORE  INSERT OR UPDATE ON EMPLOYEES
FOR EACH ROW
DECLARE 
CHECK1 EXCEPTION;
BEGIN
IF :NEW.HIRE_DATE>=SYSDATE THEN
RAISE CHECK1;
ELSE
INSERT INTO EMPLOYEES VALUES(:NEW.EMPLOYEE_ID,:NEW.first_name,:NEW.last_name,:NEW.email,:NEW.phone_number,:NEW.hire_date,:NEW.job_id,:NEW.salary,:NEW.commission_pct,:NEW.manager_id,:NEW.department_id);
END IF;
EXCEPTION 
WHEN DUP_VAL_ON_INDEX THEN
BEGIN
dbms_output.put_line('ID B? TRÙNG');
ROLLBACK;
END;
WHEN CHECK1 THEN 
dbms_output.put_line('NGÀY THUÊ PH?I NH? H?N NGÀY HI?N T?I');
ROLLBACK;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END ;
-- Test
/*BEGIN
ADD_EMP('208',	'John',	'Russell',	'JRUSSEL@GMAIL.COM',	'011.44.1344.429268',	'31-DEC-22',	'SA_MAN',	'14000',	'0.8',	'100',	'80');
END;*/
-- Cau 2 
CREATE OR REPLACE TRIGGER JOBS_INSERT
BEFORE  INSERT OR UPDATE ON JOBS
FOR EACH ROW
DECLARE 
CHECK1 EXCEPTION;
BEGIN
IF :NEW.MIN_SALARY>=:NEW.MAX_SALARY THEN
RAISE CHECK1;
END IF;
EXCEPTION 
WHEN CHECK1 THEN 
dbms_output.put_line('NGAY BAT DAU PHAI LUON NHO HON NGAY KET THUC');
ROLLBACK;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END ;
-- Cau 3 
CREATE OR REPLACE TRIGGER JOBS_HISTORY_INSERT_UPDATE
BEFORE  INSERT OR UPDATE ON JOB_HISTORY
FOR EACH ROW
DECLARE 
CHECK1 EXCEPTION;
BEGIN
IF :NEW.START_DATE>=:NEW.END_DATE THEN
RAISE CHECK1;
END IF;
EXCEPTION 
WHEN CHECK1 THEN 
dbms_output.put_line('MIN SALARY PHAI NHO HON MAX SALARY');
ROLLBACK;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END ;
-- Cau 4
CREATE OR REPLACE TRIGGER EMPLOYEES_UPDATE
BEFORE UPDATE ON EMPLOYEES
FOR EACH ROW
DECLARE 
CHECK1 EXCEPTION;
BEGIN
IF :NEW.SALARY<=:OLD.SALARY OR :NEW.COMMISSION_PCT<=:OLD.COMMISSION_PCT THEN
RAISE CHECK1;
END IF;
EXCEPTION 
WHEN CHECK1 THEN 
dbms_output.put_line('LUONG HOAC HOA HONG MOI CAP NHAT PHAI LON HON HOAC BANG LUONG HOAC HOA HONG CU ');
ROLLBACK;
WHEN OTHERS THEN 
BEGIN
  dbms_output.put_line('Some other kind of error occurred.');
  ROLLBACK;
  END;
END ;
