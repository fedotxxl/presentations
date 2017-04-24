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
Чтобы это исправить, добрый люди придумали [Project Lombok](https://projectlombok.org/) (далее Lombok).  
Вводное видео дает достаточно информации по данному проекту, но все же я бы хотел рассказать, как Lombok используется у нас.

> На первый взгляд Project Lombok выглядит, как магия. Все мы не очень любим магию (Hibernate, привет).
> Но хочу вас сразу заверить - Project Lombok это уже очень взрослый проект, который хорошо себя зарекомендовал во многих проектах.

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
}
```

Несколько интересных моментов:
1. Вы можете переопределять getter'ы
2. `@Builder` можно "повесить" на ваш конструктор
3. Мы не используем `@Setter` - мы стараемся сделать bean как можно более immutable. 


## Несколько слов о @Builder
Аннотация `@Builder` создает [Builder](https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D1%80%D0%BE%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_(%D1%88%D0%B0%D0%B1%D0%BB%D0%BE%D0%BD_%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F)) для аннотированного класса. У Builder'а есть преимущества и недостатки. Сравните:

> Для создания `java.util.Set` используется библиотека [soyuz-is-to](https://github.com/thedocs-io/soyuz-is-to)

```java
pupkin = new Employee(1, "Вася", "Пупкин", to.set("депаратамент IT", "разработчик"), new Date());
```

```java
pupkin = Employee.builder()
                .id(1)
                .firstName("Вася")
                .lastName("Пупкин")
                .tags(to.set("депаратамент IT", "разработчик"))
                .createdAt(new Date())
                .build();
```

Преимущества:
 1. Сразу видно какой параметр передается в конструктор
 2. В случае сильной вариативности параметров (когда часть параметров опциональна) нет необходимости создавать все возможные конструкторы
 
Недостатки:
 1. Нет возможности задать список обязательных параметров
 
В примере выше я использовал дополнительный параметр `toBuilder = true`. Смысл его прост - теперь я могу создавать builder из уже существующего объекта Employee.
Это зачастую оказывается очень полезно. Допустим, нам требуется поменять теги у существующего Employee.
Добавить setter, конечно, можно, но зачем нам setter, которы будет вызываться 1 раз во всем приложении? Лучше создать копию объекта и изменить у него поле tags:
```java
pupkin = pupkin
        .toBuilder()
        .tags(to.set("депаратамент IT", "старший разработчик"))
        .build();
```


## На закуску - @SneakyThrows
Checked exceptions полезный механизм. Но в довольно редких случаях. Зачестую этот механизм сильно мешает. Допустим, вы использует библиотеку, которая отправляет данные по HTTP.
Конечно, она кидает `IOException`, однако польза от такого поведения прямо противоположная:
1. Мы и так знаем, что при работе с HTTP может возникнуть `IOException`
2. Теперь нам нужно пробрасывать `IOException` по всему коду или же оборачивать в unchecked exception (`throw new RuntimeException(e)`).

Не знаю как вы, а я выбирал второй вариант. 

## Заключение
Хотите сделать код чище, читабельнее и уменьшить % ошибок? Начните использовать [Project Lombok](https://projectlombok.org/) в текущем активном проекте.