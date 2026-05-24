# 📖 Main.java — 학습 기록

> 파일 목적: UI 처리 (메뉴 출력, 입력 받기)

---

## 📅 작성 정보

| 항목 | 내용          |
|------|-------------|
| 날짜 | 2025-05-20  |
| 관련 파일 | `Main.java` |
| 학습 단계 | 진행중 / 완료    |

---

## 🎯 이 파일에서 구현하려 한 것

-
-
-

---

## 🤖 AI 활용 기록

### 📌 시도 1

**프롬프트:**
```
```
Scanner scanner = new Scanner(System.in);
```

이 코드가 행하는 기능이 뭐야?
```

**알게된 점:**
- Scanner란?
  사용자가 키보드로 입력하는 값을 읽어오는 도구입니다.
- Scanner → 입력을 읽는 클래스
  new Scanner(...) → Scanner 객체 생성
  System.in → 키보드(표준 입력)를 의미

### 📌 시도 2

**프롬프트:**
```
```
try {
choice = Integer.parseInt(scanner.nextLine().trim());
} catch (NumberFormatException e) {
System.out.println("숫자를 입력해주세요.");
continue; // while 처음으로 돌아감
}
```

이 코드 choice에 사용자가 입력한  문자열에서 공백을 제거하고
String형을 Integer형태로 바꿔 저장하고 만약 문자열을 인티저형태로
바꾸지 못할때 오류띄우면서 다시 while문초기로 돌아가는 코드야?
```

**알게된 점:**
- 네, 완벽하게 정확히 이해하셨습니다! 👏->이를 통해 내가 내 멋대로 해석한게 아님을 알아냄.
- 

---

## 🧱 막혔던 부분
main 클래스에서 error발생 
### ❓ 문제 상황
```
switch-case 사용한 main클래스에서 java11 호환이 안돼는 문제로 error발생
```

**에러 메시지 (있다면):**
```
인코딩 오류 (unmappable character for encoding x-windows-949)

case -> 문법 오류 (case 1 -> ..., : expected)
```

### 🔨 시도한 것들

| 시도 | 방법 | 결과 |
|------|------|------|
| 1 | | ❌ |
| 2 | | ❌ |
| 3 | | ✅ |

### ✅ 최종 해결 방법
>  switch (choice) { case 1 -> ... } 형태의 화살표 switch 표현식은 Java 14 이상에서만 사용 가능
> 그러므로 java11호환가능한 switch문법중 구버전 방식으로 재채택.


---

## 💡 배운 것 & 깨달은 것

-
-
-

---

## 🔗 참고한 것들

| 종류 | 링크 / 내용 |
|------|------------|
| AI 대화 | Claude 공유 링크 붙여넣기 |
| 공식 문서 | |
| 참고 블로그 | |

---

