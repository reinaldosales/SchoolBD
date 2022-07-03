package projectschoolbd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Repository.StudentRepository;
import Repository.StudentSubjectRepository;
import Repository.SubjectRepository;

public class Main {
	public static void main(String[] args) {
		try {
			int choice = showMenuAndGetChoice();

			switch (choice) {
			case 0:
				new StudentRepository().saveStudent();
				break;
			case 1:
				new StudentRepository().updateStudent();
				break;
			case 2:
				new StudentRepository().deleteStudent();
				break;
			case 3:
				new StudentRepository().findStudent();
				break;
			case 4:
				new StudentSubjectRepository().showHistoric();
				break;
			case 5:
				new SubjectRepository().showAllSubjects();
				break;
			case 6:
				new SubjectRepository().saveSubject();
				break;
			case 7:
				new SubjectRepository().updateSubject();
				break;
			case 8:
				new SubjectRepository().deleteStudent();
				break;
			case 9:
				new SubjectRepository().showSubjectByName();
				break;
			

			default:
				throw new Exception("Opção inválida");
			}

		} catch (Exception ex) {
			System.err.println("Error: " + ex.getMessage());
		}
	}

	public static int showMenuAndGetChoice() throws Exception {

		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		try {
			int choice = 0;

			System.out.println("0 - Adicionar Estudante");
			System.out.println("1 - Atualizar Estudante");
			System.out.println("2 - Deletar Estudante");
			System.out.println("3 - Procurar Estudante(s)");
			System.out.println("4 - Exibir historico de estudante");
			System.out.println("5 - Exibir materias");
			System.out.println("6 - Adicionar materia");
			System.out.println("7 - Editar materia");
			System.out.println("8 - Deleter materia");
			System.out.println("9 - Buscar materia por nome");
			System.out.print("Escolha: ");
			choice = input.nextInt();

			return choice;

		} catch (Exception ex) {
			throw ex;
		}
	}

	
}
