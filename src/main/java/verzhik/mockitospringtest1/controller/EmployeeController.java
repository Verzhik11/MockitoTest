package verzhik.mockitospringtest1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import verzhik.mockitospringtest1.objects.Employee;
import verzhik.mockitospringtest1.services.EmployeeService;

import java.util.Map;
@RestController
public class EmployeeController {
    public EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String showGreetings() {
        return "Hello World";
    }
    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam(required = false) String name,
                                @RequestParam(required = false) int salary,
                                @RequestParam(required = false) int department) {
        return employeeService.addEmployee(name, salary, department);
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam(required = false) String name,
                                   @RequestParam(required = false) int salary,
                                   @RequestParam(required = false) int department) {
        return employeeService.removeEmployee(name, salary, department);
    }
    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) int salary,
                                 @RequestParam(required = false) int department) {
        return employeeService.findEmployee(name, salary, department);
    }
}
