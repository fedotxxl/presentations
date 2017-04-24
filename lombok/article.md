# Как сделать ваш Java код чуточку чище
С выходом Java8 мое настроение значительно поднялось - lambda, streams, method references и default methods стали огромным скачком - на java стало можно писать. 
Еще осталось много проблем и одна из них - куча лишнего кода в bean'ах.
Взгляните на этот код:


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

Несколько интересных моментов:
1. Вы можете переопределять getter'ы
2. `@Builder` можно "повесить" на ваш конструктор
3. Мы не используем `@Setter` - мы стараемся сделать bean как можно более immutable. 


## Несколько слов о @Builder

## На закуску - @SneakyThrows

## Заключение
Хотите сделать код чище, читабельнее и уменьшить % ошибок? Начните использовать [Project Lombok](https://projectlombok.org/) в текущем активном проекте.