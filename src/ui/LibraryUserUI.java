package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Book.BookDAO;
import Member.MemberDAO;
import vo.Book;
import vo.Member;
import vo.Rent;

public class LibraryUserUI {

	private String member_id;
	private String member_pw;
	private Scanner sc = new Scanner(System.in);
	private MemberDAO mdao = new MemberDAO();
	private BookDAO bdao = new BookDAO();

	public LibraryUserUI(Member member) {
		exec(member);
	}

	public void exec(Member member) {
		this.member_id = member.getMember_id();
		this.member_pw = member.getMember_pw();
		System.out.println("---------------------------------------");
		System.out.println("< " + member_id + " > 님 \n로그인 되었습니다.");
		System.out.println("---------------------------------------");
		System.out.println();

		boolean flag = true;

		while (flag) {
			printMainMenu();
			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					int deleteResult = deleteMember();

					switch (deleteResult) {
					case 1:
						System.out.println();
						System.out.println("> 회원탈퇴가 완료되었습니다.");
						System.out.println();
						new LibraryMainUI();
						flag = false;
						break;
					case 0:
						System.out.println();
						System.out.println("> 반납하지 않은 도서가 있습니다.");
						System.out.println();
						break;
					case -1:
						System.out.println();
						System.out.println("> 비밀번호가 일치하지 않습니다.");
						System.out.println();
						break;

					default:
						System.out.println("> 회원탈퇴에 실패하였습니다.");
					}
					break;
				case 2:
					readBook();
					break;
				case 3:
					rentBook();
					break;
				case 4:
					returnBook();
					break;
				case 5:
					readMemberInfo();
					break;
				case 6:
					selectAll();
					break;
				case 9:
					new LibraryMainUI();
					flag = false;
					break;
				default:
					System.out.println();
					System.out.println("> 메뉴에 해당하는 숫자를 입력해주세요.");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> 메뉴에 해당하는 숫자를 입력해주세요.");
				System.out.println();
				sc.nextLine();
			}
		}
	}
	public void printMainMenu() {
		System.out.println("=======================================");
		System.out.println("	     ∥ 회원메뉴 ∥");
		System.out.println("=======================================");
		System.out.println("1.회원탈퇴");
		System.out.println("2.도서검색");
		System.out.println("3.도서대출");
		System.out.println("4.도서반납");
		System.out.println("5.회원정보확인");
		System.out.println("6.도서목록 조회");
		System.out.println("9.로그아웃");
		System.out.println("=======================================");
		System.out.print("메뉴 선택 > ");
	}

	public void selectAll() {
		ArrayList<Book> list = bdao.selectBookAll();
		System.out.println("=======================================");
		System.out.println("	     ∥ 도서목록 ∥");
		System.out.println("=======================================");
		System.out.println();
		System.out.println("=======================================");
		for (Book b : list) {
			System.out.println("> 도서 이름 : " + b.getBook_name() + "\t");
			System.out.println("> 도서 번호 : " + b.getBook_number() + "\t");
			System.out.println("> 저자 : " + b.getBook_publisher());
			System.out.println("> 대출여부 : " + b.getBook_status());
			System.out.println("=======================================");
		}
		System.out.println();
	}

	public int deleteMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     ∥ 회원탈퇴 ∥");
		System.out.println("=======================================");
		System.out.print("비밀번호 재입력 > ");
		String member_pw = sc.nextLine();
		member_pw = member_pw.trim();
		ArrayList<Rent> isMemberRentBook = bdao.readRent(this.member_id);
		if (this.member_pw.equals(member_pw) && isMemberRentBook.size() == 0) {
			int result = mdao.deleteMember(member_id, member_pw);
			if (result > 0) {
				return 1;
			} else {
				return -2;
			}
		} else if (this.member_pw.equals(member_pw) && isMemberRentBook.size() != 0) {
			return 0;
		}
		return -1;
	}

	public void readBook() {
		while (true) {
			System.out.println();
			System.out.println("=======================================");
			System.out.println("        ∥ 도서검색 ∥");
			System.out.println("=======================================");
			int col = 0;
			String word = null;

			try {
				System.out.println("> 검색 조건");
				System.out.println("=======================================");
				System.out.println("1.도서 이름\n2.도서 번호\n3.저자\n4.돌아가기");
				System.out.println("=======================================");
				System.out.print("메뉴 선택 > ");
				col = sc.nextInt();
				sc.nextLine();
				switch (col) {
				case 1:
					System.out.println();
					System.out.print("> 도서 이름을 입력하세요 > ");
					break;
				case 2:
					System.out.println();
					System.out.print("> 도서 번호를 입력하세요 > ");
					break;
				case 3:
					System.out.println();
					System.out.print("> 저자명을 입력하세요 > ");
					break;
				case 4:
					return;
				default:
					System.out.println();
					System.out.println("> 잘못된 입력입니다.");
					continue;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> 해당 메뉴를 숫자로 입력해주세요.");
				sc.nextLine();
				continue;
			}
			word = sc.nextLine();
			if (col == 2) {
				try {
					Integer.parseInt(word);
				} catch (Exception e) {
					System.out.println();
					System.out.println("> 올바른 값을 입력해주세요.");
					continue;
				}
			}
			ArrayList<Book> list = bdao.searchBook(col, word);
			if (list == null || list.size() == 0) {
				System.out.println();
				System.out.println("> 검색결과가 없습니다.");
			} else {
				System.out.println();
				System.out.println("=======================================");
				for (Book b : list) {
					System.out.println("> 도서 이름 : " + b.getBook_name() + "\t");
					System.out.println("> 도서 번호 : " + b.getBook_number() + "\t");
					System.out.println("> 저자 : " + b.getBook_author());
					System.out.println("> 대출여부 : " + b.getBook_status());
					System.out.println("=======================================");
				}
				System.out.println();
			}
		}
	}

	public void rentBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     ∥ 도서대출 ∥");
		System.out.println("=======================================");
		System.out.print("> 대출하려는 도서 번호를 입력하세요 > ");
		int book_number = 0;
		try {
			book_number = sc.nextInt();

			boolean book = bdao.updateStatus(book_number);
			if (book == false) {
				System.out.println();
				System.out.println("> 해당 도서는 대출중입니다.");
				System.out.println();
				return;
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println("> 해당 도서 번호를 숫자로 입력해주세요.");
			System.out.println();
			sc.nextLine();
			return;
		}
		// '가능' => '대출중'변경

		Book bookInfo = bdao.selectBookInfo(book_number);
		Rent rent = new Rent();
		rent.setRent_book_number(bookInfo.getBook_number());
		rent.setRent_member_id(member_id);

		boolean rentResult = bdao.insertRent(rent);

		if (rentResult) {
			System.out.println();
			System.out.println("> 대출이 완료 되었습니다.");
			System.out.println();
		} // RENT에 값을 입력
	}

	public void returnBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     ∥ 도서반납 ∥");
		System.out.println("=======================================");
		System.out.print("> 반납하려는 도서 번호를 입력하세요 > ");
		int book_return = 0;

		try {
			book_return = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> 반납하려는 도서 번호를 숫자로 입력해주세요.");
			System.out.println();
			sc.nextLine();
			return;
		}
		if ((bdao.updateReturnStatus(book_return) && bdao.deleteRent(member_id, book_return)) == true) {
			System.out.println();
			System.out.println("> 반납 되었습니다.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("> 대출중인 도서가 아닙니다.");
			System.out.println();
		}
	}

	public void readMemberInfo() {
		System.out.println();
		System.out.println("=========================================================================================");
		System.out.println("				∥ 회원정보 확인 ∥ ");
		System.out.println("=========================================================================================");
		System.out.println();
		Member member = mdao.readMember(member_id);
		ArrayList<Rent> list = bdao.readRent(member_id);
		System.out.println("=========================================================================================");
		System.out.println("      아이디         |            이름              |           핸드폰번호");
		System.out.println("=========================================================================================");
		System.out.printf("       %s                      %s              		%s\n", member.getMember_id(),
				member.getMember_name(), member.getMember_phone());
		System.out.println("=========================================================================================");
		System.out.println("    대여도서 번호    |            대여일           |              반납일");
		System.out.println("=========================================================================================");
		for (Rent r : list) {
			System.out.printf("        %d                   %s            %s\n", r.getRent_book_number(),
					r.getRent_rentdate(), r.getRent_returndate());
		}
		System.out.println();
	}

}
