package com.udemy.cruddemo;

import com.udemy.cruddemo.dao.AppDAO;
import com.udemy.cruddemo.entity.Course;
import com.udemy.cruddemo.entity.Instructor;
import com.udemy.cruddemo.entity.InstructorDetail;
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
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetail(appDAO);
//			deleteInstructorDetail(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourse(appDAO);
			findCoursesForInstructor(appDAO);
		};

	}

	// Fixes lazy load error
	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding courses by instructor id " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("instructor: " + instructor);

		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("the courses: " + instructor.getCourses());
		System.out.println("Done");
	}

	// Generates error on lazy load
	private void findInstructorWithCourse(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id " + id);

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("instructor: " + instructor);
		System.out.println("the courses: " + instructor.getCourses());
		System.out.println("Done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// create the instructor
		Instructor instructor = new Instructor("Bob", "Belcher", "bob@burgers.com");
		InstructorDetail instructorDetail = new InstructorDetail("bburgers", "Food");

		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// Create the courses
		Course burgers101 = new Course("Beginning Burgers");
		Course friesForYou = new Course("Make the best fries");

		// Add the courses
		instructor.addCourse(burgers101);
		instructor.addCourse(friesForYou);

		// Save the instructor
		// Saves the courses also because of CascadeType.PERSIST
		System.out.println("Saving instructor: " + instructor);
		System.out.println("The courses: " + instructor.getCourses());
		appDAO.save(instructor);
		System.out.println("Done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting instructor detail: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor detail id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("instructor detail: " + instructorDetail);
		System.out.println("instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done");
	}

	private void findInstructor(AppDAO appDAO) {
		Instructor test = appDAO.findInstructorById(1);
		System.out.println(test);
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		Instructor instructor = new Instructor("Bob", "Belcher", "bob@burgers.com");
		InstructorDetail instructorDetail = new InstructorDetail("bburgers", "Food");

		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor (saves detail as well because of CascadeType.ALL)
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done");
	}

}
