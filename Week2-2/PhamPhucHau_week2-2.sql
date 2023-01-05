--Bai 1 - Viet lenh tao bang
-- Table CAMPUS
CREATE TABLE CAMPUS(
CampusID number(20) not null primary key,
CampusName varchar2(100) not null,
Street varchar2(100) not null,
City varchar2(100) not null,
State varchar2(100) not null,
Zip varchar2(100),
Phone varchar2(100),
CampusDiscount decimal (2,2)
)
-- Table POSITION
CREATE TABLE POSITION (
PositionID number(20) NOT NULL PRIMARY KEY,
POSITION varchar2(100),
YearlyMembershipFee Decimal (7,2)
)


-- Table MEMBERS
CREATE TABLE MEMBERS(
MemberID number(20) not null primary key,
LastName varchar2(100) not null,
FirstName varchar2(100) not null,
CampusAddress varchar2(100) not null,
CampusPhone varchar2(100) not null,
CampusID number(20) not null, 
PositionID number(20) not null,
ContactDuration integer not null
)
ALTER TABLE MEMBERS
ADD ( CONSTRAINT MEMBERS_POSITION   FOREIGN KEY (PositionID) REFERENCES POSITION(PositionID),CONSTRAINT MEMBERS_CAMPUS   FOREIGN KEY (CampusID) REFERENCES CAMPUS(CampusID));
-- Table PRICES
CREATE TABLE PRICES(
FoodItemTypeID number(20) not null primary key,
MealType varchar2(100) not null,
MealPrice DECIMAL (7,2) NOT NULL
)

-- Table FoodItems
CREATE TABLE FoodItems(
FoodItemsID number(20) not null primary key ,
FoodItemName varchar2(100) not null,
FoodItemTypeID number(20) not null
)
ALTER TABLE FoodItems 
ADD CONSTRAINT FoodItems_PRICES FOREIGN KEY (FoodItemTypeID) REFERENCES PRICES(FoodItemTypeID);


-- Table Orders
CREATE TABLE Orders
( OrderID number(20) not null primary key,
MemberID number(20) not null ,
OrderDate varchar2(25) not null
);
ALTER TABLE Orders
ADD CONSTRAINT Orders_MEMBERS FOREIGN KEY (MemberID) REFERENCES MEMBERS(MemberID);
-- Table OrderLine
CREATE TABLE OrderLine(
OrderID number(20) not null, 
FoodItemsID number(20) not null,
Quantity integer not null
)
ALTER TABLE OrderLine
ADD(CONSTRAINT tb_PK PRIMARY KEY (OrderID, FoodItemsID),
CONSTRAINT OrderLine_Order FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
CONSTRAINT OrderLine_FoodItemsID FOREIGN KEY (FoodItemsID) REFERENCES FoodItems(FoodItemsID));

CREATE SEQUENCE Prices_FoodItemTypeID_SEQ;
-- Bai 2 - Viet lenh tao du lieu
-- Insert Data Campus
CREATE SEQUENCE CAMPUS_SEQ;
INSERT INTO CAMPUS VALUES(CAMPUS_SEQ.Nextval,'IUPUI','425 University Blvd.','Indianapolis', 'IN','46202', '317-274-4591',.08);
INSERT INTO CAMPUS VALUES(CAMPUS_SEQ.Nextval,'Indiana University','107 S. Indiana Ave.','Bloomington', 'IN','47405', '812-855-
4848',.07);
INSERT INTO CAMPUS VALUES(CAMPUS_SEQ.Nextval,'Purdue University','475 Stadium Mall Drive','West Lafayette', 'IN','47907', '765-
494-1776',.06);

SELECT * FROM CAMPUS;
-- Insert Data POSITION
CREATE SEQUENCE POSITION_SEQ;
--alter sequence POSITION_SEQ restart ;

INSERT INTO POSITION VALUES(POSITION_SEQ.Nextval,'Lecturer', 1050.50);
INSERT  INTO POSITION VALUES(POSITION_SEQ.Nextval,'Associate Professor', 900.50);
INSERT  INTO POSITION VALUES(POSITION_SEQ.Nextval,'Assistant Professor', 875.50);
INSERT  INTO POSITION VALUES(POSITION_SEQ.Nextval,'Professor', 700.75);
INSERT  INTO POSITION VALUES(POSITION_SEQ.Nextval,'Full Professor', 500.50);
 
SELECT * FROM POSITION;
-- INSERT DATA MEMBERS
CREATE SEQUENCE MEMBERS_SEQ;
--alter sequence MEMBERS_SEQ restart ;
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Ellen','Monk','009 Purnell', '812-123-1234', '2', '5', 12);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Joe','Brady','008 Statford Hall', '765-234-2345', '3', '2', 10);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Dave','Davidson','007 Purnell', '812-345-3456', '2', '3', 10);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Sebastian','Cole','210 Rutherford Hall', '765-234-2345', '3', '5', 10);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Michael','Doo','66C Peobody', '812-548-8956', '2', '1', 10);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Jerome','Clark','SL 220', '317-274-9766', '1', '1', 12);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Bob','House','ET 329', '317-278-9098', '1', '4', 10);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Bridget','Stanley','SI 234', '317-274-5678', '1', '1', 12);
INSERT INTO MEMBERS VALUES(MEMBERS_SEQ.NEXTVAL,'Bradley','Wilson','334 Statford Hall', '765-258-2567', '3', '2', 10);
SELECT * FROM MEMBERS;
-- Insert Data PRICES
CREATE SEQUENCE PRICES_SEQ;
INSERT INTO PRICES VALUES(PRICES_SEQ.NEXTVAL,'Beer/Wine', 5.50);
INSERT INTO PRICES VALUES(PRICES_SEQ.NEXTVAL,'Dessert', 2.75);
INSERT INTO PRICES VALUES(PRICES_SEQ.NEXTVAL,'Dinner', 15.50);
INSERT INTO PRICES VALUES(PRICES_SEQ.NEXTVAL,'Soft Drink', 2.50);
INSERT INTO PRICES VALUES(PRICES_SEQ.NEXTVAL,'Lunch', 7.25);

