package verzhik.mockitospringtest1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import verzhik.mockitospringtest1.objects.Employee;
import verzhik.mockitospringtest1.services.DepartmentService;
import verzhik.mockitospringtest1.services.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentService out;

    @BeforeEach
    void setUp() {
        Mockito.when(employeeService.getAll()).thenReturn(
                List.of(
                        new Employee("Wayne Rooney", 350000, 1),
                        new Employee("Rayan Giggs", 250000, 1),
                        new Employee("Tierie Henry", 400000, 2),
                        new Employee("Cesk Fabregas", 300000, 2),
                        new Employee("Steven Gerard", 300000, 3),
                        new Employee("Mohamed Salah", 350000, 3)
                )
        );
    }

    static Stream<Arguments> SalarySumFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 600000),
                Arguments.of(2, 700000),
                Arguments.of(3, 650000),
                Arguments.of(4, 0)
        );

    }

    static Stream<Arguments> SalaryMinFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 250000),
                Arguments.of(2, 300000),
                Arguments.of(3, 300000)
        );

    }

    static Stream<Arguments> SalaryMaxFromDepartmentTestParams() {
        return Stream.of(
                Arguments.of(1, 350000),
                Arguments.of(2, 400000),
                Arguments.of(3, 350000)
        );

    }

    static Stream<Arguments> EmployeesFromDepartmentParams() {
        return Stream.of(
                Arguments.of(1,
                        List.of("Wayne Rooney", 350000, 1),
                        List.of("Rayan Giggs", 250000, 1)),
                Arguments.of(2,
                        List.of("Tierie Henry", 400000, 2),
                        List.of("Cesk Fabregas", 300000, 2)),
                Arguments.of(3,
                        List.of("Steven Gerard", 300000, 3),
                        List.of("Mohamed Salah", 350000, 3)),
                Arguments.of(4,
                        Collections.emptyList())
        );
    }

    @ParameterizedTest
    @MethodSource("SalarySumFromDepartmentTestParams")
    void findSumSalaryfromDepartmentTest(int departmentID, int expected) {
        Assertions.assertThat(out.findSumSalaryfromDepartment(departmentID)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("SalaryMaxFromDepartmentTestParams")
    void findMaxSalaryFromDepartmentTest(int departmentID, int expected) {
        Assertions.assertThat(out.findMaxSalaryFromDepartment(departmentID)).isEqualTo(expected);
    }

    @Test
    void findMaxSalaryFromDepartmentNotFoundTest() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.findMaxSalaryFromDepartment(4));
    }

    @ParameterizedTest
    @MethodSource("SalaryMinFromDepartmentTestParams")
    void findMinSalaryFromDepartment(int departmentID, int expected) {
        Assertions.assertThat(out.findMinSalaryFromDepartment(departmentID)).isEqualTo(expected);
    }

    @Test
    void findMinSalaryFromDepartmentNotFoundTest() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> out.findMinSalaryFromDepartment(4));
    }

    @Test
    void employeesGroupedByDepartmentTest() {
        Map<Integer, List<Employee>> expected = java.util.Map.of(
                1,
                List.of(
                        new Employee("Wayne Rooney", 350000, 1),
                        new Employee("Rayan Giggs", 250000, 1)),
                 2,
                List.of(
                        new Employee("Tierie Henry", 400000, 2),
                        new Employee("Cesk Fabregas", 300000, 2)),
                  3,
                List.of(
                        new Employee("Steven Gerard", 300000, 3),
                        new Employee("Mohamed Salah", 350000, 3))
                );
        Assertions.assertThat(out.employeesGroupedByDepartment()).containsExactlyInAnyOrderEntriesOf(expected);

    }

    @ParameterizedTest
    @MethodSource("EmployeesFromDepartmentParams")
    void printEmployeeDepartment(int departmentID, List<Employee>expected) {
        Assertions.assertThat(out.printEmployeeDepartment(departmentID)).containsExactlyInAnyOrderElementsOf(expected);
    }
}