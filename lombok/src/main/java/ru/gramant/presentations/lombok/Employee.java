package ru.gramant.presentations.lombok;

import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags = new HashSet<>();
    private Date createdAt;

    public static void main(String[] args) {
        Employee pupkin;

        pupkin = Employee.builder().id(1).firstName("Вася").lastName("Пупкин").build();

        //int tagsCount = pupkin.tags.size(); //java.lang.NullPointerException

        pupkin = new Employee().toBuilder().id(1).firstName("Вася").lastName("Пупкин").build();

        int tagsCount = pupkin.tags.size();
    }
}
