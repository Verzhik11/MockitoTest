package verzhik.mockitospringtest1.services;

import org.springframework.stereotype.Service;
import verzhik.mockitospringtest1.objects.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class EmployeeService {
    List<Employee> employees;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }


    public Employee addEmployee(String name, int salary, int department) {
        Employee newEmployee = new Employee(name, salary, department);
        if (employees.contains(newEmployee)) {
            throw new RuntimeException("Нельзя добавить сотрудника, такой сотрудник уже есть в массиве");
        }
        employees.add(newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, int salary, int department) {
        Employee removeEmployee = new Employee(name, salary, department);
        if (employees.contains(removeEmployee)) {
            employees.remove(removeEmployee);
            return removeEmployee;
        }
        throw new RuntimeException("Удаляемый сотрудник не найден");
    }

    public Employee findEmployee(String name, int salary, int department) {
        Employee findEmployee = new Employee(name, salary, department);
        if (employees.contains(findEmployee)) {
            return findEmployee;
        }
        throw new RuntimeException(findEmployee + "не найден");
    }

    public List<Employee> getAll() {
        return new ArrayList<>(employees);
    }
}
