package library;

/**
 * [OOP 개념: 상속(Inheritance) + 메서드 오버라이딩(Method Overriding)]
 *
 * PhysicalBook은 Book을 상속받는 "자식 클래스".
 * extends Book → Book의 모든 필드와 메서드를 물려받음.
 *
 * 상속의 핵심:
 *   - 공통 부분(bookId, title, author, isAvailable, getter/setter)은 Book에 이미 있으니 다시 안 써도 됨
 *   - PhysicalBook만의 특징(실물책 대출 기간 14일)만 여기서 추가
 *
 * @Override: 부모 클래스의 메서드를 자식이 재정의하는 것.
 *   Book에서 abstract로 선언한 getMaxBorrowDays()를 여기서 실제로 구현.
 */
public class PhysicalBook extends Book {

    // 실물책은 특별한 추가 필드 없이 Book의 필드만으로 충분

    // super(...): 부모 클래스(Book)의 생성자를 호출
    // Book의 생성자가 bookId, title, author, isAvailable을 초기화해주므로 여기선 super만 호출
    public PhysicalBook(int bookId, String title, String author) {
        super(bookId, title, author);
    }

    // [오버라이딩] 실물책의 최대 대출일: 14일
    // @Override: "나는 부모 메서드를 재정의하는 거야"라고 컴파일러에게 알림
    //   → 오타로 메서드 이름이 틀리면 컴파일 에러로 잡아줌 (없으면 그냥 새 메서드로 취급됨)
    @Override
    public int getMaxBorrowDays() {
        return 14;
    }

    // toString() 오버라이딩: 실물책임을 표시하고 대출 가능일도 함께 출력
    @Override
    public String toString() {
        String status = isAvailable() ? "대출가능" : "대출중";
        return String.format("[%d] ≪%s≫ - %s [실물책] (%s) 최대%d일",
                getBookId(), getTitle(), getAuthor(), status, getMaxBorrowDays());
    }
}