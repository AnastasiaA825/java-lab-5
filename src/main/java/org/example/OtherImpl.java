package org.example;

/**
 * Альтернативная реализация интерфейса {@link SomeInterface}.
 * <p>
 * Выводит символ "B" при вызове метода {@link #doSomething()}.
 * Может быть использована в качестве замены {@link SomeImpl}
 * путем изменения конфигурации в файле properties.
 * </p>
 *
 * @see SomeInterface
 * @see SomeBean
 * @version 1.0
 */

public class OtherImpl implements SomeInterface {
    /**
     * Выполняет действие, выводящее символ "B" в консоль.
     */
    @Override
    public void doSomething() {
        System.out.print("B");
    }
}