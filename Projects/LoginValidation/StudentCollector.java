import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentCollector {
	static ArrayList<StudentBean> list = new ArrayList<>();
	static ArrayList<String> namelist = new ArrayList<String>();
	static TreeSet<String> emailList = new TreeSet<String>();
	static TreeSet<Integer> idList = new TreeSet<>();

//	static static StudentBean bean = null;
	static Scanner sc = new Scanner(System.in);

// name validation 
	public static String nameValidation(String name) {
		Pattern pname = Pattern.compile("^[A-Za-z]\\w*$");
		Matcher m = pname.matcher(name);
		boolean n1 = m.matches();
		boolean n2 = namelist.add(name);

		while (n1 != true || n2 != true) {

			System.out.println("Inavalid Name!!!......*Name must be starts with Alphabets");
			System.out.println("Enter Admin Name : ");
			name = sc.next();
			// Pattern p1 = Pattern.compile("^[A-Za-z]\\w*$");
			Matcher m2 = pname.matcher(name);
			n1 = m2.matches();

			if (n1) {
				n2 = namelist.add(name);
			}

		}

		return name;
	}

//	id validation
	public static int idValidation(int id) {
		while (idList.add(id) != true) {
			System.out.println("Inavalid Id!!!......*Thid id is already registered");
			System.out.println("Enter Id : ");
			id = sc.nextInt();
		}
		return id;
	}

// email validation 
	public static String emailValidation(String email) {
		Pattern p = Pattern.compile("[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]"
				+ "+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*");// expression
		Matcher m = p.matcher(email);// input
		boolean b = m.matches();
		System.out.println(b);

		return email;
	}

	public static void main(String[] args) {

		int choice;
		StudentDao dao = new StudentDao();
		do {

			System.out.println("Enter 1 for Register Data:-");
			System.out.println("Enter 2 for delete Data:-");
			System.out.println("Enter 3 for view Data:-");
			System.out.println("Enter 4 for update Data:-");
			System.out.println("Enter your choice :-");

			choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println("Enter Admin Id : ");
				int id = sc.nextInt();

				idValidation(id);

				System.out.println("Enter Admin Name : ");
				String name = sc.next();

				nameValidation(name);

				System.out.println("Enter Email : ");
				String email = sc.next();

				emailValidation(email);

				break; // ********************end of case 1*******************************

			}// end of switch

		} while (choice != 5);
	}
}
