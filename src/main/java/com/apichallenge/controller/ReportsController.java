package com.apichallenge.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.apichallenge.repository.employeeRepository;
import com.apichallenge.helpers.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apichallenge.model.Employee;
import com.apichallenge.model.EmployeeAgeReport;
import com.apichallenge.model.EmployeeSalaryReport;

@RestController
@RequestMapping("/reports")
public class ReportsController {
  @Autowired
  private employeeRepository employeeRepository;

  @GetMapping("/employees/age")
  public EmployeeAgeReport employeeAge(@RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    EmployeeAgeReport response = new EmployeeAgeReport(
        this.employeeRepository.findYoungerEmployeer().get(0),
        this.employeeRepository.findOlderEmployeer().get(0),
        this.calculateAgeAverageFromEmployees());

    return response;
  }

  @GetMapping("/employees/salary")
  public EmployeeSalaryReport employeeSalary(@RequestHeader(value = "Authorization", required = false) String authKey) {
    Authorization.isValid(authKey);
    EmployeeSalaryReport response = new EmployeeSalaryReport(
        this.employeeRepository.findLowestPaidEmployeer().get(0),
        this.employeeRepository.findHighestPaidEmployeer().get(0),
        this.employeeRepository.getSalaryAverage());

    return response;
  }

  private double calculateAgeAverageFromEmployees() {
    double sumOfAges = 0.00;
    double averageAge = 0.00;
    List<Date> birthDates = this.employeeRepository.getAllBirhDates();
    int amountOfEmployees = birthDates.size();
    for (int iterator = 0; iterator < amountOfEmployees; iterator++) {
      sumOfAges += this.getAgeFromBirthDate(birthDates.get(iterator));
    }
    averageAge = sumOfAges / amountOfEmployees;
    return Math.round(averageAge * 100.00) / 100.0;
  }

  private int getAgeFromBirthDate(Date birthDate) {
    Date now = new Date();
    DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    int birthDateInInt = Integer.parseInt(formatter.format(birthDate));
    int currentDateInInt = Integer.parseInt(formatter.format(now));
    int age = (currentDateInInt - birthDateInInt) / 10000;
    return age;
  }
}