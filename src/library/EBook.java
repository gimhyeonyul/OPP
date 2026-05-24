package library;

/**
 * [OOP 개념: 상속 + 필드 추가 + 다형성(Polymorphism)]
 *
 * EBook도 Book을 상속받지만, PhysicalBook과 달리 추가 필드가 있음.
 *   → fileFormat: 전자책의 파일 형식 (PDF, EPUB 등)
 *
 * 다형성(Polymorphism)이란?
 *   Library 입장에서 List<Book>에 PhysicalBook도, EBook도 모두 넣을 수 있음.
 *   꺼낼 때는 Book 타입으로 꺼내도 실제 동작은 각 클래스의 것이 실행됨.
 *
 *   예시:
 *     Book b = new EBook(1, "클린코드", "마틴", "PDF");
 *     b.getMaxBorrowDays(); // → 7 (PhysicalBook이었으면 14)
 *
 *   같은 메서드 호출인데 실제 타입에 따라 다르게 동작 = 다형성
 */
public class EBook extends Book {

    // EBook만의 추가 필드: 파일 형식
    private final String fileFormat; // "PDF", "EPUB" 등

    public EBook(int bookId, String title, String author, String fileFormat) {
        super(bookId, title, author); // 공통 초기화는 부모 생성자에게 위임
        this.fileFormat = fileFormat;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    // [오버라이딩] 전자책의 최대 대출일: 7일 (실물책 14일보다 짧음)
    @Override
    public int getMaxBorrowDays() {
        return 7;
    }

    // toString(): 파일 형식과 대출 가능일 추가 출력
    @Override
    public String toString() {
        String status = isAvailable() ? "대출가능" : "대출중";
        return String.format("[%d] ≪%s≫ - %s [전자책/%s] (%s) 최대%d일",
                getBookId(), getTitle(), getAuthor(), fileFormat, status, getMaxBorrowDays());
    }
}