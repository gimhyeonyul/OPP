package library;

/**
 * [OOP 개념: 캡슐화(Encapsulation)]
 *
 * 캡슐화란? 데이터(필드)를 외부에서 직접 건드리지 못하게 숨기고,
 * 허가된 방법(메서드)으로만 접근하게 만드는 것.
 *
 * 왜? 예를 들어 bookId를 외부에서 마음대로 바꿀 수 있으면
 * 같은 ID가 두 권에 생겨도 막을 방법이 없음.
 * private으로 막고 getter만 열면 "읽기는 되지만 수정은 안 됨"을 보장할 수 있음.
 */
public class Book {

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

    public int getBookId() {
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

    // toString(): System.out.println(book) 했을 때 보여줄 문자열 정의
    // 안 만들면 "library.Book@1b6d3586" 같은 의미없는 주소값이 출력됨
    @Override
    public String toString() {
        String status = isAvailable ? "대출가능" : "대출중";
        return String.format("[%d] ≪%s≫ - %s (%s)", bookId, title, author, status);
    }
}