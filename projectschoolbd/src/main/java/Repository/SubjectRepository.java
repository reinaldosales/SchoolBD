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
import projectschoolbd.Entity.Subject;

public class SubjectRepository {

	public void saveSubject() throws Exception {

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
			System.out.print("Creditos: ");
			int credits = input.nextInt();

			sqlCommand = MessageFormat.format("INSERT INTO subject (Name, Credits) VALUES (''{0}'', {1})", name,
					credits);

			stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			ResultSet resultSet = stmt.getGeneratedKeys();

			if (resultSet.next())
				System.out.println("Materia inserida: Id " + resultSet.getInt(1));

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public void updateSubject() throws Exception {
		Database database = null;
		Connection connection = null;
		Scanner input = new Scanner(System.in);

		try {
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			ArrayList<Subject> subjectList = getAllSubjects();

			for (Subject subject : subjectList) {
				System.out.println(subject);
			}

			System.out.print("\nId da materia: ");
			String subjectId = input.nextLine();

			System.out.print("Nome: ");
			String newName = input.nextLine();
			System.out.print("Creditos: ");
			int newCredits = input.nextInt();

			sqlCommand = MessageFormat.format("UPDATE subject SET Name = ''{0}'', Credits = {1} WHERE SubjectId = {2} ",
					newName, newCredits, subjectId);

			stmt.execute(sqlCommand);

			if (stmt.getUpdateCount() > 0)
				System.out.println("\nMateria atualizada com sucesso!\n");

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

			ArrayList<Subject> subjectList = getAllSubjects();

			for (Subject subject : subjectList) {
				System.out.println(subject);
			}

			System.out.print("\nId da materia: ");
			String subjectId = input.nextLine();

			sqlCommand = MessageFormat.format("DELETE FROM subject WHERE SubjectId = {0}", subjectId);

			stmt.execute(sqlCommand);

			System.out.println("\nEstudante removido com sucesso!\n");

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public static ArrayList<Subject> getAllSubjects() throws Exception {
		Database database = null;
		Connection connection = null;

		try {
			ArrayList<Subject> subjectList = new ArrayList<Subject>();
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			sqlCommand = "SELECT * FROM subject";

			stmt.execute(sqlCommand);

			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				int subjectId = resultSet.getInt("SubjectId");
				String name = resultSet.getString("Name");
				int credits = resultSet.getInt("Credits");

				Subject subject = new Subject(subjectId, name, credits);

				subjectList.add(subject);
			}

			return subjectList;

		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}

	}

	public Subject getSubjectById(long id) throws Exception {
		Database database = null;
		Connection connection = null;
		Subject subject = null;

		try {

			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			sqlCommand = MessageFormat.format("SELECT * FROM subject WHERE SubjectId = {0}", id);

			stmt.execute(sqlCommand);

			ResultSet resultSet = stmt.getResultSet();

			while (resultSet.next()) {
				long subjectId = resultSet.getLong("SubjectId");
				String name = resultSet.getString("Name");
				int credits = resultSet.getInt("Credits");

				subject = new Subject(subjectId, name, credits);

				return subject;
			}

			return subject;
		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}

	public ArrayList<Subject> getSubjectsByName(String name) throws Exception {
		try {

			ArrayList<Subject> subjectWithName = new ArrayList<Subject>();
			ArrayList<Subject> subjectList = getAllSubjects();

			for (Subject subject : subjectList) {
				if (subject.getName().contains(name))
					subjectWithName.add(new Subject(subject.getSubjectId(), subject.getName(), subject.getCredits()));
			}
			
			return subjectWithName;

		} catch (Exception ex) {
			throw ex;
		}
	}

	public void showSubjectByName() throws Exception {
		Scanner input = new Scanner(System.in);
		
		try {
			
			System.out.print("\nNome da disciplina: ");
			String name = input.nextLine();
			
			ArrayList<Subject> subjectList = getSubjectsByName(name);

			for (Subject subject : subjectList) {
				System.out.println(subject);
			}

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	public void showAllSubjects() throws Exception {
		try {

			ArrayList<Subject> subjectList = getAllSubjects();

			for (Subject subject : subjectList) {
				System.out.println(subject);
			}

		} catch (Exception ex) {
			throw ex;
		}
	}
}
