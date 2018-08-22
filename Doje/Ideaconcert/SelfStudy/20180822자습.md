2018.08.22 MySQL의 특징
======================
****



## 1. 구조


### 1-1. 서버엔진

* 쿼리파싱: 데이터베이스가 SQL을 이해 가능하게 쿼리 재구성
* 디스크나 메모리 같은 물리적인 저장장치와 통신하는 스토리지 엔진에 데이터를 요청하는 업무 담당


### 1-2. 스토리지엔진

* 서버 엔진이 필요한 데이터를 물리 장치에서 가져오는 역할 담당.


| 이름 | 버전 | 트랜잭션 | LOCK 세분성 | 강점 | 약점 |
|:--------|:--------:|:--------:|:--------:|:--------:|:--------:|
| MyISAM | 모든버전 | 지원안함 | 동시 삽입 가능,테이블 락| SELECT, INSERT, 대량적재 | 읽기/쓰기, 혼합 작업 |
| InnoDB | 모든버전 | 지원 | MVCC의 ROW락  | 트랜잭션 처리 | 모델디자인 시간소요 |



참고사항

1. 스토리지엔진이란? [&#128209;](http://12bme.tistory.com/72)
2. MyISAM vs InnoDB [&#128209;](http://ojava.tistory.com/25)
3. MyISAM과 InnoDB가 어떻게 다른가요? [&#128209;](http://www.mysqlkorea.com/gnuboard4/bbs/board.php?bo_table=community_03&wr_id=1702)


****

## 2. 처리
### 2-1. 처리방식


* 스토리지 엔진은 단일코어로만 데이터를 처리(병렬처리X) 


* CPU코어 개수를 늘리는 Sacle-Out -> 단위 처리량이 좋은 CPU로 Scale-Up 하는 것(효율적)


### 2-2. BNL(Block Nested Loop)


* BNL이 제공되는 이유는 Nested Loop 조인만 지원하는 한계점 보완 때문이다.
* BNL 방식은 프로세스 내 별도의 버퍼(조인버퍼)에 Driving 테이블의 레코드를 저장 후 Inner 테이블을 스캔하면서 조인 버퍼를 탐색한다.

참고사항

1. BNL에 대한 이해(처리결과)[&#128209;](http://blog.naver.com/PostView.nhn?blogId=parkjy76&logNo=221069454499&categoryNo=14&parentCategoryNo=0&viewDate=&currentPage=1&postListTopCurrentPage=1&from=postView)

### 2-3. 쿼리 성능 진단


* 쿼리 앞에 EXPLAIN 이라는 키워드를 붙여서 사용 
* mysql 실행계획 [&#128209;](http://multifrontgarden.tistory.com/149)


****


2018.08.22 묵시적 형변환 리포트
======================


## 1. 개요
### 1-1. 정의
조건절의 데이터 타입이 다를 때 우선순위가 높은 타입으로 형변환이 내부적으로 되는 것을 말한다.
 
 
Ex)문자열과 정수값을 비교하면, 우선순위가 낮은 문자열은 자동으로 정수 타입으로 형변환 처리된다.
### 1-2. 과제예제


~~~mysql
create table test(
inti int unsigned not null auto_increment,
intj int unsigned not null,
str varchar(64) not null,
d datetime not null,
primary key(inti)
);

alter table test add key(intj), add key(str), add key(d);

insert into test(intj, str, d)
values(
	crc32(rand()),
    crc32(rand()*12345),
    date_add(now(), interval -crc32(rand())/5 second)
);

INSERT INTO test(intj, str,d)
SELECT
	crc32(rand()),
    crc32(rand())*12345,
    date_add(now(), interval -crc32(rand())/5 second)
FROM test;
 ~~~
 ****
 ## 2. 진행과정
 ### 2-1. 쿼리문 이해
 unsigned: 컬럼을 생성할때 활용도에 따라 범위를 다르게 주는 부분이다.
 Ex)id라는 컬럼은 대부분 음수를 사용할 일이 없으나 int 선언시 음수 부분이 포함되고 그 절반은 사용되지 않는다 그런데 unsigned를 사용하면 그 범위가 양수로 옮겨진다(0~4294967295)
 

 crc32(): 32비트 unsigned 값을 반환하는 함수로 인자가 NULL이면 NULL을 반환합니다.
 
 crc32 function을 사용하여 32비트의 무작위 unsigned값을 int와 string 컬럼에 삽입하는 쿼리문 예제이다.
 
 
 ### 2-2. SELECT 결과
 intj: int형 컬럼
 
 
 문자열로 검색한 결과: 0.000sec(ref)
 
 
 정수형으로 검색한 결과: 0.000sec(ref)
 
 
 str: varchar 컬럼
 
 
 문자열로 검색한 결과: 0.000sec(ref)
 
 
 정수형으로 검색한 결과: 0.453sec(ALL) -> 테이블 풀스캔 발생
 
 ****
  ## 3. 내용정리
  varchar 컬럼인 str에서 같은 문자열로 검색하면 처리시간에 큰 영향이 없으나, 
  
  
  정수형으로 검색할 경우 형변환이 발생하여 처리시간이 더 오래 걸린다.
