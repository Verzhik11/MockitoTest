package verzhik.mockitospringtest1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import verzhik.mockitospringtest1.objects.Employee;
import verzhik.mockitospringtest1.services.DepartmentService;

import java.util.Map;

public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/all")
    public Map<String, Employee> printEmployees() {
        return departmentService.printEmployees();
    }

    @GetMapping(path = "/min-salary")
    public Employee findEmployeeMinSalary(@RequestParam(value = "departmentId", required = false)
                                          int department) {
        return departmentService.findEmployeeMinSalary(department);
    }
    @GetMapping(path = "/max-salary")
    public Employee findEmployeeMaxSalary(@RequestParam(value = "departmentId", required = false)
                                          int department) {
        return departmentService.findEmployeeMaxSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public Map<Employee, String> printEmployeeDepartment(@RequestParam(value = "departmentId", required = false)
                                                         int department) {
        return departmentService.printEmployeeDepartment(department);
    }
}
