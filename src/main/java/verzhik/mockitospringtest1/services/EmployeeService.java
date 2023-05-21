package verzhik.mockitospringtest1.services;

import org.springframework.stereotype.Service;
import verzhik.mockitospringtest1.objects.Employee;

import java.util.HashMap;
import java.util.Map;
@Service
public class EmployeeService {
    Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }


    public String getKey(Employee employee) {
        return employee.getName();
    }

    public Employee addEmployee(String name, int salary, int department) {
        Employee newEmployee = new Employee(name, salary, department);
        if (employees.containsKey(newEmployee.getName())) {
            throw new RuntimeException("Нельзя добавить сотрудника, такой сотрудник уже есть в массиве");
        }
        employees.put(newEmployee.getName(), newEmployee);
        return newEmployee;
    }

    public Employee removeEmployee(String name, int salary, int department) {
        Employee removeEmployee = new Employee(name, salary, department);
        if (employees.containsKey(removeEmployee.getName())) {
            employees.remove(removeEmployee);
            return removeEmployee;
        }
        throw new RuntimeException("Удаляемый сотрудник не найден");
    }

    public Employee findEmployee(String name, int salary, int department) {
        Employee findEmployee = new Employee(name, salary, department);
        if (employees.containsKey(findEmployee.getName())) {
            return findEmployee;
        }
        throw new RuntimeException(findEmployee + "не найден");
    }
}
