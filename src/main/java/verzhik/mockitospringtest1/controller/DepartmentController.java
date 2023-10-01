package verzhik.mockitospringtest1.controller;

import jdk.jfr.Frequency;
import org.springframework.web.bind.annotation.*;
import verzhik.mockitospringtest1.objects.Employee;
import verzhik.mockitospringtest1.services.DepartmentService;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/department")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping( "/employees")
    public Map<Integer, List<Employee>> printEmployees() {
        return departmentService.employeesGroupedByDepartment();
    }
    @GetMapping( "/{id}/salary/sum")
    public int findSumSalaryFromDepartment(@PathVariable int id) {
        return departmentService.findSumSalaryfromDepartment(id);
    }
    @GetMapping( "/{id}/salary/min")
    public int findMinSalaryFromDepartment(@PathVariable int id) {
        return departmentService.findMinSalaryFromDepartment(id);
    }
    @GetMapping( "/{id}/salary/max")
    public int findMaxSalaryFromDepartment(@PathVariable int id) {
        return departmentService.findMaxSalaryFromDepartment(id);
    }
    @GetMapping(value = "/{id}/employees")
    public List<Employee> printEmployeeDepartment(@PathVariable int id) {
        return departmentService.printEmployeeDepartment(id);
    }
}
