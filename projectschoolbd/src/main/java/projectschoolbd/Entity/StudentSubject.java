package projectschoolbd.Entity;

import java.text.MessageFormat;

public class StudentSubject {
	private long studentSubjectId;
	private long subjectId;
	private long studentId;
	private int period;
	private double grade;
	private int attendance;
	
	public Subject subject;
	public Student student;
	
	public StudentSubject(long studentSubjectId, long subjectId, long studentId, int period, double grade, int attendance) {
		this.studentSubjectId = studentSubjectId;
		this.subjectId = subjectId;
		this.studentId = studentId;
		this.period = period;
		this.grade = grade;
		this.attendance = attendance;
	}
	
	public long getStudentSubjectId() {
		return studentSubjectId;
	}

	public long getSubjectId() {
		return subjectId;
	}

	public long getStudentId() {
		return studentId;
	}

	public int getPeriod() {
		return period;
	}

	public double getGrade() {
		return grade;
	}

	public int getAttendance() {
		return attendance;
	}

	public Subject getSubject() {
		return subject;
	}

	public Student getStudent() {
		return student;
	}

	@Override
	public String toString() {
		return MessageFormat.format("Id: {0} \nDisciplina: {1} \nEstudante: {2} \nPeriodo: {3} \nNota: {4} \nFrequencia: {5}",
				this.studentSubjectId, this.subject.getName(), this.student.getName(), this.period, this.grade, this.attendance);
	}
}
