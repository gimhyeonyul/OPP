package library;

import java.util.ArrayList;
import java.util.List;

/**
 * [OOP 개념: 캡슐화 - 두 번째 예시]
 *
 * Member도 Book과 같은 구조.
 * 차이점: 회원은 "현재 대출 중인 책 목록"을 직접 들고 다님.
 *
 * 왜 Member가 대출 목록을 가지나?
 * → "이 회원이 무슨 책을 빌렸지?" 라는 질문에 바로 대답하려면
 *   Member 객체 안에 그 정보가 있어야 빠름.
 */
// implements Searchable: Member도 이름으로 검색될 수 있음
// Book과 Member는 상속 관계가 전혀 없지만, 같은 인터페이스를 구현할 수 있음
// → 인터페이스의 핵심: 관계없는 클래스들에게 같은 "능력"을 부여
public class Member implements Searchable {

    private final int memberId;
    private final String name;

    // ArrayList: 크기가 늘었다 줄었다 하는 배열. 몇 권 빌릴지 모르니까 일반 배열 대신 사용.
    // List<Book>: Book 객체들을 담는 리스트
    private List<Book> borrowedBooks;

    public Member(int memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>(); // 처음엔 빈 목록
    }

    // --- Getter ---

    public int getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    // 대출 목록 반환: 외부에서 목록을 읽을 수 있게 함
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // [Searchable 인터페이스 구현] 회원 이름에 keyword가 포함되면 true
    @Override
    public boolean matchesKeyword(String keyword) {
        return name.contains(keyword);
    }

    // --- 대출/반납 관련 메서드 ---

    // 대출: 회원의 목록에 책을 추가
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // 반납: 회원의 목록에서 책을 제거
    // removeIf: 조건에 맞는 항목을 리스트에서 삭제하는 Java 메서드
    public void returnBook(Book book) {
        borrowedBooks.removeIf(b -> b.getBookId() == book.getBookId());
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (대출 중: %d권)", memberId, name, borrowedBooks.size());
    }
}