--테이블 생성
CREATE TABLE userInfo(
userID VARCHAR2(20),
userPassword VARCHAR2(20),
userName VARCHAR2(20),
userGender VARCHAR2(20),
userEmail VARCHAR2(50),
CONSTRAINT primary_ID PRIMARY KEY(userID)
);

--테이블 확인
SELECT * FROM userinfo;
DESC userinfo;

--데이터 입력
INSERT INTO userinfo
VALUES('gildong','123456','홍길동','남자','gildong@naver.com');

--커밋
commit;