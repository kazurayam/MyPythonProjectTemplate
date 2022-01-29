package flaskrtest

import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class GreeterTest {

    @Test
    void test_greeting() {
        String msg = Greeter.sayHelloTo("Henri")
        assertEquals("Hello, Henri", msg)
    }
}
