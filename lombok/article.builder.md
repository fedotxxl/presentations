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


## Заключение
Хотите сделать код чище, читабельнее и уменьшить % ошибок? Начните использовать [Project Lombok](https://projectlombok.org/) в текущем активном проекте.