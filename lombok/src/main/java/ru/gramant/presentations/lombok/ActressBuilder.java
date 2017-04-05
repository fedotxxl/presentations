
package ru.gramant.presentations.lombok;

import io.belov.soyuz.utils.to;
import lombok.*;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Set;


@Getter
@ToString
@NoArgsConstructor
public class ActressBuilder {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags = to.set();
    private Date createdAt;

    @Builder(toBuilder = true)
    private ActressBuilder(int id, String firstName, String lastName, Set<String> tags, @Nullable Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tags = tags;
        this.createdAt = (createdAt == null) ? new Date() : createdAt;
    }

    static class Run {
        public static void main(String[] args) {
            //new ActressBean(1, "Fedor", "Belov", to.set(), new Date());

            //actress
            //actressWithDefaults
            //actressWithTags
            //builder on constructor
            val actress = ActressBuilder.builder()
                    .firstName("Fedor")
                    .lastName("Belov")
                    .build();

            val actressWithDefaults = new ActressBuilder().toBuilder().build();

            val actressWithTags = actress.toBuilder().tags(to.set("hello", "world")).build();

            System.out.println(actress);
            System.out.println(actressWithDefaults);
            System.out.println(actressWithTags);
        }
    }
}
