package ru.gramant.presentations.lombok;

import io.belov.soyuz.utils.to;
import lombok.*;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags;
    private Date createdAt;

    @Builder(toBuilder = true)
    public Employee(int id, String firstName, String lastName, Set<String> tags, @Nullable Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tags = tags;
        this.createdAt = (createdAt == null) ? new Date() : createdAt;
    }

    public static void main(String[] args) {
        Employee pupkin = new Employee(1, "Вася", "Пупкин", to.set("депаратамент IT", "разработчик"), new Date());

        Employee.builder()
                .id(1)
                .firstName("Вася")
                .lastName("Пупкин")
                .tags(to.set("депаратамент IT", "разработчик"))
                .createdAt(new Date())
                .build();

        pupkin = pupkin
                .toBuilder()
                .tags(to.set("депаратамент IT", "старший разработчик"))
                .build();
    }
}
