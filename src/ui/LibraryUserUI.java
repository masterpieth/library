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
		System.out.println("< " + member_id + " > �� \n�α��� �Ǿ����ϴ�.");
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
						System.out.println("> ȸ��Ż�� �Ϸ�Ǿ����ϴ�.");
						System.out.println();
						new LibraryMainUI();
						flag = false;
						break;
					case 0:
						System.out.println();
						System.out.println("> �ݳ����� ���� ������ �ֽ��ϴ�.");
						System.out.println();
						break;
					case -1:
						System.out.println();
						System.out.println("> ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						System.out.println();
						break;

					default:
						System.out.println("> ȸ��Ż�� �����Ͽ����ϴ�.");
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
					System.out.println("> �޴��� �ش��ϴ� ���ڸ� �Է����ּ���.");
					System.out.println();
				}
			} catch (Exception e) {
				System.out.println();
				System.out.println("> �޴��� �ش��ϴ� ���ڸ� �Է����ּ���.");
				System.out.println();
				sc.nextLine();
			}
		}
	}
	public void printMainMenu() {
		System.out.println("=======================================");
		System.out.println("	     �� ȸ���޴� ��");
		System.out.println("=======================================");
		System.out.println("1.ȸ��Ż��");
		System.out.println("2.�����˻�");
		System.out.println("3.��������");
		System.out.println("4.�����ݳ�");
		System.out.println("5.ȸ������Ȯ��");
		System.out.println("6.������� ��ȸ");
		System.out.println("9.�α׾ƿ�");
		System.out.println("=======================================");
		System.out.print("�޴� ���� > ");
	}

	public void selectAll() {
		ArrayList<Book> list = bdao.selectBookAll();
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
		System.out.println();
	}

	public int deleteMember() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     �� ȸ��Ż�� ��");
		System.out.println("=======================================");
		System.out.print("��й�ȣ ���Է� > ");
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

	public void rentBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     �� �������� ��");
		System.out.println("=======================================");
		System.out.print("> �����Ϸ��� ���� ��ȣ�� �Է��ϼ��� > ");
		int book_number = 0;
		try {
			book_number = sc.nextInt();

			boolean book = bdao.updateStatus(book_number);
			if (book == false) {
				System.out.println();
				System.out.println("> �ش� ������ �������Դϴ�.");
				System.out.println();
				return;
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println("> �ش� ���� ��ȣ�� ���ڷ� �Է����ּ���.");
			System.out.println();
			sc.nextLine();
			return;
		}
		// '����' => '������'����

		Book bookInfo = bdao.selectBookInfo(book_number);
		Rent rent = new Rent();
		rent.setRent_book_number(bookInfo.getBook_number());
		rent.setRent_member_id(member_id);

		boolean rentResult = bdao.insertRent(rent);

		if (rentResult) {
			System.out.println();
			System.out.println("> ������ �Ϸ� �Ǿ����ϴ�.");
			System.out.println();
		} // RENT�� ���� �Է�
	}

	public void returnBook() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	     �� �����ݳ� ��");
		System.out.println("=======================================");
		System.out.print("> �ݳ��Ϸ��� ���� ��ȣ�� �Է��ϼ��� > ");
		int book_return = 0;

		try {
			book_return = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			System.out.println();
			System.out.println("> �ݳ��Ϸ��� ���� ��ȣ�� ���ڷ� �Է����ּ���.");
			System.out.println();
			sc.nextLine();
			return;
		}
		if ((bdao.updateReturnStatus(book_return) && bdao.deleteRent(member_id, book_return)) == true) {
			System.out.println();
			System.out.println("> �ݳ� �Ǿ����ϴ�.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("> �������� ������ �ƴմϴ�.");
			System.out.println();
		}
	}

	public void readMemberInfo() {
		System.out.println();
		System.out.println("=========================================================================================");
		System.out.println("				�� ȸ������ Ȯ�� �� ");
		System.out.println("=========================================================================================");
		System.out.println();
		Member member = mdao.readMember(member_id);
		ArrayList<Rent> list = bdao.readRent(member_id);
		System.out.println("=========================================================================================");
		System.out.println("      ���̵�         |            �̸�              |           �ڵ�����ȣ");
		System.out.println("=========================================================================================");
		System.out.printf("       %s                      %s              		%s\n", member.getMember_id(),
				member.getMember_name(), member.getMember_phone());
		System.out.println("=========================================================================================");
		System.out.println("    �뿩���� ��ȣ    |            �뿩��           |              �ݳ���");
		System.out.println("=========================================================================================");
		for (Rent r : list) {
			System.out.printf("        %d                   %s            %s\n", r.getRent_book_number(),
					r.getRent_rentdate(), r.getRent_returndate());
		}
		System.out.println();
	}

}
