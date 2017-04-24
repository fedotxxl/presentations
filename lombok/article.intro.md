# Как сделать ваш Java код чуточку чище
С выходом Java8 мое настроение значительно поднялось - lambda, streams, method references и default methods стали огромным скачком - на java стало можно писать. 
Еще осталось много проблем и одна из них - куча лишнего кода в bean'ах.
Взгляните на этот код:
```java
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags;
    private Date createdAt;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, Set<String> tags, Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tags = tags;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Set<String> getTags() {
        return tags;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (tags != null ? !tags.equals(employee.tags) : employee.tags != null) return false;
        return createdAt != null ? createdAt.equals(employee.createdAt) : employee.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tags=" + tags +
                ", createdAt=" + createdAt +
                '}';
    }
}
```

Много ли полезной логики в нашем бине? 5-10%. Остальное - шелуха. Читать такой код сложно.
К тому же некоторые методы (equals / hashCode) необходимо не забывать обновлять.
Чтобы это исправить, добрые люди придумали [Project Lombok](https://projectlombok.org/) (далее Lombok).  
Вводное видео дает достаточно информации по данному проекту, но все же я бы хотел рассказать, как Lombok используется у нас.

> На первый взгляд Project Lombok выглядит, как магия. Все мы не очень любим магию (Hibernate, привет).
> Но хочу вас сразу заверить - Project Lombok это уже очень взрослая библиотека, котора хорошо себя зарекомендовала во многих проектах.

## Подготавливаем IDE
У нас в компании используется Intellij IDEA. Поэтому кратко опишу процесс ее настройки под Lombok:
1. [Устанавливаем](https://github.com/mplushnikov/lombok-intellij-plugin#plugin-installation) плагин [Lombok](https://github.com/mplushnikov/lombok-intellij-plugin)
2. Включаем [Annotation Processing](http://stackoverflow.com/a/14582541/716027) - данный пункт необходимо выполнять каждый раз для нового проекта

## Добавляем Project Lombok в проект
Добавить Lombok в проект очень просто:
```xml
<!-- maven -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.16.16</version>
    <scope>provided</scope>
</dependency>
```
```groovy
compileOnly 'org.projectlombok:lombok:1.16.16' //gradle
```

## Какие аннотации используется у нас
Настоящему разработчику проще читать код, чем текст. Поэтому посмотрим сразу пример:

```java
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags;
    private Date createdAt;
}
```
Как видите, код стал значительно чище. Несколько моментов, которые стоит отметить:
1. Вы можете переопределить необходимые вам getter'ы
2. Мы не используем аннотацию `@Setter`, т.к. стараемся делать бины как можно более immutable (чуть подробнее я коснусь этой темы в заметке про `@Builder`)
3. Мы не используем аннотации `@Data` или `@Value` - они, конечно, убирают еще 3-4 строчки кода, но делают его менее читабельным

## Заключение
В данной статье я затронул только самые очевидные аннотации, которые значительно уменьшают объем кода, делают его читабельнее и (как следствие) уменьшают % ошибок.
В последующих заметках я расскажу о других не менее полезных аннотациях - `@Builder` и `@SneakyThrows`.
А пока добавьте [Lombok](https://projectlombok.org/) в ваш текущий проект и начните его использовать уже сейчас! 