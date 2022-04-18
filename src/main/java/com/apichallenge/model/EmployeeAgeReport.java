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
public class EmployeeAgeReport {
  private Employee younger;
  private Employee older;
  private double average;
}
