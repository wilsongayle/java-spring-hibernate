package com.udemy.cruddemo;

import com.udemy.cruddemo.dao.AppDAO;
import com.udemy.cruddemo.entity.Instructor;
import com.udemy.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetail(appDAO);
		};
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 1;
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
		Instructor instructor = new Instructor("Barbara", "Anne", "banne@gmail.com");
		InstructorDetail instructorDetail = new InstructorDetail("ba", "music");

		// associate the objects
		instructor.setInstructorDetail(instructorDetail);

		// save the instructor (saves detail as well because of CascadeType.ALL)
		System.out.println("Saving instructor: " + instructor);
		appDAO.save(instructor);
		System.out.println("Done");
	}

}
