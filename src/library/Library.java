package library;

import java.util.ArrayList;
import java.util.List;

/**
 * [OOP 개념: 객체 간의 협력]
 *
 * Library는 Book과 Member를 직접 만들지 않고 "관리"하는 역할.
 * 각 클래스가 자기 역할만 담당하고, Library가 이들을 조율함.
 *
 * 역할 분담:
 *   Book     → 책 정보와 상태만 알고 있음
 *   Member   → 회원 정보와 본인의 대출 목록만 알고 있음
 *   Library  → Book과 Member를 연결하는 대출/반납 로직을 처리
 */
public class Library {

    private List<Book> books;       // 도서관이 보유한 모든 책
    private List<Member> members;   // 등록된 모든 회원
    private int nextBookId;         // 다음 책 등록 시 부여할 ID (자동 증가)
    private int nextMemberId;       // 다음 회원 등록 시 부여할 ID (자동 증가)

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.nextBookId = 1;
        this.nextMemberId = 1;
    }

    // =========================================================
    // 책 관련
    // =========================================================

    // 실물책 등록
    // [다형성] List<Book>에 PhysicalBook을 넣어도 됨 - PhysicalBook은 Book이니까
    public void addBook(String title, String author) {
        PhysicalBook book = new PhysicalBook(nextBookId++, title, author);
        books.add(book);
        System.out.println("책 등록 완료: " + book);
    }

    // 전자책 등록 - 파일 형식을 추가로 받음
    public void addEBook(String title, String author, String fileFormat) {
        EBook book = new EBook(nextBookId++, title, author, fileFormat);
        books.add(book);
        System.out.println("전자책 등록 완료: " + book);
    }

    // 전체 책 목록 출력
    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println("등록된 책이 없습니다.");
            return;
        }
        System.out.println("\n=== 전체 도서 목록 ===");
        for (Book book : books) {
            System.out.println(book); // book.toString() 자동 호출
        }
    }

    // 책 검색 (제목 + 저자 동시 검색, 결과 여러 개 반환)
    // 기존 searchByTitle은 제목만, 첫 번째 결과만 반환했음
    // Searchable 인터페이스 덕분에 book.matchesKeyword()로 통일해서 호출 가능
    // → 나중에 검색 조건이 바뀌어도 Library는 수정 없이 Book쪽만 수정하면 됨
    public List<Book> search(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.matchesKeyword(keyword)) {
                results.add(book);
            }
        }
        return results;
    }

    // 회원 검색 (이름으로 검색)
    // Book과 Member 둘 다 Searchable을 구현했으므로 같은 방식으로 검색 가능
    public List<Member> searchMember(String keyword) {
        List<Member> results = new ArrayList<>();
        for (Member member : members) {
            if (member.matchesKeyword(keyword)) {
                results.add(member);
            }
        }
        return results;
    }

    // =========================================================
    // 회원 관련
    // =========================================================

    // 회원 등록
    public void addMember(String name) {
        Member member = new Member(nextMemberId++, name);
        members.add(member);
        System.out.println("회원 등록 완료: " + member);
    }

    // ID로 회원 찾기 (내부에서 자주 쓰이는 헬퍼 메서드)
    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }

    // =========================================================
    // 대출 / 반납
    // =========================================================

    /**
     * 대출 처리
     * 성공 조건: ① 책이 존재함  ② 대출 가능 상태  ③ 회원이 존재함
     * 하나라도 실패하면 이유를 출력하고 중단.
     */
    public void borrowBook(int bookId, int memberId) {
        // 1단계: 책 찾기
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("해당 ID의 책이 없습니다: " + bookId);
            return; // 여기서 메서드 종료 (아래 코드 실행 안 됨)
        }

        // 2단계: 대출 가능 여부 확인
        if (!book.isAvailable()) {
            System.out.println("이미 대출 중인 책입니다: " + book.getTitle());
            return;
        }

        // 3단계: 회원 찾기
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("해당 ID의 회원이 없습니다: " + memberId);
            return;
        }

        // 모든 조건 통과 → 실제 대출 처리
        book.setAvailable(false);     // 책 상태 변경 (대출불가)
        member.borrowBook(book);      // 회원 대출 목록에 추가
        System.out.println(member.getName() + "님이 ≪" + book.getTitle() + "≫을 대출했습니다.");
    }

    /**
     * 반납 처리
     */
    public void returnBook(int bookId, int memberId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("해당 ID의 책이 없습니다: " + bookId);
            return;
        }

        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("해당 ID의 회원이 없습니다: " + memberId);
            return;
        }

        // 실제로 이 회원이 이 책을 빌렸는지 확인
        boolean hadIt = member.getBorrowedBooks().stream()
                .anyMatch(b -> b.getBookId() == bookId);

        if (!hadIt) {
            System.out.println(member.getName() + "님은 해당 책을 대출하지 않았습니다.");
            return;
        }

        book.setAvailable(true);      // 책 상태 복구 (대출가능)
        member.returnBook(book);      // 회원 대출 목록에서 제거
        System.out.println(member.getName() + "님이 ≪" + book.getTitle() + "≫을 반납했습니다.");
    }

    // 특정 회원의 대출 목록 출력
    public void showBorrowedBooks(int memberId) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("해당 ID의 회원이 없습니다: " + memberId);
            return;
        }

        List<Book> borrowed = member.getBorrowedBooks();
        if (borrowed.isEmpty()) {
            System.out.println(member.getName() + "님은 대출 중인 책이 없습니다.");
        } else {
            System.out.println(member.getName() + "님의 대출 목록:");
            for (Book book : borrowed) {
                System.out.println("  " + book);
            }
        }
    }

    // =========================================================
    // 내부 헬퍼 (private: Library 내부에서만 사용)
    // =========================================================

    // private: 외부에서 호출할 필요 없는 내부용 메서드
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }
}