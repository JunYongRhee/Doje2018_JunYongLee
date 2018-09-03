# SQL 가이드

****

## 1. 개요

관계형 데이터베이스에서 데이터 정의, 조작, 제어를 하기 위해 사용하는 언어이다.

SQL에서는 테이블을 조작하거나 조회하는 명령어의 종류는 다음과 같다.

![sqlê°ì´ë](http://www.dbguide.net/publishing/img/knowledge/SQL_148.jpg)

****

## 2. DDL

### 2-1. CREATE

~~~sql
CREATE　TABLE　테이블이름 ( 칼럼명1 DATATYPE [DEFAULT 형식], 칼럼명2 DATATYPE [DEFAULT 형식], 칼럼명2 DATATYPE [DEFAULT 형식] ) ;
~~~

위와 다르게 사용자가 원하는 조건의 데이터만 유지(무결성 유지)하기 위해 특정 컬럼에 제약을 추가 할 수 있다.

아래 사진은 테이블을 생성할 때 사용할 수 있는 제약 조건들을 표로 정리한 것이다.

![sqlê°ì´ë](http://www.dbguide.net/publishing/img/knowledge/SQL_166.jpg)



### 2-2. ALTER

한번 생성된 테이블의 구조를 사용자가 변경하고 싶을 때(Ex: 컬럼추가 및 삭제) 사용하는 명령어이다.

사용예시: 

1. 컬럼추가

~~~sql
ALTER TABLE 테이블명 ADD 추가할 칼럼명 데이터 유형;
~~~

2. 컬럼삭제

~~~sql
ALTER TABLE 테이블명 DROP COLUMN 삭제할 칼럼명;
~~~

3. 컬럼개조(데이터 유형, 디폴트 값, NOT NULL 등 제약조건 변경)

~~~sql
[Oracle] ALTER TABLE 테이블명 MODIFY (칼럼명1 데이터 유형 [DEFAULT 식] [NOT NULL], 칼럼명2 데이터 유형 …);
[SQL Server] ALTER TABLE 테이블명 ALTER (칼럼명1 데이터 유형 [DEFAULT 식] [NOT NULL], 칼럼명2 데이터 유형 …);
~~~

4. 제약조건추가

~~~sql
ALTER TABLE 테이블명 ADD CONSTRAINT 제약조건명 제약조건 (칼럼명);
~~~

5. 제약조건삭제

~~~sql
ALTER TABLE 테이블명 DROP CONSTRAINT 제약조건명;
~~~



### 2-3. DROP&TRUNCATE

~~~sql
DROP(TRUNCATE) TABLE 테이블명
~~~

DROP: 테이블 자체를 삭제한다.

TRUNCATE: 테이블 자체 삭제가 아닌, 테이블 내부의 모든 행들을 삭제한다.

****

## 3. DML

### 3-1. INSERT

~~~sql
▶ INSERT INTO 테이블명 (COLUMN_LIST)VALUES (COLUMN_LIST에 넣을 VALUE_LIST); 
▶ INSERT INTO 테이블명VALUES (전체 COLUMN에 넣을 VALUE_LIST);
~~~

값을 정의하지 않고 인서트하면 디폴트로 NULL 값이 입력된다(NOT NULL 컬럼은 제외). 



### 3-2. UPDATE

입력한 정보 중, 잘못되거나 수정하고 싶은 정보가 있을 경우 UPDATE 명령어를 사용하여 수정할 수 있다.

~~~sql
UPDATE 테이블명 SET 수정되어야 할 칼럼명 = 수정되기를 원하는 새로운 값;
~~~



### 3-3. DELETE

테이블의 정보 중 필요없는 정보는 DELETE 명령어를 사용하여 삭제 할 수 있다.

~~~sql
DELETE [FROM] 삭제를 원하는 정보가 들어있는 테이블명;
~~~



### 3-4. SELECT

사용자가 입력한 데이터를 조회하고 싶을 때 SELECT 명령어를 사용하여 테이블 내부의 정보를 조회할 수 있으며, 조회에 필요한 조건을 설정하여 SELECT 할 수 있다.

~~~sql
select 컬럼명1,컬럼명2 from 테이블명;
~~~



아래의 표는 SELECT 명령어를 사용할 때 같이 첨가할 수 있는 조건과 연산자들을 정리한 것이다.

| 명령어/연산자 | 특징                                                         |
| ------------- | ------------------------------------------------------------ |
| DISTINCT      | 중복되는 값을 피하고, 서로 다른 값만 나타낸다.               |
| WILDCARD      | 해당 테이블의 모든 컬럼 정보를 보고 싶을 때 와일드 카드의 *를 사용하여 볼 수 있다. |
| ()            | 연산자 우선순위를 변경한다.                                  |
| *             | 곱하기                                                       |
| /             | 나누기                                                       |
| +             | 더하기                                                       |
| -             | 빼기                                                         |
| \|\|          | 합성연산자, 문자와 문자를 연결할 수 있다.                    |

