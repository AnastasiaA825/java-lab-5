package org.example;

/**
 * Реализация интерфейса {@link SomeOtherInterface}.
 * <p>
 * Выводит символ "C" при вызове метода {@link #doSomeOther()}.
 * Используется для демонстрации внедрения второй зависимости.
 * </p>
 *
 * @see SomeOtherInterface
 * @see SomeBean
 * @version 1.0
 */

public class SODoer implements SomeOtherInterface {
    /**
     * Выполняет другое действие, выводящее символ "C" в консоль.
     */
    @Override
    public void doSomeOther() {
        System.out.print("C");
    }
}