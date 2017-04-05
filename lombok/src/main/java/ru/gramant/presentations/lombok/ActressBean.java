

package ru.gramant.presentations.lombok;

import io.belov.soyuz.utils.to;
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ActressBean {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags;
    private Date createdAt;

    public static void main(String[] args) {
        System.out.println(new ActressBean(1, "Fedor", "Belov", to.set(), new Date()));
    }
}
