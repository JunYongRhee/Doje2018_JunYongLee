# 문제 : 공통된 접두어(Prefix) 구하기

## I. 접근법

1. 단어 배열에 담겨진 하나의 단어를 기준으로한다
2. 그 단어를 한글자씩 나누고(Ex.car->c,a,r) 같은 방식으로 배열안의 단어들을 한 글자씩 나누어 비교한다
3. 지정된 문자열의 길이 끝까지 돌면 그 단어가 곧 가장 긴 접두어가 되므로 그 길이를 리턴
4. 문자열을 하나씩 비교하는 도중 틀린 문자열 발견시 stop, 그 길이를 리턴
5. 만약 지정한 단어의 접두어가 다른 단어의 접두어와 모두 일치하는 경우 그 길이를 리턴

## II. 수도코드
  ```
  공개 클래스 GetPrefixLength 시작
    
	메인 함수 시작 (문자열)
		단어 배열 : String 타입 배열,선언 및 초기값 주입
		출력 : 공통접두어길이 구하기 메서드 호출
	메인 함수 종료
	
	공개된 정적 함수 getMaxPrefixLength 시작 (정수)

		매개변수 : String타입 배열(단어배열)
		가장 긴 접두어 : String 타입,매개변수(단어배열)의 첫번째 값 
		
		반복문 A 시작(int i를 0에서부터 가장 긴 접두어의 길이까지)
			조각 : char 타입, 가장 긴 접두어를 단어단위로 조각낸 한 조각 단어
			
			반복문 B 시작(int j를 0에서부터 단어 배열의 길이까지)
				조건문 시작
				조건문 조건 : i가 단어배열 j번방의 문자열 길이까지 같거나 혹은 단어배열 j번방의 문자열의 i번째 char이 조각과 다른 경우
					리턴 : i
				조건문 종료
			반복문 B 종료
		반복문 A 종료

		리턴 : 가장 긴 접두어의 길이
	공개된 정적 함수 getMaxPrefixLength 종료

공개 클래스 GetPrefixLength 종료	
  ```
## III. 실제코드

```
public class GetPrefixLength {

	public static void main(String[] args) {
		
		String [] words = {"appp","ap","ap"};
		System.out.println("공통된 가장 긴 접두어의 길이:"+getMaxPrefixLength(words));
	}
	
	public static int getMaxPrefixLength(String[] word_arr) {
		String max_prefix = word_arr[0];
		
	    for (int i=0;i<max_prefix.length();i++) {
	        char piece = max_prefix.charAt(i);
	        
	        for (int j=0;j<word_arr.length;j++) {
	        	if (i==word_arr[j].length() || word_arr[j].charAt(i)!=piece) {
	            	return i; 
	            }
	        }
	    }
	    return max_prefix.length();
	}
}
```

## IV. 예상 시간복잡도

n = 배열의 길이

m = 배열의 첫번째 문자열의 길이

시간복잡도 = O ( m * n )
