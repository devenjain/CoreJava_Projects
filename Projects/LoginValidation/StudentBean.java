
public class StudentBean {
	private int id;
	private String email;
	private String name;
	private String password;
	private int age;

	public void getid(int id) {
		this.id = id;
	}

	public int setid() {
		return id;
	}

	public void getname(String name) {
		this.name = name;
	}

	public String setname() {
		return name;
	}

	public void getemail(String email) {
		this.email = email;
	}

	public String setemail() {
		return email;
	}

	public void getpassword(String password) {
		this.password = password;
	}

	public String setpassword() {
		return password;
	}

	public void getage(int age) {
		this.age = age;
	}

	public int setage() {
		return age;
	}

}
