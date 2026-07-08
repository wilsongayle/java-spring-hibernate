package com.udemy.cruddemo.rest;

import com.udemy.cruddemo.entity.Employee;
import com.udemy.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private JsonMapper jsonMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, JsonMapper jsonMapper) {
        this.employeeService = employeeService;
        this.jsonMapper = jsonMapper;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("employees/{id}")
    public Employee findById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee not found: " + id);
        }

        return employee;
    }

    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        // Force save instead of update (if using Integer wrapper as type - use null instead of 0)
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> patchPayload) {
        Employee tempEmployee = employeeService.findById(id);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }

        if (patchPayload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body: " + id);
        }

        Employee patchedEmployee = jsonMapper.updateValue(tempEmployee, patchPayload);

        return employeeService.save(patchedEmployee);
    }

    @DeleteMapping("employees/{id}")
    public String deleteById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }
        employeeService.deleteById(id);

        return "Deleted: " + employee.toString();
    }
}
