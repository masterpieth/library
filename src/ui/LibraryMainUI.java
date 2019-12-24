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
							System.out.println("> 잘못된 비밀번호입니다.");
							System.out.println();
						} else {
							System.out.println("> 가입되지 않은 아이디입니다.");
							System.out.println();
						}
					} catch (Exception e) {
						System.out.println("> 등록된 정보가 없습니다.");
						System.out.println();
						break;
					}
					break;
				case 2:
					signIn();
					break;
				case 9:
					System.out.println("> 프로그램을 종료합니다.");
					return;
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
		System.out.println("	   ∥ SCIT-C 도서관 ∥");
		System.out.println("=======================================");
		System.out.println("1.로그인");
		System.out.println("2.회원가입");
		System.out.println("9.프로그램 종료");
		System.out.println("=======================================");
		System.out.print("메뉴 선택 > ");
	}

	public Member login() {
		System.out.println();
		System.out.println("=======================================");
		System.out.println("	      ∥ 로그인 ∥ ");
		System.out.println("=======================================");
		System.out.print("아이디 > ");
		String member_id = sc.nextLine();
		member_id = member_id.trim();
		System.out.print("비밀번호 > ");
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
		System.out.println("	      ∥ 회원가입 ∥ ");
		System.out.println("=======================================");
		System.out.print("1.아이디 > ");
		String member_id = sc.nextLine();
		member_id = member_id.trim();
		System.out.print("2.비밀번호 > ");
		String member_pw = sc.nextLine();
		member_pw = member_pw.trim();
		System.out.print("3.이름 > ");
		String member_name = sc.nextLine();
		System.out.print("4.휴대폰 > ");
		String member_phone = sc.nextLine();

		Member member = new Member(member_id, member_pw, member_name, member_phone);

		Member memberCheckById = searchMember(member.getMember_id());
		Member memberCheckByPhone = searchMemberByPhone(member.getMember_phone());
		if (memberCheckById == null && memberCheckByPhone == null) {
			boolean result = mdao.insertMember(member);
			if (result)
				System.out.println();
			System.out.println("> 가입 완료 ");
			System.out.println();
		} else if(memberCheckById != null && memberCheckByPhone == null){
			System.out.println();
			System.out.println("> 중복된 아이디입니다.");
			System.out.println();
		} else if(memberCheckById == null && memberCheckByPhone != null) {
			System.out.println();
			System.out.println("> 중복된 전화번호입니다.");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("> 중복된 회원입니다.");
			System.out.println();
		}

	}

}
