package vo;

public class Book {

	private String book_name;
	private int book_number;
	private String book_author;
	private String book_publisher;
	private String book_status;

	public Book() {
		super();
	}

	public Book(String book_name, int book_number, String book_author, String book_publisher, String book_status) {
		super();
		this.book_name = book_name;
		this.book_number = book_number;
		this.book_author = book_author;
		this.book_publisher = book_publisher;
		this.book_status = book_status;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public int getBook_number() {
		return book_number;
	}

	public void setBook_number(int book_number) {
		this.book_number = book_number;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBook_status() {
		return book_status;
	}

	public void setBook_status(String book_status) {
		this.book_status = book_status;
	}

	@Override
	public String toString() {
		return "Book [book_name=" + book_name + ", book_number=" + book_number + ", book_author=" + book_author
				+ ", book_publisher=" + book_publisher + ", book_status=" + book_status + "]";
	}

}
