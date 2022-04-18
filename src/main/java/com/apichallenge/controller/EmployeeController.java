package com.apichallenge.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.apichallenge.repository.employeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apichallenge.helpers.Authorization;
import com.apichallenge.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  // GET
  // POST
  // PUT
  // DELETE
  // PATCH
  private List<Employee> employees = new ArrayList<>();

  @Autowired
  private employeeRepository employeeRepository;

  @GetMapping("/{id}")
  public Employee employee(@PathVariable("id") Long id,
      @RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    Optional<Employee> employeeFind = this.employeeRepository.findById(id);
    if (employeeFind.isPresent()) {
      Employee employee = employeeFind.get();
      return employee;
    }

    return null;
  }

  @PostMapping("/")
  public Employee employee(@RequestBody Employee employee,
      @RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    return this.employeeRepository.save(employee);
  }

  @GetMapping("/list")
  public List<Employee> list(@RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    {
      return this.employeeRepository.findAll();
    }
  }
}