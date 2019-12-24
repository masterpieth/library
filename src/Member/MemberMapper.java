package Member;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Member;

public interface MemberMapper {

	public int insertMember(Member member);
	public int deleteMember(Member member); 
	public ArrayList<Member> selectMember();
	public Member readMember(String member_id);
	public Member readMemberByPhone (String member_phone);
	public Member readMemberByIdPw (Member member);
}
