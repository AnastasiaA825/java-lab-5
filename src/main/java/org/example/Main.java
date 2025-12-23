package org.example;

/**
 * Главный класс для демонстрации работы Dependency Injection Framework.
 * <p>
 * Показывает проблему NullPointerException при отсутствии внедрения зависимостей
 * и демонстрирует решение с использованием класса {@link Injector}.
 * </p>
 *
 * @author Анастасия
 * @version 1.0
 */

public class Main {

    /**
     * Точка входа в приложение. Демонстрирует работу Injector.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        System.out.println("=== Лабораторная работа: Dependency Injection ===\n");

        // Демонстрация проблемы без DI
        System.out.println("1. Демонстрация проблемы (без DI):");
        try {
            SomeBean beanWithoutDI = new SomeBean();
            System.out.println("   Вызов метода foo() без внедрения зависимостей:");
            System.out.print("   Результат: ");
            beanWithoutDI.foo();
        } catch (NullPointerException e) {
            System.out.println("   Ошибка: NullPointerException - зависимости не инициализированы");
        }

        // Демонстрация решения с DI
        System.out.println("\n2. Демонстрация решения (с DI):");
        SomeBean bean = new SomeBean();
        Injector injector = new Injector();

        System.out.println("   Внедряем зависимости...");
        SomeBean injectedBean = injector.inject(bean);

        System.out.println("   Зависимости успешно внедрены:");
        System.out.println("   - field1: " + injectedBean.getField1().getClass().getSimpleName());
        System.out.println("   - field2: " + injectedBean.getField2().getClass().getSimpleName());

        System.out.print("   Результат вызова foo(): ");
        injectedBean.foo();

        // Демонстрация гибкости конфигурации
        System.out.println("\n3. Демонстрация гибкости конфигурации:");
        System.out.println("   Для изменения реализации SomeInterface на OtherImpl");
        System.out.println("   необходимо изменить строку в config.properties:");
        System.out.println("   org.example.SomeInterface=org.example.OtherImpl");
        System.out.println("   После этого вывод изменится на: BC");
    }
}