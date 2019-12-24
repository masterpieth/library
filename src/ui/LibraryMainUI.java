package ui;

import java.util.Scanner;

import Member.MemberDAO;
import vo.Member;

public class LibraryMainUI {

	private Member member = null;
	private Scanner sc = new Scanner(System.in);
	private MemberDAO mdao = new MemberDAO();

	public LibraryMainUI() {

		while (true) {
			printMainMenu();
			int choice = 0;
			try {
				choice = sc.nextInt();
				sc.nextLine();
				switch (choice) {
				case 1:
					member = login();
					Member loginResult = null;
					try {
						loginResult = searchMember(member.getMember_id());
						if (member.getMember_id().equals("admin") && member.getMember_pw().equals("admin")) {
							new LibraryAdminUI();
							return;
						} else if (member.getMember_id().equals(loginResult.getMember_id())
								&& member.getMember_pw().equals(loginResult.getMember_pw())) {
							new LibraryUserUI(member);
							return;
						} else if (member.getMember_id().equals(loginResult.getMember_id())
								&& !(member.getMember_pw().equals(loginResult.getMember_pw()))) {
							System.out.println("> �߸��� ��й�ȣ�Դϴ�.");
							System.out.println();
						} else {
							System.out.println("> ���Ե��� ���� ���̵��Դϴ�.");
							System.out.println();
						}
					} catch (Exception e) {
						System.out.println("> ��ϵ� ������ �����ϴ�.");
						System.out.println();
						break;
					}
					break;
				case 2:
					signIn();
					break;
				case 9:
					System.out.println("> ���α׷��� �����մϴ�.");
					return;
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
		System.out.println("	   �� SCIT-C ������ ��");
		System.out.println("=======================================");
		System.out.println("1.�α���");
		System.out.println("2.ȸ������");
		System.out.println("9.���α׷� ����");
		System.out.println("=======================================");
		System.out.print("�޴� ���� > ");
	}

	public Member login() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	      �� �α��� �� ");
		System.out.println("=======================================");
		System.out.print("���̵� > ");
		String member_id = sc.nextLine();
		member_id = member_id.trim();
		System.out.print("��й�ȣ > ");
		String member_pw = sc.nextLine();
		member_pw = member_pw.trim();
		System.out.println();
		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);
		return member;
	}

	public Member searchMember(String member_id) {
		Member result = mdao.readMember(member_id);
		return result;
	}
	public Member searchMemberByPhone(String member_phone) {
		Member result = mdao.readMemberByPhone(member_phone);
		return result;
	}

	public void signIn() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	      �� ȸ������ �� ");
		System.out.println("=======================================");
		System.out.print("1.���̵� > ");
		String member_id = sc.nextLine();
		member_id = member_id.trim();
		System.out.print("2.��й�ȣ > ");
		String member_pw = sc.nextLine();
		member_pw = member_pw.trim();
		System.out.print("3.�̸� > ");
		String member_name = sc.nextLine();
		System.out.print("4.�޴��� > ");
		String member_phone = sc.nextLine();

		Member member = new Member(member_id, member_pw, member_name, member_phone);

		Member memberCheckById = searchMember(member.getMember_id());
		Member memberCheckByPhone = searchMemberByPhone(member.getMember_phone());
		if (memberCheckById == null && memberCheckByPhone == null) {
			boolean result = mdao.insertMember(member);
			if (result)
				System.out.println();
			System.out.println("> ���� �Ϸ� ");
			System.out.println();
		} else if(memberCheckById != null && memberCheckByPhone == null){
			System.out.println();
			System.out.println("> �ߺ��� ���̵��Դϴ�.");
			System.out.println();
		} else if(memberCheckById == null && memberCheckByPhone != null) {
			System.out.println();
			System.out.println("> �ߺ��� ��ȭ��ȣ�Դϴ�.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("> �ߺ��� ȸ���Դϴ�.");
			System.out.println();
		}

	}

}
