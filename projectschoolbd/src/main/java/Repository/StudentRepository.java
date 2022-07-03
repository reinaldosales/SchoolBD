package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import projectschoolbd.Database;
import projectschoolbd.Entity.Student;

public class StudentRepository {

	public void saveStudent() throws Exception {

		Database database = null;
		Connection connection = null;
		Scanner input = new Scanner(System.in);

		try {
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			System.out.print("Nome: ");
			String name = input.nextLine();
			System.out.print("E-mail: ");
			String email = input.nextLine();
			System.out.print("Phone: ");
			String phone = input.nextLine();
			System.out.print("Gender (F/M): ");
			String gender = input.nextLine();
			System.out.print("Birthday (yyyy-MM-dd): ");
			String birthday = input.nextLine();
			long code = new Random().nextLong(100, 100000);

			sqlCommand = MessageFormat.format(
					"INSERT INTO student (Name, Email, Code, Phone, Gender, Birthday) VALUES (''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'')",
					name, email, code, phone, gender, birthday);

			stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next())
				System.out.println("Estudante inserido: Id " + resultSet.getInt(1));
		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public void updateStudent() throws Exception {
		Database database = null;
		Connection connection = null;
		Scanner input = new Scanner(System.in);

		try {
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			ArrayList<Student> studentList = getAllStudents();

			for (Student student : studentList) {
				System.out.println(student);
			}

			System.out.print("\nId do estudante: ");
			String studentId = input.nextLine();
			System.out.print("Nome: ");
			String newName = input.nextLine();
			System.out.print("E-mail: ");
			String newEmail = input.nextLine();
			System.out.print("Telefone: ");
			String newPhone = input.nextLine();
			System.out.print("Sexo (M/F): ");
			String newGender = input.nextLine();
			System.out.print("Data nascimento (yyyy-MM-dd): ");
			String newBirthday = input.nextLine();

			sqlCommand = MessageFormat.format(
					"UPDATE student SET Name = ''{0}'', Email = ''{1}'', Phone = ''{2}'', Gender = ''{3}'', Birthday = ''{4}'' WHERE StudentId = {5}",
					newName, newEmail, newPhone, newGender, newBirthday, studentId);

			stmt.execute(sqlCommand);

			if (stmt.getUpdateCount() > 0)
				System.out.println("\nEstudante atualizado com sucesso!\n");

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public void deleteStudent() throws Exception {
		Database database = null;
		Connection connection = null;
		Scanner input = new Scanner(System.in);

		try {
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			ArrayList<Student> studentList = getAllStudents();

			for (Student student : studentList) {
				System.out.println(student);
			}

			System.out.print("\nId do estudante: ");
			String studentId = input.nextLine();

			sqlCommand = MessageFormat.format("DELETE FROM student WHERE StudentId = {0}", studentId);

			stmt.execute(sqlCommand);

			System.out.println("\nEstudante removido com sucesso!\n");

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public static ArrayList<Student> getAllStudents() throws Exception {
		Database database = null;
		Connection connection = null;

		try {
			ArrayList<Student> studentList = new ArrayList<Student>();
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			sqlCommand = "SELECT * FROM Student";

			stmt.execute(sqlCommand);

			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				int studentId = resultSet.getInt("StudentId");
				String name = resultSet.getString("Name");
				String email = resultSet.getString("Email");
				long code = resultSet.getLong("Code");
				String phone = resultSet.getString("Phone");
				String gender = resultSet.getString("Gender");
				String birthday = resultSet.getString("Birthday");

				Student student = new Student(studentId, name, email, code, phone, gender, birthday);

				studentList.add(student);
			}

			return studentList;

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}

	}

	public static void showAllStudents() throws Exception {
		try {
			ArrayList<Student> studentList = getAllStudents();

			for (Student student : studentList) {
				System.out.println(student);
			}

		} catch (Exception ex) {
			throw ex;
		}
	}

	public void findStudent() throws Exception {
		Scanner input = new Scanner(System.in);

		try {
			ArrayList<Student> studentList = getAllStudents();

			System.out.print("\nNome ou E-mail do estudante: ");
			String nameOrEmail = input.nextLine();

			for (Student student : studentList) {
				if (student.getEmail().contains(nameOrEmail) || student.getName().contains(nameOrEmail))
					System.out.println(student);
			}

		} catch (Exception ex) {
			throw ex;
		}
	}

	public Student getStudentById(long id) throws Exception {
		Database database = null;
		Connection connection = null;
		Student student = null;

		try {

			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			sqlCommand = MessageFormat.format("SELECT * FROM student WHERE StudentId = {0}", id);

			stmt.execute(sqlCommand);

			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				int studentId = resultSet.getInt("StudentId");
				String name = resultSet.getString("Name");
				String email = resultSet.getString("Email");
				long code = resultSet.getLong("Code");
				String phone = resultSet.getString("Phone");
				String gender = resultSet.getString("Gender");
				String birthday = resultSet.getString("Birthday");

				student = new Student(studentId, name, email, code, phone, gender, birthday);

				return student;
			}

			return null;

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}
}
