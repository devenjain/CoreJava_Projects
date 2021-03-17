import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminFileController {

	File file = new File("DataBase.txt");
	static boolean flag = true;
	static ArrayList<AdminFileBean> list = new ArrayList<>();
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

			System.out.println();
			System.out.println("   Inavalid Name!!!......*Name must be starts with Alphabets");
			System.out.println();
			System.out.print("Enter Admin Name : ");
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
			System.out.println("Inavalid Id!!!......*This id is already registered");
			System.out.print("Enter Id : ");
			id = sc.nextInt();
		}
		return id;
	}

//	age validation
	public static int ageValidation(int age) {
		while (age < 21 || age > 58) {
			System.out.println();
			System.out.println("   Inavalid Age!!!......*Age must be betwwen 21-58");
			System.out.println();
			System.out.print("Enter Id : ");
			age = sc.nextInt();
		}
		return age;
	}

// email validation 
	public static String emailValidation(String email) {
		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

		Pattern p1 = Pattern.compile(regex);// expression
		Matcher m = p1.matcher(email);// input
		boolean e1 = m.matches();
//		System.out.println(e1);
		boolean e2 = emailList.add(email);
		while (e1 != true || e2 != true) {
			System.out.println();
			System.out.println("   Inavalid Email!!!......");
			System.out.println();
			System.out.print("Enter Email : ");
			email = sc.next();
			Matcher m2 = p1.matcher(email);
			e1 = m2.matches();

			if (e1)
				e2 = emailList.add(email);

		}
		return email;
	}

	public static String loginEmailValidation(String email) {

		String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

		Pattern p1 = Pattern.compile(regex);// expression
		Matcher m = p1.matcher(email);// input
		boolean e1 = m.matches();
//		System.out.println(e1);
//		boolean e2 = emailList.add(email);
		while (e1 != true) {
			System.out.println();
			System.out.println("   Inavalid Email!!!......");
			System.out.println();
			System.out.print("Enter Email : ");
			email = sc.next();
			Matcher m2 = p1.matcher(email);
			e1 = m2.matches();

		}
		return email;
	}

//	password validation
	public static String passwordValidation(String password) {

		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,10})";

		Pattern p1 = Pattern.compile(regex);// expression
		Matcher mp = p1.matcher(password);// input
		boolean p = mp.matches();
//		System.out.println(e1);	
		while (p != true || password == null) {
			System.out.println();
			System.out.println(
					"Inavalid Password!!!...\n*Your Password must be greater than 8 and less than 10 character and its contains atleast one digite, Capital letter, small letter, special character");
			System.out.println();
			System.out.print("Enter Password : ");
			password = sc.next();
			Matcher mp1 = p1.matcher(password);
			p = mp1.matches();
		}
		return password;
	}

	public static String loginpasswordValidation(String password) {

		String regex = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,10})";

		Pattern p2 = Pattern.compile(regex);// expression
		Matcher m2 = p2.matcher(password);// input
		boolean e2 = m2.matches();

		if (e2 == false) {
			System.out.println("Invalid Password!!!");
		}
//		System.out.println(e1);	
//		while (e2 != true || password == null) {
//			System.out.println(
//					"Inavalid Password!!!...\n *Your Password must be greater than 8 and less than 10 character and its contains atleast one digite, Capital letter, small letter, special character");
//			System.out.println("Enter Password : ");
//			password = sc.next();
//		}
		return password;
	}

// main function
	public static void main(String[] args) {

		int choice;
		AdminFileDao adminFileDao = new AdminFileDao();

		do {
			System.out.println();
			System.out.println("********************Admin Modual********************");
			System.out.println();
			System.out.println("Enter 1 for Sign Up");
			System.out.println("Enter 2 for Sign In");
			System.out.println("Enter 3 for Exit");
			System.out.print("Enter Your Choice : ");
			choice = sc.nextInt();

			switch (choice) {

			case 1:
				System.out.println();
				System.out.print("Enter Admin Id : ");
				int id = sc.nextInt();

				id = idValidation(id);

				System.out.print("Enter Admin Name : ");
				String name = sc.next();

				name = nameValidation(name);

				System.out.print("Enter Age : ");
				int age = sc.nextInt();

				age = ageValidation(age);

				System.out.print("Enter Email : ");
				String email = sc.next();

				email = emailValidation(email);

				System.out.println("Enter Password : ");
				String password = sc.next();

				password = passwordValidation(password);

//				System.out.println(id + "  " + name + "  " + age + "  " + email + "  " + password);

				adminFileDao.registerAdmin(id, name, email, password, age);

				System.out.println();
				System.out.println("Data Registered...");

				break; // ********************end of case 1*******************************

			case 2:
				System.out.println();
				System.out.println("**************Login your account***************");
				System.out.println();
				System.out.print("Enter Email : ");
				email = sc.next();

				email = loginEmailValidation(email);

				System.out.print("Enter Password : ");
				password = sc.next();

				password = loginpasswordValidation(password);

				adminFileDao.login(email, password);

				if (adminFileDao.getlogin() == true) {
					System.out.println();
					System.out.println("Login SuccessFull");
					System.out.println();
				}

				while (flag == true) {
					if (adminFileDao.getlogin() == true) {
						System.out.println("**************Operations***************");
						System.out.println();
						System.out.println("1 ===== Update Data");
						System.out.println("2 ===== Get Data");
						System.out.println("3 ===== Delete data");
						System.out.println("4 ===== Get data by name");
						System.out.println("5 ===== Get Data Between age");
						System.out.println("6 ===== Exit");
						System.out.print("Enter Your choice : ");
						int ch = sc.nextInt();

						switch (ch) {
						case 1:
							adminFileDao.update(email, password);

							System.out.println();
							System.out.println("Data Updated...");
							flag = true;
							break;

						case 2:

							System.out.println();
							adminFileDao.display();
//							System.out.format("%5s %10s %5s %18s %15s", "Id", "Name", "Age", "Email", "Password");
//							System.out.println();
//							for (int i = 0; i < list.size(); i++) {
//								AdminBean adminBean = list.get(i);
//
//								System.out.format("%5d %10s %5d %18s %15s", adminBean.getid(), adminBean.getname(),
//										adminBean.getage(), adminBean.getemail(), adminBean.getpassword());
//
//								System.out.println();
////								System.out.println();
////								System.out.print(adminBean.getid());
////								System.out.print("   " + adminBean.getname());
////								System.out.print("   " + adminBean.getage());
////								System.out.print("   " + adminBean.getemail());
////								System.out.print("   " + adminBean.getpassword());
////								System.out.println();
//							}
							flag = true;
							break;

						case 3:

							adminFileDao.delete(email, password);
							System.out.println();
//							System.out.println();
							System.out.println("Your Data Deleted...");
							System.out.println();
							flag = false;
							break;

						case 4:

							System.out.println();
							System.out.print("Enter name to see data : ");
							name = sc.next();
							adminFileDao.dataByName(name);
							System.out.println();
							flag = true;
							break;

						case 5:

							System.out.println();
							int lower, upper;

							do {
								System.out.print("Enter Age from : ");
								lower = sc.nextInt();
								System.out.print("Enter Age : ");
								upper = sc.nextInt();
							} while (lower < 22 || upper > 58);
							flag = true;
							adminFileDao.getDataBetween(lower, upper);
							System.out.println();
							break;

						case 6:
//							System.exit(0);-
							flag = false;
							break;

						} // end nested Switch
					} // end inner(login) if

					else {
						System.out.println("Login Failed!!!.........*please check entered email id or password");
						break;
					}

				}

			}// end of switch

		} while (choice != 3 || flag == true);
	}

}