SELECT * FROM PRICES;
-- INSERT DATA FOODITEMS
CREATE SEQUENCE FOODITEMS_SEQ START WITH 10001;
DROP SEQUENCE FOODITEMS_SEQ;
--ALTER SEQUENCE FOODITEMS_SEQ START WITH 1001;
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Lager', 1);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Red Wine', 1);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'White Wine', 1);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Coke', 4);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Coffee', 4);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Chicken a la King', 4);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Rib Steak', '3');
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Fish and Chips',4);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Veggie Delight', 4);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Chocolate Mousse', 2);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Carrot Cake', 2);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Fruit Cup', 2);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Fish and Chips', 2);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Angus Beef Burger', 2);
INSERT INTO FOODITEMS VALUES(FOODitems_SEQ.NEXTVAL,'Cobb Salad', 2);
SELECT * FROM FOODITEMS;
--DELETE FOODITEMS;
-- INSERT DATA ORDERS
CREATE SEQUENCE ORDERS_SEQ;
--ALTER SEQUENCE ORDERS_SEQ RESTART;
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL,'9', 'March 5, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '8', 'March 5, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '7', 'March 5, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '6', 'March 7, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '5', 'March 7, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '4', 'March 10, 2005');

INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '3', 'March 7, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '2', 'March 7, 2005');
INSERT INTO ORDERS VALUES(ORDERS_SEQ.NEXTVAL, '1', 'March 10, 2005');
SELECT * FROM ORDERS;
--DELETE ORDERS

-- INSERT DATA ORDERLINE 
INSERT INTO OrderLine VALUES(1,10001,1);
INSERT INTO OrderLine VALUES(1,10006,1);
INSERT INTO OrderLine VALUES(1,10012,1);
INSERT INTO OrderLine VALUES(2,10004,2);
INSERT INTO OrderLine VALUES(2,10013,1);
INSERT INTO ORDERLINE VALUES(2,10014,1);
INSERT INTO ORDERLINE VALUES(3,10005,1);
INSERT INTO ORDERLINE VALUES(3,10011,1);
INSERT INTO ORDERLINE VALUES(4,10005,2);
INSERT INTO ORDERLINE VALUES(4,10004,2);
INSERT INTO ORDERLINE VALUES(4,10006,1);
INSERT INTO ORDERLINE VALUES(4,10007,1);
INSERT INTO ORDERLINE VALUES(4,10010,2);
INSERT INTO ORDERLINE VALUES(5,10003,1);
INSERT INTO ORDERLINE VALUES(6,10002,2);
INSERT INTO ORDERLINE VALUES(7,10005,2);
INSERT INTO ORDERLINE VALUES(8,10005,1);
INSERT INTO ORDERLINE VALUES(8,10011,1);
INSERT INTO ORDERLINE VALUES(9,10001,1);
-- Bai 3 - Viet lenh truy van theo yeu cau.
--1) Liet ke tat ca constrain trong database;
SELECT *
  FROM user_cons_columns
WHERE owner = 'SYS' AND table_name IN('CAMPUS','ORDERLINE','ORDERS','FOODITEMS','PRICES','MEMBERS','POSITION') AND POSITION IS NOT NULL;
--2) Liet ke tat ca cac bang trong database
select * from user_tables;
--3) Liet ke tat ca sequence trong database
SELECT * FROM user_sequences;
--4) Liet ke cac thanh vien voi cot FirstName,LastName...
SELECT FirstName,LastName,Position,CampusName,(YearlyMembershipFee/12) monthly_dues
FROM  CAMPUS C  join MEMBERS M ON M.CampusID=C.CampusID join POSITION P ON P.PositionID=M.PositionID
ORDER BY CampusName desc, LastName asc;