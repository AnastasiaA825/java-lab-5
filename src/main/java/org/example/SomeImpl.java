package org.example;

/**
 * Первая реализация интерфейса {@link SomeInterface}.
 * <p>
 * Выводит символ "A" при вызове метода {@link #doSomething()}.
 * Используется по умолчанию в конфигурации.
 * </p>
 *
 * @see SomeInterface
 * @see SomeBean
 * @version 1.0
 */

public class SomeImpl implements SomeInterface {
    /**
     * Выполняет действие, выводящее символ "A" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.print("A");
    }
}