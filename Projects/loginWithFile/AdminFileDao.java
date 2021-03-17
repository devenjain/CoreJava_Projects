
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminFileDao {

	ArrayList<AdminFileBean> data = new ArrayList<>();
	ArrayList<AdminFileBean> fileData = new ArrayList<>();

	Scanner sc = new Scanner(System.in);
	AdminFileController adminController = new AdminFileController();

	static boolean log = false;
	static Random random = new Random();

	public void setlogin(boolean log) {
		this.log = log;
	}

	public boolean getlogin() {
		return log;
	}

	public void send(String to, String subject, String msg) {

		final String user = "jspservlet6@gmail.com";
		final String pass = "jsp@1234";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", pass);

		props.put("mail.smtp.port", 587);

		props.put("mail.debug", "false"); // optional
		props.put("mail.smtp.auth", "true");

		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSl.enable", "true");

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

				return new javax.mail.PasswordAuthentication(user, pass);
			}

		});

//		System.out.println("**********************************************");

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(msg);

			Transport.send(message);
//			System.out.println("done.........");

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void registerAdmin(int id, String name, String email, String password, int age) {

		AdminFileBean adminFileBean = new AdminFileBean();
		adminFileBean.setid(id);
		adminFileBean.setname(name);
		adminFileBean.setage(age);
		adminFileBean.setemail(email);
		adminFileBean.setpassword(password);

		data.add(adminFileBean);

		try {

			FileOutputStream fileOutputStream = new FileOutputStream("AdminData.ser");

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(data);
			objectOutputStream.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} // end register function

	public void login(String email, String password) {
		for (int i = 0; i < data.size(); i++) {
			AdminFileBean adminFileBean = data.get(i);
//			boolean log = false;
			if (email.equals(adminFileBean.getemail()) && password.equals(adminFileBean.getpassword())) {
				setlogin(true);
				break;
			}
		}

		if (log == true) {

		} else {

			System.out.println("1. Forgott Password??");
			System.out.println("2. Try Again");
			System.out.println("3. Exit");
			int ch = sc.nextInt();

			switch (ch) {

			case 1:

//					String newpwd = String.format("%04d", random.nextInt(10000));
				send(email, "1234", "otp messege....");
				String id = String.format("%04d", random.nextInt(10000));
				String p1 = email.substring(0, 1).toUpperCase();
				String p2 = email.substring(1, 4).concat("@").concat(id);
				String newpwd = p1.concat(p2);
				System.out.println();
				System.out.println("Your password is generating and we are sending you mail...please wait");
				send(email, newpwd, "Your new Password is : ");
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
				System.out.println("your password created and sent mail....please enter received password...");
				System.out.println();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.print("Enter Password : ");
				password = sc.next();

				if (password.equals(newpwd)) {
					System.out.println();
//					System.out.println("Login Successfull");
					setlogin(true);
				} else {
					System.out.println();
					System.out.println("Incorrect password......login failed");
					setlogin(false);
					break;
				}

				break;

			case 2:
				setlogin(false);
				break;

//				case 3:

			} // end switch

		} // end else

	} // end login function

	public void update(String email, String password) {

//		AdminFileBean adminFileBean = new AdminFileBean();

		String copy = email;
		int index = 0;
		for (int i = 0; i < data.size(); i++) {
			AdminFileBean adminFileBean = data.get(i);
//				boolean log = false;
			if (copy.equals(adminFileBean.getemail())) {
				index = i;
				break;
			}
		}

		System.out.println("Enter Admin Id : ");
		int id = sc.nextInt();

		id = adminController.idValidation(id);

		System.out.println("Enter Admin Name : ");
		String name = sc.next();

		name = adminController.nameValidation(name);

		System.out.println("Enter Age : ");
		int age = sc.nextInt();

		age = adminController.ageValidation(age);

		System.out.println("Enter Email : ");
		email = sc.next();

		email = adminController.emailValidation(email);

		System.out.println("Enter Password : ");
		password = sc.next();

		password = adminController.passwordValidation(password);

		AdminFileBean adminFileBean = new AdminFileBean();

		adminFileBean.setid(id);
		adminFileBean.setname(name);
		adminFileBean.setage(age);
		adminFileBean.setemail(email);
		adminFileBean.setpassword(password);

		data.set(index, adminFileBean);
		/*
		 * for (int i = 0; i < data.size(); i++) { adminFileBean = data.get(i);
		 * 
		 * System.out.println(); System.out.print(adminFileBean.getid());
		 * System.out.print("   " + adminFileBean.getname()); System.out.print("   " +
		 * adminFileBean.getage()); System.out.print("   " + adminFileBean.getemail());
		 * System.out.print("   " + adminFileBean.getpassword()); System.out.println();
		 */
		display();

//		System.out.println("Data Updated...");
	} // end Update Function

	public void display() {

		try {

			FileInputStream fileInputStream = new FileInputStream("AdminData.ser");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			fileData = (ArrayList<AdminFileBean>) objectInputStream.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("======================================================================");
		System.out.println();
		System.out.format("%5s | %-10s | %5s | %-25s | %-15s", "Id", "Name", "Age", "Email", "Password");
		System.out.println();
		System.out.println("   ----------------------------------------------------------------");
		System.out.println();
		for (int i = 0; i < fileData.size(); i++) {
			AdminFileBean adminFileBean = fileData.get(i);

			System.out.format("%5s | %-10s | %5s | %-25s | %-15s", adminFileBean.getid(), adminFileBean.getname(),
					adminFileBean.getage(), adminFileBean.getemail(), adminFileBean.getpassword());

			System.out.println();

		}
		System.out.println();
		System.out.println("======================================================================");
		System.out.println();

	}

	public void delete(String email, String password) {
		AdminFileBean adminFileBean = new AdminFileBean();

		for (int i = 0; i < data.size(); i++) {
			adminFileBean = data.get(i);

			if (email.equals(adminFileBean.getemail())) {
				adminController.emailList.remove(email);
				adminController.idList.remove(adminFileBean.getid());
				data.remove(i);
//				System.out.println("Data Deleted...");
				break;
			}
		}
	}

	public void dataByName(String name) {
		for (int i = 0; i < data.size(); i++) {
			AdminFileBean adminFileBean = data.get(i);

			if (name.equals(adminFileBean.getname())) {

				System.out.println();
				System.out.print(adminFileBean.getid());
				System.out.print("   " + adminFileBean.getname());
				System.out.print("   " + adminFileBean.getage());
				System.out.print("   " + adminFileBean.getemail());
				System.out.print("   " + adminFileBean.getpassword());
				System.out.println();

				break;
			}
		}
	}

	public void getDataBetween(int lower, int upper) {
		for (int i = 0; i < data.size(); i++) {
			AdminFileBean adminFileBean = data.get(i);

			if (adminFileBean.getage() >= lower && adminFileBean.getage() <= upper) {

				System.out.println();
				System.out.print(adminFileBean.getid());
				System.out.print("   " + adminFileBean.getname());
				System.out.print("   " + adminFileBean.getage());
				System.out.print("   " + adminFileBean.getemail());
				System.out.print("   " + adminFileBean.getpassword());
				System.out.println();

			}
		}
	}
}
