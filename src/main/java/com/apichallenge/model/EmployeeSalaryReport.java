package com.apichallenge.model;

import com.apichallenge.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeSalaryReport {
  private Employee lowest;
  private Employee highest;
  private double average;
}