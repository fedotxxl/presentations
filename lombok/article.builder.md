# Как сделать ваш Java код чуточку надежнее
Продолжаем обзор аннотаций [Project Lombok](https://projectlombok.org/) (далее Lombok). Сегодня поговорим про @Builder. Вопрос - как @Builder связан с надежностью? 
Дело в том, что аннотация @Builder (помимо [очевидного предназначения](https://ru.wikipedia.org/wiki/%D0%A1%D1%82%D1%80%D0%BE%D0%B8%D1%82%D0%B5%D0%BB%D1%8C_(%D1%88%D0%B0%D0%B1%D0%BB%D0%BE%D0%BD_%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F))) помогает делать бины более Immutable, что (теоретически) означает более надежный код.
Об этом мы поговорим чуть ниже, а пока начнем с базовой функциональности.

## @Builder
Как всегда, будем разбираться на реальном коде:

> Для создания `java.util.Set` используется библиотека [soyuz-is-to](https://github.com/thedocs-io/soyuz-is-to)

```java
import io.thedocs.soyuz.to;
import lombok.*;
import javax.annotation.Nullable;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private Set<String> tags;
    private Date createdAt;

    @Builder
    public Employee(int id, String firstName, String lastName, Set<String> tags, @Nullable Date createdAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tags = tags;
        this.createdAt = (createdAt == null) ? new Date() : createdAt;
    }

    public static void main(String[] args) {
        //constructor init
        Employee pupkin = new Employee(1, "Вася", "Пупкин", to.set("депаратамент IT", "разработчик"), new Date());

        //builder init
        pupkin = Employee.builder()
                .id(1)
                .firstName("Вася")
                .lastName("Пупкин")
                .tags(to.set("депаратамент IT", "разработчик"))
                .createdAt(new Date())
                .build();
    }
}
```

Итак, что нужно помнить о @Builder:
1. @Builder можно повесить на класс или конструктор (как в примере). Первое удобно, т.к. кратко, второе - т.к. можно модифицировать параметры (`createdAt`)
2. @Builder делает инициализацию объекта более читабельной - вы сразу видите за что какой параметр отвечает
3. @Builder удобен в случае большой вариативности параметров (когда часть параметров опциональна), т.к. нет необходимости создавать все возможные конструкторы
4. @Builder не позволяет задать список обязательных параметров - например, теоретически можно создать Employee без id, что может противоречить бизнес логике. В этом случае лучше использовать стандартный конструктор.

## @Builder(toBuilder = true)
У аннотации @Builder есть опциональный параметр toBuilder, который по-умолчанию равен false. Если задать его равным, то мы сможем создавать builder из уже существующего объекта (т.е. builder будет заполнен данными из исходного объекта).
Это, зачастую, оказывается очень полезно. Допустим, нам требуется поменять теги у существующего Employee.
Добавить setter, конечно, можно, но зачем нам setter, который будет вызываться 1 раз во всем приложении? Лучше создать копию объекта и изменить у него поле tags:
```java
pupkin = pupkin
        .toBuilder()
        .tags(to.set("депаратамент IT", "старший разработчик"))
        .build();
```
На выходе мы получим новый объект Employee, у которого будет изменено поле tags. 
Грамотно используя @Builder(toBuilder = true), мы можем уменьшить количество setter'ов, т.е. сделать бины чуть более immutable, т.е. сделать Java код чуточку надежнее. 

## Подводные камни
Давайте взглянем на пример:
```java
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
```

Для меня было новостью, что @Builder не ициализирует поля по-умолчанию. Хотя мы явно указали `Set<String> tags = new HashSet<>()` в первом случае у нас возникнет `NullPointerException`, т.к. `pupkin.tags == null`.
Я знаю два решения данной проблемы:
1. Создавать кастомный @Builder, как сделано в первом примере
2. Использовать @Builder(toBuilder = true) и инициализировать пустой объект, как с сделано в данном примере. 

## Заключение
В данной статье я рассказал о нашем опыте использования аннотации @Builder. Данная аннотация удачно дополняет конструкторы в Java и при грамотном ее использовании, надеемся, сделает ваш код читабельнее и чуточку надежнее.