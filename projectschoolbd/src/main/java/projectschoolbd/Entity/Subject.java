package projectschoolbd.Entity;

import java.text.MessageFormat;

public class Subject {
	private long subjectId;
	private String name;
	private int credits;
	
	public Subject(long subjectId, String name, int credits) {
		this.subjectId = subjectId;
		this.name = name;
		this.credits = credits;
	}
	
	

	public long getSubjectId() {
		return subjectId;
	}

	public String getName() {
		return name;
	}

	public int getCredits() {
		return credits;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("Id: {0} \nNome: {1} \nCredits: {2}",
				this.subjectId, this.name, this.credits);
	}
}
