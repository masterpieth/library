package vo;

import java.util.List;

public class Rent {

	private int rent_no;
	private String rent_member_id;
	private int rent_book_number;
	private String rent_rentdate;
	private String rent_returndate;

	public Rent() {
		super();
	}

	public Rent(int rent_no, String rent_member_id, int rent_book_number, String rent_rentdate,
			String rent_returndate) {
		super();
		this.rent_no = rent_no;
		this.rent_member_id = rent_member_id;
		this.rent_book_number = rent_book_number;
		this.rent_rentdate = rent_rentdate;
		this.rent_returndate = rent_returndate;
	}

	public int getRent_no() {
		return rent_no;
	}

	public void setRent_no(int rent_no) {
		this.rent_no = rent_no;
	}

	public String getRent_member_id() {
		return rent_member_id;
	}

	public void setRent_member_id(String rent_member_id) {
		this.rent_member_id = rent_member_id;
	}

	public int getRent_book_number() {
		return rent_book_number;
	}

	public void setRent_book_number(int rent_book_number) {
		this.rent_book_number = rent_book_number;
	}

	public String getRent_rentdate() {
		return rent_rentdate;
	}

	public void setRent_rentdate(String rent_rentdate) {
		this.rent_rentdate = rent_rentdate;
	}

	public String getRent_returndate() {
		return rent_returndate;
	}

	public void setRent_returndate(String rent_returndate) {
		this.rent_returndate = rent_returndate;
	}


	@Override
	public String toString() {
		return "Rent [rent_no=" + rent_no + ", rent_member_id=" + rent_member_id + ", rent_book_number="
				+ rent_book_number + ", rent_rentdate=" + rent_rentdate + ", rent_returndate=" + rent_returndate + "]";
	}

	
}
