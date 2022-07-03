package projectschoolbd.Entity;

import java.text.MessageFormat;

public class Student {
	private long studentId;
	private String name;
	private String email;
	private long code;
	private String phone;
	private String gender;
	private String birthday;
	
	public Student(long studentId, String name, String email, long code, String phone, String gender, String birthday) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.code = code;
		this.phone = phone;
		this.gender = gender;
		this.birthday = birthday;
	}
	
	public long getStudentId() {
		return this.studentId;
	}

	public String getName() {
		return this.name;
	}

	public String getEmail() {
		return this.email;
	}

	public long getCode() {
		return this.code;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getGender() {
		return this.gender;
	}

	public String getBirthday() {
		return this.birthday;
	}

	@Override
	public String toString() {
		return MessageFormat.format("\nId: {0} \nNome: {1} \nE-mail: {2} \nCodigo: {3} \nTelefone: {4} \nSexo: {5} \nData nascimento: {6}",
				this.studentId, this.name, this.email, this.code, this.phone, this.gender, this.birthday);
	}
}
