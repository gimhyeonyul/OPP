package library;

/**
 * [OOP 개념: 상속(Inheritance) + 추상 클래스(Abstract Class)]
 *
 * 이전 단계: Book을 일반 클래스로 만들어 캡슐화를 배웠음.
 * 이번 단계: Book을 추상 클래스로 바꿔 "공통 뼈대"만 정의하고,
 *            실물책(PhysicalBook)과 전자책(EBook)이 각각 구체적인 내용을 채우게 함.
 *
 * abstract class란?
 *   - 직접 객체를 만들 수 없음: new Book(...) 불가
 *   - 공통 필드/메서드는 여기서 구현, 클래스마다 달라지는 것은 abstract로 선언만 해둠
 *   - 자식 클래스가 반드시 abstract 메서드를 구현해야 컴파일 됨 (강제 계약)
 *
 * 왜 추상 클래스를 쓰나?
 *   실물책은 14일, 전자책은 7일처럼 대출 기간이 다름.
 *   그 차이를 각 서브클래스에게 맡기고, Book에는 공통 로직만 남김.
 */
public abstract class Book {

    // private: 이 클래스 안에서만 접근 가능. 외부에서 book.bookId = 999; 이런 거 불가능.
    private final int bookId;       // 고유 ID (한번 정해지면 바뀌면 안 되니까 final)
    private final String title;     // 제목
    private final String author;    // 저자
    private boolean isAvailable;    // 대출 가능 여부 (대출되면 false로 바뀜)

    // 생성자: Book 객체를 만들 때 반드시 필요한 값들을 받음
    // "책을 등록하려면 ID, 제목, 저자는 필수"라는 규칙을 코드로 표현한 것
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // 처음 등록된 책은 당연히 대출 가능
    }

    // --- Getter: 외부에서 값을 읽을 수 있는 창구 ---

    public
    int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // --- Setter: isAvailable만 변경 허용 (id, title, author는 setter 없음 = 변경 불가) ---

    // 도서관 로직(Library.java)에서만 대출/반납 상태를 바꿔야 하지만,
    // 지금은 단순하게 setter를 열어둠. 심화 단계에서 접근 제어를 더 조일 수 있음.
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // [추상 메서드] 최대 대출 가능 일수
    // abstract: 구현 없이 선언만 함 → 자식 클래스(PhysicalBook, EBook)가 반드시 구현해야 함
    // 이게 없으면 컴파일 에러 발생 → "잊어버리는 실수"를 컴파일러가 잡아줌
    public abstract int getMaxBorrowDays();

    // toString(): System.out.println(book) 했을 때 보여줄 문자열 정의
    // 안 만들면 "library.Book@1b6d3586" 같은 의미없는 주소값이 출력됨
    @Override
    public String toString() {
        String status = isAvailable ? "대출가능" : "대출중";
        return String.format("[%d] ≪%s≫ - %s (%s)", bookId, title, author, status);
    }
}