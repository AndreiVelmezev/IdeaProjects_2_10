package service;

import exeption.EmployeeNotFoundException;
import model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service

public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmant() == department)
                        .max(Comparator.comparingDouble(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findEmployeeWithMixSalaryFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmant() == department)
                        .min(Comparator.comparingDouble(Employee::getSalary))
                        .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> findEmployeesFromDepartment(int department) {
        return employeeService.getAll().stream()
                .filter(employee -> employee.getDepartmant() == department)
                        .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployees() {
        return employeeService.getAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmant));
    }

}
