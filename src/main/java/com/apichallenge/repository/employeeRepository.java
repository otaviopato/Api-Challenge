package com.apichallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

import com.apichallenge.model.Employee;

public interface employeeRepository extends JpaRepository<Employee, Long> {
  @Query(value = "SELECT * FROM employee ORDER BY birth_date DESC LIMIT 1", nativeQuery = true)
  public List<Employee> findYoungerEmployeer();

  @Query(value = "SELECT * FROM employee ORDER BY birth_date ASC LIMIT 1", nativeQuery = true)
  public List<Employee> findOlderEmployeer();

  @Query(value = "SELECT birth_date FROM employee", nativeQuery = true)
  public List<Date> getAllBirhDates();

  @Query(value = "SELECT * FROM employee ORDER BY salary DESC LIMIT 1", nativeQuery = true)
  public List<Employee> findHighestPaidEmployeer();

  @Query(value = "SELECT * FROM employee ORDER BY salary ASC LIMIT 1", nativeQuery = true)
  public List<Employee> findLowestPaidEmployeer();

  @Query(value = "SELECT * FROM employee WHERE salary", nativeQuery = true)
  public List<Employee> getAllSalary();

  @Query(value = "SELECT AVG(salary) as average_salary FROM employee", nativeQuery = true)
  public double getSalaryAverage();
}
