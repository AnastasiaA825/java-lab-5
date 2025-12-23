package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

/**
 * Комплексные тесты для класса {@link Injector}.
 * Проверяет корректность внедрения зависимостей, обработку ошибок и граничные случаи.
 */

class InjectorTest {

    private Injector injector;

    @BeforeEach
    void setUp() {
        injector = new Injector();
    }

    /**
     * Тест на корректное внедрение зависимостей.
     * Проверяет, что поля помеченные @AutoInjectable инициализируются правильно.
     */
    @Test
    void testInjectionSuccess() {
        SomeBean bean = new SomeBean();

        // Проверяем, что поля до внедрения равны null
        assertNull(bean.getField1(), "field1 должен быть null до внедрения");
        assertNull(bean.getField2(), "field2 должен быть null до внедрения");

        // Внедряем зависимости
        SomeBean injectedBean = injector.inject(bean);

        // Проверяем, что поля после внедрения не null
        assertNotNull(injectedBean.getField1(), "field1 не должен быть null после внедрения");
        assertNotNull(injectedBean.getField2(), "field2 не должен быть null после внедрения");

        // Проверяем типы внедренных объектов
        assertTrue(injectedBean.getField1() instanceof SomeInterface,
                "field1 должен быть экземпляром SomeInterface");
        assertTrue(injectedBean.getField2() instanceof SomeOtherInterface,
                "field2 должен быть экземпляром SomeOtherInterface");

        // Проверяем конкретные реализации
        assertEquals(SomeImpl.class, injectedBean.getField1().getClass(),
                "field1 должен быть экземпляром SomeImpl согласно конфигурации");
        assertEquals(SODoer.class, injectedBean.getField2().getClass(),
                "field2 должен быть экземпляром SODoer согласно конфигурации");
    }

    /**
     * Тест на работоспособность методов после внедрения зависимостей.
     * Проверяет, что внедренные зависимости корректно работают.
     */
    @Test
    void testInjectedBeanFunctionality() {
        SomeBean bean = injector.inject(new SomeBean());

        // Проверяем, что метод foo() выполняется без исключений
        assertDoesNotThrow(() -> bean.foo(),
                "Метод foo() должен выполняться без исключений после внедрения");
    }

    /**
     * Тест на обработку null объекта.
     * Проверяет, что передача null вызывает исключение.
     */
    @Test
    void testInjectNullObject() {
        assertThrows(IllegalArgumentException.class,
                () -> injector.inject(null),
                "Передача null должна вызывать IllegalArgumentException");
    }

    /**
     * Тест на внедрение в объект без аннотированных полей.
     * Проверяет, что инжектор корректно обрабатывает такие объекты.
     */
    @Test
    void testBeanWithoutInjectableFields() {
        // Создаем тестовый класс без аннотированных полей
        class TestBean {
            private String normalField = "test";
        }

        TestBean bean = new TestBean();
        TestBean injectedBean = injector.inject(bean);

        // Объект должен остаться неизменным
        assertEquals("test", bean.normalField,
                "Поля без аннотации @AutoInjectable не должны изменяться");
    }

    /**
     * Тест на изоляцию зависимостей.
     * Проверяет, что разные объекты получают разные экземпляры зависимостей.
     */
    @Test
    void testDependencyIsolation() {
        SomeBean bean1 = injector.inject(new SomeBean());
        SomeBean bean2 = injector.inject(new SomeBean());

        // Проверяем, что это разные объекты
        assertNotSame(bean1, bean2, "Должны быть разные объекты SomeBean");

        // Проверяем, что зависимости тоже разные (новые экземпляры)
        assertNotSame(bean1.getField1(), bean2.getField1(),
                "Разные объекты должны получать разные экземпляры SomeInterface");
        assertNotSame(bean1.getField2(), bean2.getField2(),
                "Разные объекты должны получать разные экземпляры SomeOtherInterface");
    }

    /**
     * Тест на соответствие типов.
     * Проверяет, что внедряются только подходящие типы.
     */
    @Test
    void testTypeCompatibility() {
        SomeBean bean = injector.inject(new SomeBean());

        // Проверяем, что можно безопасно приводить типы
        SomeInterface field1 = bean.getField1();
        SomeOtherInterface field2 = bean.getField2();

        assertDoesNotThrow(() -> {
            field1.doSomething();
            field2.doSomeOther();
        }, "Методы зависимостей должны вызываться без исключений приведения типов");
    }

    /**
     * Тест на обработку неинтерфейсного поля.
     * Проверяет, что попытка внедрения в поле неинтерфейсного типа вызывает исключение.
     */
    @Test
    void testNonInterfaceFieldInjection() {
        // Создаем тестовый класс с полем неинтерфейсного типа
        class InvalidBean {
            @AutoInjectable
            private String invalidField;
        }

        InvalidBean bean = new InvalidBean();

        assertThrows(RuntimeException.class,
                () -> injector.inject(bean),
                "Внедрение в поле неинтерфейсного типа должно вызывать исключение");
    }
}

/**
 * Дополнительный тестовый класс для проверки граничных случаев.
 */

class AdditionalTestBean {
    @AutoInjectable
    private SomeInterface dependency1;

    @AutoInjectable
    private SomeOtherInterface dependency2;

    // Поле без аннотации
    private String normalField = "normal";

    public void testMethod() {
        if (dependency1 != null && dependency2 != null) {
            dependency1.doSomething();
            dependency2.doSomeOther();
        }
    }
}