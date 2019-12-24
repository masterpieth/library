package ui;

import java.util.ArrayList;
import java.util.Scanner;

import Book.BookDAO;
import Member.MemberDAO;
import vo.Book;
import vo.Member;
import vo.Rent;

public class LibraryAdminUI {

	private Scanner sc = new Scanner(System.in);
	private BookDAO bdao = new BookDAO();
	private MemberDAO mdao = new MemberDAO();

	public LibraryAdminUI() {

		System.out.println("---------------------------------------");
		System.out.println("< admin > 님 \n로그인 되었습니다.");
		System.out.println("---------------------------------------");

		boolean flag = true;
		while (flag) {

			mainMenu();
			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch (choice) {
				case 1:
					selectMember();
					break;
				case 2:
					readMember();
					break;
				case 3:
					int deleteResult = deleteMember();
					switch (deleteResult) {
					case 1:
						System.out.println();
						System.out.println("> 회원삭제가 완료되었습니다.");
						System.out.println();
						new LibraryMainUI();
						return;
					case 0:
						System.out.println();
						System.out.println("> 반납하지 않은 도서가 있습니다.");
						System.out.println();
						break;
					case -1:
						System.out.println();
						System.out.println("> 등록되지 않은 회원입니다.");
						System.out.println();
						break;
					case -2:
						System.out.println();
						System.out.println("> 잘못된 비밀번호입니다.");
						System.out.println();
						break;
					default:
						System.out.println("> 회원탈퇴에 실패하였습니다.");
					}
					break;
				case 4:
					addBook();
					break;
				case 5:
					deleteBook();
					break;
				case 6:
					selectAll();
					break;
				case 7:
					readBook();
					break;
				case 8:
					updateReturndate();
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
				sc.nextLine();
			}
		}
	}

