package verzhik.mockitospringtest1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import verzhik.mockitospringtest1.objects.Employee;
import verzhik.mockitospringtest1.services.EmployeeService;

public class EmployeeServiceTest {
    private final EmployeeService out = new EmployeeService();
    @BeforeEach
    public void beforeEach() {
        out.addEmployee("Rahim Sterling", 300000, 3);
        out.addEmployee("Joe Terry", 200000, 2);
        out.addEmployee("Didie Drogba", 2050000, 1);
    }
    @AfterEach
    public void afterEach() {
        out.getAll().forEach(employee -> out.removeEmployee(employee.getName(), employee.getSalary(), employee.getDepartment()));
    }
    @Test
    public void addEmployeeTest() {
        Employee expected = new Employee("Andreas Iniesta", 350000, 4);
        Assertions.assertThat(out.addEmployee("Andreas Iniesta", 350000, 4)).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    public void whenAlreadyAddedEmployee() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->out.addEmployee("Rahim Sterling", 300000, 3));
    }
    @Test
    public void removeEmployeeTest() {
        Employee expected = new Employee("Rahim Sterling", 300000, 3);
        Assertions.assertThat(out.removeEmployee("Rahim Sterling", 300000, 3)).isEqualTo(expected)
                .isNotIn(out.getAll());
    }
    @Test
    public void whenYouCantRemove() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->out.removeEmployee("Andreas Iniesta", 350000, 4));
    }
    @Test
    public void findEmployeeTest() {
        Employee expected = new Employee("Rahim Sterling", 300000, 3);
        Assertions.assertThat(out.findEmployee("Rahim Sterling", 300000, 3)).isEqualTo(expected)
                .isIn(out.getAll());
    }
    @Test
    public void whenYouCantFind() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(()->out.findEmployee("Andreas Iniesta", 350000, 4));
    }
    @Test
    public void getAllTest() {
        Assertions.assertThat(out.getAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Rahim Sterling", 300000, 3),
                        new Employee("Joe Terry", 200000, 2),
                        new Employee("Didie Drogba", 2050000, 1)
                );
    }

}
