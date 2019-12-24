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
		System.out.println("< admin > �� \n�α��� �Ǿ����ϴ�.");
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
						System.out.println("> ȸ�������� �Ϸ�Ǿ����ϴ�.");
						System.out.println();
						new LibraryMainUI();
						return;
					case 0:
						System.out.println();
						System.out.println("> �ݳ����� ���� ������ �ֽ��ϴ�.");
						System.out.println();
						break;
					case -1:
						System.out.println();
						System.out.println("> ��ϵ��� ���� ȸ���Դϴ�.");
						System.out.println();
						break;
					case -2:
						System.out.println();
						System.out.println("> �߸��� ��й�ȣ�Դϴ�.");
						System.out.println();
						break;
					default:
						System.out.println("> ȸ��Ż�� �����Ͽ����ϴ�.");
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
					System.out.println("> �޴��� �ش��ϴ� ���ڸ� �Է����ּ���.");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> �޴��� �ش��ϴ� ���ڸ� �Է����ּ���.");
				sc.nextLine();
			}
		}
	}

	void mainMenu() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    �� �����ڸ޴� ��");
		System.out.println("=======================================");
		System.out.println("1.ȸ����� ��ȸ");
		System.out.println("2.ȸ������ �˻�");
		System.out.println("3.ȸ������");
		System.out.println("4.�����߰�");
		System.out.println("5.��������");
		System.out.println("6.������� ��ȸ");
		System.out.println("7.�����˻�");
		System.out.println("8.����Ⱓ ����");
		System.out.println("9.�α׾ƿ�");
		System.out.println("=======================================");
		System.out.print("�޴� ���� > ");
	}

	public void selectMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    �� ȸ����� ��");
		System.out.println("=======================================");
		ArrayList<Member> list = mdao.selectMember();
		System.out.println();
		System.out.println("=======================================");
		for (Member m : list) {
			if (m.getMember_id().equals("admin"))
				continue;
			System.out.println("> ȸ�� ���̵� : " + m.getMember_id());
			System.out.println("> ȸ�� �̸� : " + m.getMember_name());
			System.out.println("> ȸ�� ��ȭ��ȣ: " + m.getMember_phone());
			System.out.println("=======================================");
		}
	}

	public void selectAll() {
		ArrayList<Book> list = bdao.selectBookAll();
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     �� ������� ��");
		System.out.println("=======================================");
		System.out.println();
		System.out.println("=======================================");
		for (Book b : list) {
			System.out.println("> ���� �̸� : " + b.getBook_name() + "\t");
			System.out.println("> ���� ��ȣ : " + b.getBook_number() + "\t");
			System.out.println("> ���� : " + b.getBook_publisher());
			System.out.println("> ���⿩�� : " + b.getBook_status());
			System.out.println("=======================================");
		}
	}

	public void readMember() {
		System.out.println();
		System.out.print("> ��ȸ�� ȸ���� ���̵� �Է����ּ��� : ");
		String member_id = sc.nextLine();
		Member member = mdao.readMember(member_id);
		System.out.println("=========================================================================================");
		System.out.println("				�� ȸ������ ��ȸ��� �� ");
		System.out.println("=========================================================================================");
		if (member != null) {
			ArrayList<Rent> list = bdao.readRent(member_id);
			System.out.println(
					"=========================================================================================");
			System.out.println("      ���̵�         |            �̸�              |           �ڵ�����ȣ");
			System.out.println(
					"=========================================================================================");
			System.out.printf("       %s                      %s              		%s\n", member.getMember_id(),
					member.getMember_name(), member.getMember_phone());
			System.out.println(
					"=========================================================================================");
			System.out.println("    �뿩���� ��ȣ    |            �뿩��           |              �ݳ���");
			System.out.println(
					"=========================================================================================");
			for (Rent r : list) {
				System.out.printf("        %d                   %s            %s\n", r.getRent_book_number(),
						r.getRent_rentdate(), r.getRent_returndate());
			}
		} else {
			System.out.println();
			System.out.println("> ��ϵ��� ���� ���̵��Դϴ�.");
			System.out.println();
		}
	}

	public int deleteMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    �� ȸ������ ��");
		System.out.println("=======================================");
		System.out.print("> ������ ȸ���� ���̵� �Է����ּ��� :");
		String member_id = sc.nextLine();
		System.out.println();
		System.out.print("> ������ ȸ���� ��й�ȣ�� �Է����ּ��� :");
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
			System.out.println("	      �� �����߰� �� ");
			System.out.println("=======================================");
			System.out.print("1.å �̸� : ");
			bookName = sc.nextLine();
			System.out.print("2.å ��ȣ : ");
			bookNumber = sc.nextInt();
			sc.nextLine();
			System.out.print("3.å ���� : ");
			bookAuthor = sc.nextLine();
			System.out.print("4.å ���ǻ� : ");
			bookPublisher = sc.nextLine();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> �߸��� �Է��� �ֽ��ϴ�.");
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
			System.out.println("> ���� �߰��� �Ϸ�Ǿ����ϴ�. ");
			System.out.println();
			return;
		} else {
			System.out.println();
			System.out.println("> �ߺ��� ������ȣ�Դϴ�.");
		}
	}

	public void deleteBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	      �� �������� �� ");
		System.out.println("=======================================");
		System.out.print("> ���� �� ������ȣ �Է� : ");
		int no = 0;
		try {
			no = sc.nextInt();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> �ش� ������ȣ�� ���ڷ� �Է����ּ���.");
			sc.nextLine();
			return;
		}
		boolean result = bdao.deleteBook(no);

		if (result) {
			System.out.println();
			System.out.println("> ���������� �Ϸ�Ǿ����ϴ�.");
		} else {
			System.out.println();
			System.out.println("> ��ϵ��� ���� ������ȣ�Դϴ�.");
		}
	}

	public void updateReturndate() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	    �� ����Ⱓ ���� �� ");
		System.out.println("=======================================");
		int day = 0;
		boolean flag = true;
		while (flag) {
			System.out.print("> ����Ⱓ �Է� : ");
			try {
				day = sc.nextInt();
				if (day > 14) {
					System.out.println();
					System.out.println("> ������ �ִ� 7�ϱ��� �����մϴ�.");
					System.out.println();
					sc.nextLine();
					continue;
				} else if (day <= 0) {
					System.out.println();
					System.out.println("> ������ 0�� �̻���� �����մϴ�.");
					System.out.println();
					sc.nextLine();
					continue;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> �����ϼ��� ���ڷ� �Է����ּ���.");
				System.out.println();
				sc.nextLine();
				continue;
			}
			System.out.println();
			if (day > 0) {
				while (flag) {
					System.out.print("> ������ȣ�� �Է����ּ��� : ");
					int no = 0;
					try {
						no = sc.nextInt();
						sc.nextLine();
						int result = bdao.updateReturnDate(day, no);
						Rent rent = bdao.readRentByBookNum(no);
						if (rent != null) {
							if (result > 0) {
								System.out.println();
								System.out.println("> ����Ⱓ ���濡 �����Ͽ����ϴ�.");
								flag = false;
							} else {
								System.out.println();
								System.out.println("> ����Ⱓ ���濡 �����Ͽ����ϴ�.");
								flag = false;
							}
						} else {
							System.out.println();
							System.out.println("> ��ϵ��� ���� ������ȣ�Դϴ�.");
							System.out.println();
							continue;
						}
					} catch (Exception e) {
						System.out.println();
						System.out.println("> ������ȣ�� ���ڷ� �Է����ּ���.");
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
			System.out.println("        �� �����˻� ��");
			System.out.println("=======================================");
			int col = 0;
			String word = null;

			try {
				System.out.println("> �˻� ����");
				System.out.println("=======================================");
				System.out.println("1.���� �̸�\n2.���� ��ȣ\n3.����\n4.���ư���");
				System.out.println("=======================================");
				System.out.print("�޴� ���� > ");
				col = sc.nextInt();
				sc.nextLine();
				switch (col) {
				case 1:
					System.out.println();
					System.out.print("> ���� �̸��� �Է��ϼ��� > ");
					break;
				case 2:
					System.out.println();
					System.out.print("> ���� ��ȣ�� �Է��ϼ��� > ");
					break;
				case 3:
					System.out.println();
					System.out.print("> ���ڸ��� �Է��ϼ��� > ");
					break;
				case 4:
					return;
				default:
					System.out.println();
					System.out.println("> �߸��� �Է��Դϴ�.");
					continue;
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> �ش� �޴��� ���ڷ� �Է����ּ���.");
				sc.nextLine();
				continue;
			}
			word = sc.nextLine();
			if (col == 2) {
				try {
					Integer.parseInt(word);
				} catch (Exception e) {
					System.out.println();
					System.out.println("> �ùٸ� ���� �Է����ּ���.");
					continue;
				}
			}
			ArrayList<Book> list = bdao.searchBook(col, word);
			if (list == null || list.size() == 0) {
				System.out.println();
				System.out.println("> �˻������ �����ϴ�.");
			} else {
				System.out.println();
				System.out.println("=======================================");
				for (Book b : list) {
					System.out.println("> ���� �̸� : " + b.getBook_name() + "\t");
					System.out.println("> ���� ��ȣ : " + b.getBook_number() + "\t");
					System.out.println("> ���� : " + b.getBook_author());
					System.out.println("> ���⿩�� : " + b.getBook_status());
					System.out.println("=======================================");
				}
				System.out.println();
			}
		}
	}
}
