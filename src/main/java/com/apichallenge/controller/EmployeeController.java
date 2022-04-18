package com.apichallenge.controller;

import java.util.List;
import java.util.Optional;
import com.apichallenge.repository.employeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.apichallenge.helpers.Authorization;
import com.apichallenge.model.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
  // PUT
  // PATCH
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

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
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
    return this.employeeRepository.findAll();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteEmployee(@PathVariable("id") Long id,
      @RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    Optional<Employee> existsEmployee = this.employeeRepository.findById(id);
    if (existsEmployee.isPresent()) {
      this.employeeRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
  }

  @PutMapping("/{id}")
  public Employee putUpdateEmployeeById(@PathVariable("id") Long id,
      @RequestHeader(value = "Authorization", required = false) String authKey,
      @RequestBody Employee fieldsToUpdate) {
    Optional<Employee> employeeFind = this.employeeRepository.findById(id);
    if (employeeFind.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }
    fieldsToUpdate.setId(id);
    return this.employeeRepository.save(fieldsToUpdate);
  }

  @PatchMapping("/{id}")
  public Employee patchUpdateEmployeeById(@PathVariable("id") Long id,
      @RequestHeader(value = "Authorization", required = false) String authKey,
      @RequestBody Employee fieldsToUpdate) {
    Optional<Employee> employeeFind = this.employeeRepository.findById(id);
    if (employeeFind.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found");
    }
    Employee employee = employeeFind.get();
    fieldsToUpdate.setId(id);
    // Ugly code area
    fieldsToUpdate.setName(fieldsToUpdate.getName() == null ? employee.getName() : fieldsToUpdate.getName());
    fieldsToUpdate.setEmail(fieldsToUpdate.getEmail() == null ? employee.getEmail() : fieldsToUpdate.getName());
    fieldsToUpdate.setSalary(fieldsToUpdate.getSalary() == 0.0 ? employee.getSalary() : fieldsToUpdate.getSalary());
    fieldsToUpdate.setDepartment(
        fieldsToUpdate.getDepartment() == null ? employee.getDepartment() : fieldsToUpdate.getDepartment());
    fieldsToUpdate
        .setBirthDate(fieldsToUpdate.getBirthDate() == null ? employee.getBirthDate() : fieldsToUpdate.getBirthDate());
    // Ugly code area
    return this.employeeRepository.save(fieldsToUpdate);
  }
}