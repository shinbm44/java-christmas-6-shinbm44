# 크리스마스 이벤트 필요 기능 정리 
# 우테코 4주차 미션 크리스마스 이벤트에 필요한 기능을 먼저 정리해보자.
************************
## Part.1 입력

### 1)날짜 입력

1) 정수가 들어왔는가?

2) 1~31 범주의 숫자가 들어왔는가?

### 2) 메뉴 입력

1) 주어진 형식으로 메뉴를 입력했나?

2) 최대 20개까지 범주로 주문했나?

3) 메뉴판에 정해진 것을 골랐나?

4) 메뉴의 개수를 1 이상의 정수로 입력했나?

5) 중복 메뉴를 입력하지 않았는가?

6) 음료만 주문하지 않았는가?

## Part.2 기능

### 1) 총 주문금액 계산 & 이벤트 할인 여부

1) 먼저, 주문금액을 계산한다. 이후 총 주문금액이 10,000원 이상일 경우, 이벤트 조건 적용 가능하다.
만약 10,000원 미만의 금액이라면 이벤트 적용에 해당사항이 없다.

### 2) D-day 할인 

1) 날짜에 따른 이벤트 적용 할인 금액을 계산한다.

### 3) 평일 할인 

1) 일요일 ~ 목요일에 해당할 경우, '디저트 메뉴' 1개당 2023원 할인 적용

### 4) 주말 할인

1) 금요일 or 토요일 경우, '메인 메뉴' 1개당 2023원 할인 적용

### 5) 특별 할인

1) ★ ('별')이 있는 날이면 1000원 할인 적용

### 6) 증정 이벤트 

1) 이벤트 할인 전, 총 주문 금액이 120,000원 이상이라면 샴페인 1개 증정된다.
그리고 이 샴페인은 총 혜택(할인) 금액에 적용된다.(for 이벤트 배지 부여) 

### 7) 총 혜택 금액 계산

1) 총 혜택 금액 = 할인금액 합계 + 증정 메뉴 가격
2) 총 혜택 금액을 기반으로 '이벤트 배지'를 부여



### * 예외처리 *
- "[ERRROR]~ ,다시 입력해주세요." 출력 후 다시 입력받기
- 단, '없는 메뉴/잘못된 메뉴 개수/메뉴 입력 형식 오류/중복 메뉴 입력'의 경우. 정해진 오류문을 사용한다. 

## Part.3 출력

### 1) 주문 메뉴의 출력 순서는 자유롭게

### 2) 혜택 금액 : 할인 금액 합계 + 증정 메뉴 가격(25,000)

### 3) 결제 금액 : 총 주문 금액 - 할인 금액 

### 4) 적용되는 혜택 내역이나, 증정메뉴, 이벤트 배지 적용 사항이 없다면 "없음"이라고 출력해준다. 

### 5) 여러 혜택이 적용된다면, 순서는 자유롭게 출력해준다. 
