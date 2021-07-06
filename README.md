# 키친포스

## 요구 사항

**상품(product)**

- 상품은 이름과 가격을 가진다.
- 상품은 등록할 수 있다.
- 상품의 가격이 올바르지 않으면 등록할 수 없다.
  - 가격이 0원 이상이어야 한다.
- 상품의 전체 목록을 조회할 수 있다.

**메뉴(menu)**

- 메뉴는 이름, 가격, 메뉴 그룹 그리고 1개 이상의 메뉴 상품으로 구성된다.

- 메뉴를 등록할 수 잇다. 
- 메뉴의 가격이 올바르지 않으면 등록될 수 없다.
  - 가격이 0원 이상이어야 한다.
- 메뉴는 메뉴 그룹이 올바르지 않으면 등록될 수 없다.
  - 메뉴를 등록하는 시점에 메뉴 그룹이 미리 등록되어 있어야 한다.
- 메뉴의 가격이 기존의 메뉴그룹의 가격 합보다 높을 경우 등록될 수 없다.

--

**주문 테이블(order table)**

- 주문 테이블 지정시, 단체 지정(table group)은 빈 값으로 초기화되어진다.
- 주문 테이블을 빈 테이블로 만들 수 있다.
  - 주문의 상태가 조리이거나, 식사의 경우에는 빈 테이블로 만들 수 없다.
- 주문 테이블에는 방문한 손님 수를 변경할 수 있다.
  - 만약 처음 방문한 손님의 수가 없을 경우, 변경할 수 없다.
  - 빈 주문 테이블의 경우에는 손님의 수를 변경할 수 없다.
- 주문 테이블의 전체 목록을 조회할 수 있다.

**단체 지정(table group)**

- 단체 지정을 원할 경우, 

  - 적어도 2개 이상의 주문된 테이블을 가져야 한다. 

  - 단체 지정 시점에, 주문 테이블이 단체 지정시 주문받은 주문 테이블과 숫자가 맞지 않으면 생성될 수 없다.
  - 단체 지정 시점에, 시점에 주문 테이블이 빈 테이블이 아니라면 단체 지정을 할 수 없다.

- 단체 지정을 취소할 수 있다.
  - 그러나 만약 주문이 조리 또는 식사 중일 때는 단체 지정을 취소할 수 없다.

**주문(order)**

- 주문은 등록 할 수 있다
- 주문의 주문 항목이 올바르지 않으면 주문을 등록할 수 없다.
  - 주문 항목이 비어있다면 주문을 등록할 수 없다.
  - 주문 항목의 갯수가 주문 항목의 메뉴의 갯수와 일치 하지 않으면 등록할 수 없다.
- 주문의 주문 테이블이 빈 테이블일 경우 주문을 등록할 수 없다.
- 주문이 등록되면, 처음 주문 상태(order status)는 조리(COOKING) 상태가 된다.
- 주문의 전체 현황을 조회할 수 있다.
- 주문의 주문 상태(order status)를 변경할 수 있다.
  - 주문의 주문 상태(order status)를 식사 상태로 변경 할 수 있다.
  
- 이미 계산이 완료된 주문은 주문 상태(order status)를 바꿀 수 없다.


## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |

TODO
 - [x] kitchenpos 패키지의 코드를 보고 키친포스의 요구 사항을 README.md 에 작성

-- 2단계
- [x] Menu JPA 적용
- [x] Order JPA 적용

- [x] Menu Service 리팩토링
- [x] Order Service 리팩토링
  

---
피드백 리뷰 결과
- [ ] Custom Exception 만들기
- [ ] setter 제거하기
- [ ] base Entity
- [ ] Price 컬럼 원시값 포장 객체 생성
- [ ] 일급 컬렉션 생성


- Menu 도메인 테스트 코드 추가
  - [ ] Menu
  - [ ] MenuGroup
  - [ ] MenuProduct
  - [ ] Product


- Order 도메인 테스트 코드 추가
  - [ ] Order
  - [ ] OrderLine
  - [ ] OrderTable
  - [ ] TableGroup

- [ ] 이벤트 기반 상태 변경

해볼 수 있는 기능들
- JPA 적용
- 이벤트 주도 개발
- 쿼리 커멘드 분리