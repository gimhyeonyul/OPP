package library;

/**
 * [OOP 개념: 인터페이스(Interface)]
 *
 * 인터페이스 = "이 기능을 반드시 구현하겠다"는 계약서
 * 구현 클래스가 matchesKeyword를 반드시 갖도록 강제함
 *
 * 추상 클래스와 핵심 차이:
 *   추상 클래스 → "is-a"  : PhysicalBook은 Book이다 (한 줄기 혈통)
 *   인터페이스  → "can-do": Book도, Member도 검색될 수 있다 (능력 부여)
 *
 * Java는 클래스 다중 상속 불가 → 하지만 인터페이스는 여러 개 구현 가능
 *   예: class EBook extends Book implements Searchable, Printable { ... }
 */
public interface Searchable {

    // 인터페이스 메서드는 묵시적으로 public abstract
    // → 구현부 없이 선언만, 이 인터페이스를 implements한 클래스가 반드시 채워야 함
    boolean matchesKeyword(String keyword);
}