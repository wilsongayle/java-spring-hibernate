package com.udemy.springdemo.mvc.model;

import com.udemy.springdemo.mvc.validation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    @NotNull(message = "Required")
    @Size(min = 1, message = "Required")
    private String lastName;

    @NotNull(message = "Required")
    @Min(value = 0, message = "Must have a minimum of 0")
    @Max(value = 10, message = "Must have a maximum of 10")
    private Integer freePasses;

    @Pattern(regexp = "^\\d{5}$", message = "A postal code should have 5 digits")
    private String postalCode;

    @CourseCode(value = "TEST", message = "This must start with TEST")
    private String courseCode;

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
