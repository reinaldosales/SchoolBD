package Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.mysql.fabric.xmlrpc.base.Array;

import projectschoolbd.Database;
import projectschoolbd.Entity.Student;
import projectschoolbd.Entity.StudentSubject;
import projectschoolbd.Entity.Subject;

public class StudentSubjectRepository {
	
	public void showHistoric() throws Exception {

		Database database = null;
		Connection connection = null;
		Scanner input = new Scanner(System.in);

		try {
			String sqlCommand = null;

			database = new Database();
			connection = database.connect();
			Statement stmt = connection.createStatement();

			ArrayList<StudentSubject> studentSubjectList = new ArrayList<StudentSubject>();
			ArrayList<Student> studentList = StudentRepository.getAllStudents();
			
			for (Student student : studentList) {
				System.out.println(student);
			}
			
			System.out.print("\nId do estudante: ");
			String id = input.nextLine();

			sqlCommand = MessageFormat.format("SELECT * FROM studentsubject WHERE StudentId = {0}", id);
			
			stmt.execute(sqlCommand);

			ResultSet resultSet = stmt.getResultSet();
			
			while (resultSet.next()) {
				long studentSubjectId = resultSet.getLong("StudentSubjectId");
				long studentId = resultSet.getLong("StudentId");
				long subjectId = resultSet.getLong("SubjectId");
				int period = resultSet.getInt("Period");
				double grade = resultSet.getDouble("Grade");
				int attendance = resultSet.getInt("Attendance");
				
				StudentSubject studentSubject = new StudentSubject(studentSubjectId, subjectId, studentId, period, grade, attendance);
						
				Student student = new StudentRepository().getStudentById(studentId);
				Subject subject = new SubjectRepository().getSubjectById(subjectId);
				
				studentSubject.student = student;
				studentSubject.subject = subject;

				studentSubjectList.add(studentSubject);
			}
			
			for (StudentSubject studentSubject : studentSubjectList) {
				System.out.println("\n" + studentSubject);
			}
			
		} catch (Exception ex) {
			throw ex;
		} finally {
			database.disconnect(connection);
		}
	}
}
