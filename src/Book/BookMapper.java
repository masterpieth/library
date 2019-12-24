package Book;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Book;
import vo.Rent;

public interface BookMapper {

	public int addBook(Book book);
	public int deleteBook(int no);
	public ArrayList<Book> searchBook(HashMap<String,Object> map);
	public Book selectBookInfo(int book_number);
	public ArrayList<Book> selectBookAll();
	
	public int updateRentStatus(int book_number);
	public int updateReturnStatus(int no);
	public int deleteRent(Rent rent);
	public int insertRent(Rent rent);
	public ArrayList<Rent> readRent(String rent_member_id);
	public int updateReturnDate(HashMap<String, Integer> map);
	public Rent readRentByBookNum(int book_number);
}
