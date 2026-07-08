package com.udemy.cruddemo;

import com.udemy.cruddemo.dao.StudentDAO;
import com.udemy.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};

	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println(numRowsDeleted + " rows deleted");
	}

	private void deleteStudent(StudentDAO studentDAO) {
//		Student unwantedStudent = new Student("Unwanted", "Student", "nothere@gmail.com");
//		studentDAO.save(unwantedStudent);
//		System.out.println(unwantedStudent);

		Student unwantedStudent = studentDAO.findById(7);
		System.out.println(unwantedStudent);
		studentDAO.delete(7);

	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve the student based on the ID
		List<Student> theStudents = studentDAO.findByFirstAndLastName("Tina", "Belcher");

		// change the  name
		// update the student
		for (Student student : theStudents) {
			student.setLastName("Pesto");
			studentDAO.update(student);
		}

		// display updated student
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> theStudents = studentDAO.findByLastName("Pesto");
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {

		// get list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating the student");
		Student jimmy = new Student("Jimmy", "Pesto", "jimmy@gmail.com");

		// save the student
		System.out.println("Saving the student");
		studentDAO.save(jimmy);

		// display id of the saved student
		Integer jimmyId = jimmy.getId();
		System.out.println("Saved the student, generated ID: " + jimmyId);

		// retrieve student based on the id
		System.out.println("\nRetrieving student with id: " + jimmyId);
		Student foundStudent = studentDAO.findById(jimmyId);

		System.out.println("Found the student: " + foundStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating the Belchers");
		Student bobBelcher = new Student("Bob", "Belcher", "bbelcher@gmail.com");
		Student lindaBelcher = new Student("Linda", "Belcher", "lbelcher@gmail.com");
		Student tinaBelcher = new Student("Tina", "Belcher", "tbelcher@gmail.com");
		Student geneBelcher = new Student("Gene", "Belcher", "gbelcher@gmail.com");
		Student louiseBelcher = new Student("Louise", "Belcher", "littlebelcher@gmail.com");

		// save the student objects
		System.out.println("Saving the Belchers");
		studentDAO.save(bobBelcher);
		studentDAO.save(lindaBelcher);
		studentDAO.save(tinaBelcher);
		studentDAO.save(geneBelcher);
		studentDAO.save(louiseBelcher);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Paul", "Wilson", "pwilson@gmail.com");

		// save the student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());

	}

}
