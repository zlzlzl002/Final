
-- 상품 테이블
CREATE TABLE shop(
	num NUMBER PRIMARY KEY, --상품번호
	name VARCHAR2(30), --상품이름
	price NUMBER, --상품가격
	remainCount NUMBER CHECK(remainCount >= 0) --재고갯수 
);

CREATE TABLE USERSS(
	id VARCHAR2(200),
	pwd VARCHAR2(200),
	email VARCHAR2(100),
	regdate DATE
);

-- sample data

INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '사과', '1000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '바나나', '2000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '귤', '3000', 5);

-- 고객 계좌 테이블
CREATE TABLE client_account(
	id VARCHAR2(30) PRIMARY KEY, -- 고객의 아이디
	money NUMBER CHECK(money >= 0), -- 고객의 잔고 
	point NUMBER
);

-- sample data

INSERT INTO client_account (id, money, point)
VALUES('gura', 10000, 0);

INSERT INTO client_account (id, money, point)
VALUES('monkey', 10000, 0);


-- 주문 테이블
CREATE TABLE client_order(
	num NUMBER PRIMARY KEY, -- 주문번호
	id VARCHAR2(30), -- 주문 고객의 아이디
	code NUMBER, -- 주문한 상품의 번호 
	addr VARCHAR2(50) -- 배송 주소
);

CREATE SEQUENCE client_order_seq;