	void mainMenu() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    ∥ 관리자메뉴 ∥");
		System.out.println("=======================================");
		System.out.println("1.회원목록 조회");
		System.out.println("2.회원정보 검색");
		System.out.println("3.회원삭제");
		System.out.println("4.도서추가");
		System.out.println("5.도서삭제");
		System.out.println("6.도서목록 조회");
		System.out.println("7.도서검색");
		System.out.println("8.대출기간 변경");
		System.out.println("9.로그아웃");
		System.out.println("=======================================");
		System.out.print("메뉴 선택 > ");
	}

	public void selectMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    ∥ 회원목록 ∥");
		System.out.println("=======================================");
		ArrayList<Member> list = mdao.selectMember();
		System.out.println();
		System.out.println("=======================================");
		for (Member m : list) {
			if (m.getMember_id().equals("admin"))
				continue;
			System.out.println("> 회원 아이디 : " + m.getMember_id());
			System.out.println("> 회원 이름 : " + m.getMember_name());
			System.out.println("> 회원 전화번호: " + m.getMember_phone());
			System.out.println("=======================================");
		}
	}

	public void selectAll() {
		ArrayList<Book> list = bdao.selectBookAll();
		System.out.println();
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
	}

	public void readMember() {
		System.out.println();
		System.out.print("> 조회할 회원의 아이디를 입력해주세요 : ");
		String member_id = sc.nextLine();
		Member member = mdao.readMember(member_id);
		System.out.println("=========================================================================================");
		System.out.println("				∥ 회원정보 조회결과 ∥ ");
		System.out.println("=========================================================================================");
		if (member != null) {
			ArrayList<Rent> list = bdao.readRent(member_id);
			System.out.println(
					"=========================================================================================");
			System.out.println("      아이디         |            이름              |           핸드폰번호");
			System.out.println(
					"=========================================================================================");
			System.out.printf("       %s                      %s              		%s\n", member.getMember_id(),
					member.getMember_name(), member.getMember_phone());
			System.out.println(
					"=========================================================================================");
			System.out.println("    대여도서 번호    |            대여일           |              반납일");
			System.out.println(
					"=========================================================================================");
			for (Rent r : list) {
				System.out.printf("        %d                   %s            %s\n", r.getRent_book_number(),
						r.getRent_rentdate(), r.getRent_returndate());
			}
		} else {
			System.out.println();
			System.out.println("> 등록되지 않은 아이디입니다.");
			System.out.println();
		}
	}

	public int deleteMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    ∥ 회원삭제 ∥");
		System.out.println("=======================================");
		System.out.print("> 삭제할 회원의 아이디를 입력해주세요 :");
		String member_id = sc.nextLine();
		System.out.println();
		System.out.print("> 삭제할 회원의 비밀번호를 입력해주세요 :");
		String member_pw = sc.nextLine();

		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);

		Member resultMemberByIdPw = mdao.readMemberByIdPw(member);
		Member resultMemberById = mdao.readMember(member_id);
		ArrayList<Rent> isMemberRentBook = bdao.readRent(member_id);

		if (resultMemberByIdPw != null) {
			if (isMemberRentBook.size() == 0) {
				mdao.deleteMember(member_id, member_pw);
				return 1;
			} else {
				return 0;
			}
		} else if (resultMemberById != null) {
			return -2;
		} else {
			return -1;
		}
	}

	public void addBook() {
		String bookName = null;
		String bookAuthor = null;
		String bookPublisher = null;
		int bookNumber = 0;

		try {
			System.out.println();
			System.out.println("=======================================");
			System.out.println("	      ∥ 도서추가 ∥ ");
			System.out.println("=======================================");
			System.out.print("1.책 이름 : ");
			bookName = sc.nextLine();
			System.out.print("2.책 번호 : ");
			bookNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("3.책 저자 : ");
			bookAuthor = sc.nextLine();
			System.out.print("4.책 출판사 : ");
			bookPublisher = sc.nextLine();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> 잘못된 입력이 있습니다.");
			System.out.println();
			sc.nextLine();
			return;
		}

		Book book = new Book();
		book.setBook_name(bookName);
		book.setBook_number(bookNumber);
		book.setBook_author(bookAuthor);
		book.setBook_publisher(bookPublisher);

		Book bookCheck = bdao.selectBookInfo(bookNumber);
		if (bookCheck == null) {
			boolean result = bdao.addBook(book);
			if (result)
				System.out.println();
			System.out.println("> 도서 추가가 완료되었습니다. ");
			System.out.println();
			return;
		} else {
			System.out.println();
			System.out.println("> 중복된 도서번호입니다.");
		}
	}

	public void deleteBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	      ∥ 도서삭제 ∥ ");
		System.out.println("=======================================");
		System.out.print("> 삭제 할 도서번호 입력 : ");
		int no = 0;
		try {
			no = sc.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> 해당 도서번호를 숫자로 입력해주세요.");
			sc.nextLine();
			return;
		}
		boolean result = bdao.deleteBook(no);

		if (result) {
			System.out.println();
			System.out.println("> 도서삭제가 완료되었습니다.");
		} else {
			System.out.println();
			System.out.println("> 등록되지 않은 도서번호입니다.");
		}
	}

	public void updateReturndate() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    ∥ 대출기간 연장 ∥ ");
		System.out.println("=======================================");
		int day = 0;
		boolean flag = true;
		while (flag) {
			System.out.print("> 연장기간 입력 : ");
			try {
				day = sc.nextInt();
				if (day > 14) {
					System.out.println();
					System.out.println("> 연장은 최대 7일까지 가능합니다.");
					System.out.println();
					sc.nextLine();
					continue;
				} else if (day <= 0) {
					System.out.println();
					System.out.println("> 연장은 0일 이상부터 가능합니다.");
					System.out.println();
					sc.nextLine();
					continue;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> 연장일수를 숫자로 입력해주세요.");
				System.out.println();
				sc.nextLine();
				continue;
			}
			System.out.println();
			if (day > 0) {
				while (flag) {
					System.out.print("> 도서번호를 입력해주세요 : ");
					int no = 0;
					try {
						no = sc.nextInt();
						sc.nextLine();
						int result = bdao.updateReturnDate(day, no);
						Rent rent = bdao.readRentByBookNum(no);
						if (rent != null) {
							if (result > 0) {
								System.out.println();
								System.out.println("> 대출기간 변경에 성공하였습니다.");
								flag = false;
							} else {
								System.out.println();
								System.out.println("> 대출기간 변경에 실패하였습니다.");
								flag = false;
							}
						} else {
							System.out.println();
							System.out.println("> 등록되지 않은 도서번호입니다.");
							System.out.println();
							continue;
						}
					} catch (Exception e) {
						System.out.println();
						System.out.println("> 도서번호를 숫자로 입력해주세요.");
						System.out.println();
						sc.nextLine();
					}
				}
			}
		}
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
}
