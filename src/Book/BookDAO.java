package Book;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import resource.MybatisConfig;
import vo.Book;
import vo.Rent;

public class BookDAO {

	SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public boolean updateStatus(int book_number) {

		SqlSession session = null;
		int num = 0;

		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.updateRentStatus(book_number);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

	public boolean addBook(Book book) {

		SqlSession session = null;
		int num = 0;

		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.addBook(book);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

	public boolean deleteBook(int no) {

		SqlSession session = null;
		int num = 0;

		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.deleteBook(no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

	public boolean updateReturnStatus(int no) {

		SqlSession session = null;
		int num = 0;

		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.updateReturnStatus(no);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

	public ArrayList<Book> searchBook(int sel, String word) {
		SqlSession session = null;
		ArrayList<Book> list = null;
		HashMap<String, Object> map = null;

		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			map = new HashMap<String, Object>();
			map.put("sel", sel);
			map.put("words", word);
			list = mapper.searchBook(map);
			session.commit();
		} catch (Exception e) {
//			System.out.println("> 올바른 값을 입력해주세요.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public boolean insertRent(Rent rent) {
		SqlSession session = null;
		int num = 0;
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.insertRent(rent);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (num > 0)
			return true;
		else
			return false;
	}

	public Book selectBookInfo(int book_number) {
		SqlSession session = null;
		Book book = null;
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			book = mapper.selectBookInfo(book_number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public boolean deleteRent(String rent_member_id, int rent_book_number) {
		SqlSession session = null;
		int cnt = 0;
		Rent rent = new Rent();
		rent.setRent_member_id(rent_member_id);
		rent.setRent_book_number(rent_book_number);
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			cnt = mapper.deleteRent(rent);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}

		if (cnt > 0)
			return true;
		else
			return false;

	}

	public ArrayList<Rent> readRent(String rent_member_id) {
		SqlSession session = null;
		ArrayList<Rent> list = null;
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			list = mapper.readRent(rent_member_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	public Rent readRentByBookNum(int book_number) {
		SqlSession session = null;
		Rent rent = null;
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			rent = mapper.readRentByBookNum(book_number);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return rent;
	}

	public int updateReturnDate(int day, int book_number) {
		SqlSession session = null;
		int num = 0;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("day", day);
		map.put("bookNum", book_number);
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			num = mapper.updateReturnDate(map);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return num;
	}

	public ArrayList<Book> selectBookAll() {
		SqlSession session = null;
		ArrayList<Book> list = null;
		try {
			session = factory.openSession();
			BookMapper mapper = session.getMapper(BookMapper.class);
			list = mapper.selectBookAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

}
