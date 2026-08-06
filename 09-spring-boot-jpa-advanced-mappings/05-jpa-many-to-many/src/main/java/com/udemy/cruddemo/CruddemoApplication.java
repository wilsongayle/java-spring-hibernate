package com.udemy.cruddemo;

import com.udemy.cruddemo.dao.AppDAO;
import com.udemy.cruddemo.entity.*;
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
//			findCoursesForInstructor(appDAO);
//			findInstructorWithJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			deleteCourseById(appDAO);
//			createCourseAndReviews(appDAO);
//			findCourseAndReviews(appDAO);
//			deleteReviewById(appDAO);
//			createCourseAndStudents(appDAO);
//			findCourseAndStudents(appDAO);
//			findStudentAndCourses(appDAO);
//			addCoursesToStudent(appDAO);
			deleteStudentById(appDAO);
		};

	}

	private void deleteStudentById(AppDAO appDAO) {
		int id = 1;
		appDAO.deleteStudentById(id);
	}

	private void addCoursesToStudent(AppDAO appDAO) {
		int id = 5;
		System.out.println("Finding student id: " + id);
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		student.addCourse(new Course("Surviving the Irish"));
		appDAO.update(student);
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int id = 4;
		System.out.println("Finding student id: " + id);
		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student: " + student);
		System.out.println("Courses: " + student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;
		System.out.println("Finding course id: " + id);

		Course course = appDAO.findCourseAndStudentsByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		// create a course
		Course course = new Course("Growing up Irish");

		// create the students

		// add students to the course
		course.addStudent(new Student("Orla", "McCool", "orla.m@derry.ie"));
		course.addStudent(new Student("Erin", "Quinn", "erin.q@derry.ie"));
		course.addStudent(new Student("Claire", "Devlin", "clare.d.q@derry.ie"));
		course.addStudent(new Student("Michelle", "Mallon", "michelle.m@derry.ie"));
		course.addStudent(new Student("James", "Maguire", "english@derry.ie"));

		// save the course and associated students
		System.out.println("Saving the course: " + course);
		System.out.println("Associated students: " + course.getStudents());
		appDAO.save(course);
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		Course wineAppreciation = new Course("Drinking with Linda 201");

		wineAppreciation.addReview(new Review("Mommy doesn't get drunk. She just has fun."));
		wineAppreciation.addReview(new Review("Wine helps me drink."));

		System.out.println("Saving the course");
		System.out.println(wineAppreciation);
		System.out.println(wineAppreciation.getReviews());

		appDAO.save(wineAppreciation);

		System.out.println("Done");
	}

	private void deleteReviewById(AppDAO appDAO) {
		int id = 3;
		System.out.println("Deleting review id: " + id);
		appDAO.deleteReviewById(id);
		System.out.println("Done");
	}

	private void findCourseAndReviews(AppDAO appDAO) {
		int id = 19;
		System.out.println("Finding course id: " + id);

		Course course = appDAO.findCourseByAndReviewsByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 11;

		System.out.println("Deleting course id " + id);
		appDAO.deleteCourseById(id);

		System.out.println("Done");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);

		System.out.println("Updating course id: " + id);
		course.setTitle("Beginning Burgers with Bob");

		appDAO.update(course);
		System.out.println("Done");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Updating instructor id: " + id);
		instructor.setEmail("bobsburgers@seymoursbay.com");

		appDAO.update(instructor);
		System.out.println("Done");
	}

	// Fixes lazy load error - similar to eager loading without hard coding for everything (preserves flexibility)
	private void findInstructorWithJoinFetch(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);

		System.out.println("instructor: " + instructor);
		System.out.println("the courses: " + instructor.getCourses());
		System.out.println("Done");
	}

	// Fixes lazy load error - but requires separate query
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
		int id = 3;
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
