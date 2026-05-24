# 📖 Searchable.java — 학습 기록

> 파일 목적: 인터페이스(Interface) 개념 학습 및 관련 파일 변경 사항 기록

---

## 📅 작성 정보

| 항목 | 내용 |
|------|------|
| 날짜 | 2025-05-24 |
| 관련 파일 | `Searchable.java`, `Book.java`, `Member.java`, `Library.java`, `Main.java` |
| 학습 단계 | 진행중 / 완료 |

---

## 🎯 이번 단계에서 구현한 것

- `Searchable` 인터페이스 생성 → `matchesKeyword(String keyword)` 계약 정의
- `Book implements Searchable` → 제목 또는 저자로 검색 가능
- `Member implements Searchable` → 이름으로 검색 가능
- `Library.search()` 개선 → 기존 제목만 검색 → 제목 + 저자 검색, 여러 결과 반환
- `Library.searchMember()` 추가 → 회원 이름 검색

---

## 🧠 핵심 개념 정리

### 인터페이스 (Interface)
- "이 기능을 반드시 구현하겠다"는 계약서
- 메서드 선언만 있고 구현부 없음 → `implements`한 클래스가 반드시 채워야 함
- 인터페이스 메서드는 묵시적으로 `public abstract`

```java
public interface Searchable {
    boolean matchesKeyword(String keyword); // 선언만, 구현 없음
}
```

### 추상 클래스 vs 인터페이스

| 항목 | 추상 클래스 | 인터페이스 |
|------|------------|-----------|
| 키워드 | `extends` | `implements` |
| 관계 | "is-a" (PhysicalBook은 Book이다) | "can-do" (Book은 검색될 수 있다) |
| 다중 적용 | 하나만 상속 가능 | 여러 개 구현 가능 |
| 필드 | 가질 수 있음 | 가질 수 없음 |

### 관계없는 클래스에 같은 능력 부여
Book과 Member는 상속 관계가 전혀 없지만, 둘 다 `Searchable`을 구현할 수 있음

```java
// Book과 Member는 서로 관계 없음
// 하지만 둘 다 Searchable이라는 능력을 가짐
public abstract class Book implements Searchable { ... }
public class Member implements Searchable { ... }
```

### extends + implements 동시 사용
```java
// Book은 추상클래스를 상속하면서 인터페이스도 구현 가능
public class EBook extends Book implements Searchable { ... }
// (Book이 이미 Searchable을 implements 했으므로 EBook은 따로 안 써도 됨)
```

---

## 📝 파일별 변경 사항

### ✅ Searchable.java (신규 생성)
```java
public interface Searchable {
    boolean matchesKeyword(String keyword);
}
```
- 인터페이스 파일 신규 생성
- `matchesKeyword()` 하나만 선언

---

### ✅ Book.java (수정)

**변경 전:**
```java
public abstract class Book {
```

**변경 후:**
```java
public abstract class Book implements Searchable {
```

**추가된 메서드:**
```java
// 제목 또는 저자에 keyword 포함 시 true 반환
@Override
public boolean matchesKeyword(String keyword) {
    return title.contains(keyword) || author.contains(keyword);
}
```
- Book에 구현해두면 PhysicalBook, EBook은 따로 구현 안 해도 됨 (상속으로 물려받음)

---

### ✅ Member.java (수정)

**변경 전:**
```java
public class Member {
```

**변경 후:**
```java
public class Member implements Searchable {
```

**추가된 메서드:**
```java
// 회원 이름에 keyword 포함 시 true 반환
@Override
public boolean matchesKeyword(String keyword) {
    return name.contains(keyword);
}
```

---

### ✅ Library.java (수정)

**변경 전 — searchByTitle():**
```java
// 제목만 검색, 첫 번째 결과 하나만 반환
public Book searchByTitle(String keyword) {
    for (Book book : books) {
        if (book.getTitle().contains(keyword)) {
            return book;
        }
    }
    return null;
}
```

**변경 후 — search():**
```java
// 제목 + 저자 검색, 일치하는 모든 결과 반환
public List<Book> search(String keyword) {
    List<Book> results = new ArrayList<>();
    for (Book book : books) {
        if (book.matchesKeyword(keyword)) { // Searchable 인터페이스 활용
            results.add(book);
        }
    }
    return results;
}
```

**추가 — searchMember():**
```java
// 이름으로 회원 검색
public List<Member> searchMember(String keyword) {
    List<Member> results = new ArrayList<>();
    for (Member member : members) {
        if (member.matchesKeyword(keyword)) {
            results.add(member);
        }
    }
    return results;
}
```

---

### ✅ Main.java (수정)

**변경 전 — case 2:**
```java
Book result = library.searchByTitle(keyword);
if (result != null) {
    System.out.println("검색 결과: " + result);
}
```

**변경 후 — case 2:**
```java
List<Book> results = library.search(keyword);
if (results.isEmpty()) {
    System.out.println("검색 결과가 없습니다.");
} else {
    System.out.println("검색 결과 " + results.size() + "건:");
    for (Book b : results) {
        System.out.println("  " + b);
    }
}
```
- `List` import 추가

---

## 🤖 AI 활용 기록

### 📌 시도 1

**프롬프트:**
```
여기에 AI한테 입력한 프롬프트 그대로 붙여넣기
```

**결과 및 느낀점:**
- 👍 좋았던 점:
- 👎 부족했던 점:

---

## 🧱 막혔던 부분

### ❓ 문제 상황
```
어떤 코드에서, 어떤 에러가 났는지 설명
```

**에러 메시지 (있다면):**
```
에러 메시지 붙여넣기
```

### 🔨 시도한 것들

| 시도 | 방법 | 결과 |
|------|------|------|
| 1 | | ❌ |
| 2 | | ❌ |
| 3 | | ✅ |

### ✅ 최종 해결 방법
> 어떻게 해결했는지 한 줄 요약

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

## ⏭️ 다음에 할 것

- [ ]
- [ ]