package Member;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import resource.MybatisConfig;
import vo.Member;

public class MemberDAO {

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();
	
	public boolean insertMember(Member member) {
		SqlSession session = null;
		int cnt = 0;
		
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.insertMember(member);
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		if(cnt == 1) return true;
		return false;
	}
	public int deleteMember(String member_id, String member_pw) {
		SqlSession session = null;
		int cnt = 0;
		Member member = new Member();
		member.setMember_id(member_id);
		member.setMember_pw(member_pw);
		
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			cnt = mapper.deleteMember(member);
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return cnt;
	}
	public ArrayList<Member> selectMember(){
		SqlSession session = null;
		ArrayList<Member> list = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			list = mapper.selectMember();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return list;
	}
	public Member readMember(String member_id){
		SqlSession session = null;
		Member member = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member = mapper.readMember(member_id);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return member;
	}
	public Member readMemberByPhone(String member_phone){
		SqlSession session = null;
		Member member = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			member = mapper.readMemberByPhone(member_phone);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return member;
	}
	public Member readMemberByIdPw(Member member){
		SqlSession session = null;
		Member result = null;
		try {
			session = factory.openSession();
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			result = mapper.readMemberByIdPw(member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return result;
	}
}
