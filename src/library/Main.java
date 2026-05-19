package library;

import java.util.Scanner;

/**
 * [진입점 - main 메서드]
 *
 * 프로그램 실행 시 JVM이 가장 먼저 찾아 실행하는 메서드.
 * 여기서는 Library 객체 하나를 만들고, 사용자 입력을 받아 기능을 호출함.
 *
 * 이 파일의 역할: UI 처리 (메뉴 출력, 입력 받기)
 * Library.java의 역할: 실제 비즈니스 로직 처리
 * → 역할을 분리해야 나중에 UI만 바꾸거나 로직만 바꿀 수 있음
 */
public class Main {

    public static void main(String[] args) {
        Library library = new Library(); // 도서관 객체 생성
        Scanner scanner = new Scanner(System.in); // 키보드 입력을 읽는 도구

        // 테스트용 초기 데이터 (직접 추가해보고 싶으면 이 부분 지워도 됨)
        library.addBook("자바의 정석", "남궁성");
        library.addBook("객체지향의 사실과 오해", "조영호");
        library.addBook("클린 코드", "로버트 마틴");
        library.addMember("김철수");
        library.addMember("이영희");

        System.out.println("\n도서관 관리 시스템에 오신 것을 환영합니다!");

        // 프로그램 메인 루프: 0을 입력할 때까지 반복
        while (true) {
            printMenu();
            System.out.print("선택: ");

            // 숫자 입력 처리
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
                continue; // while 처음으로 돌아감
            }

            // 선택에 따라 분기
            switch (choice) {
                case 1 -> library.showAllBooks();

                case 2 -> {
                    System.out.print("검색할 제목 키워드: ");
                    String keyword = scanner.nextLine();
                    Book result = library.searchByTitle(keyword);
                    if (result != null) {
                        System.out.println("검색 결과: " + result);
                    } else {
                        System.out.println("검색 결과가 없습니다.");
                    }
                }

                case 3 -> {
                    System.out.print("책 제목: ");
                    String title = scanner.nextLine();
                    System.out.print("저자: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                }

                case 4 -> {
                    System.out.print("회원 이름: ");
                    String name = scanner.nextLine();
                    library.addMember(name);
                }

                case 5 -> {
                    System.out.print("대출할 책 ID: ");
                    int bookId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("회원 ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine().trim());
                    library.borrowBook(bookId, memberId);
                }

                case 6 -> {
                    System.out.print("반납할 책 ID: ");
                    int bookId = Integer.parseInt(scanner.nextLine().trim());
                    System.out.print("회원 ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine().trim());
                    library.returnBook(bookId, memberId);
                }

                case 7 -> {
                    System.out.print("대출 목록을 볼 회원 ID: ");
                    int memberId = Integer.parseInt(scanner.nextLine().trim());
                    library.showBorrowedBooks(memberId);
                }

                case 0 -> {
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return; // main 메서드 종료 = 프로그램 종료
                }

                default -> System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
            }
        }
    }

    // 메뉴 출력을 별도 메서드로 분리 → main이 길어지는 것을 방지
    private static void printMenu() {
        System.out.println("\n--- 메뉴 ---");
        System.out.println("1. 전체 도서 목록");
        System.out.println("2. 책 검색");
        System.out.println("3. 책 등록");
        System.out.println("4. 회원 등록");
        System.out.println("5. 대출");
        System.out.println("6. 반납");
        System.out.println("7. 내 대출 목록");
        System.out.println("0. 종료");
    }
}