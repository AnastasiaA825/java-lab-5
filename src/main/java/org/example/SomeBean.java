package org.example;

/**
 * Демонстрационный класс с внедряемыми зависимостями.
 * <p>
 * Содержит два поля, помеченные аннотацией {@link AutoInjectable},
 * которые должны быть автоматически инициализированы инжектором.
 * </p>
 *
 * @see AutoInjectable
 * @see Injector
 * @version 1.0
 */

public class SomeBean {
    /**
     * Первое внедряемое поле типа {@link SomeInterface}.
     * Будет инициализировано реализацией, указанной в конфигурации.
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Второе внедряемое поле типа {@link SomeOtherInterface}.
     * Будет инициализировано реализацией, указанной в конфигурации.
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Выполняет действия с использованием внедренных зависимостей.
     * <p>
     * Метод вызывает методы зависимостей для демонстрации их работы.
     * </p>
     */
    public void foo() {
        field1.doSomething();
        field2.doSomeOther();
    }

    /**
     * Возвращает первую зависимость.
     *
     * @return объект SomeInterface или null, если зависимость не внедрена
     */
    public SomeInterface getField1() {
        return field1;
    }

    /**
     * Возвращает вторую зависимость.
     *
     * @return объект SomeOtherInterface или null, если зависимость не внедрена
     */
    public SomeOtherInterface getField2() {
        return field2;
    }
}