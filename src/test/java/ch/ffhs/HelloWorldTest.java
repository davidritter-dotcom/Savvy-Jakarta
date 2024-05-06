package ch.ffhs;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    @Test
    public void testGetHtml() {
        // Arrange
        HelloWorld helloWorld = new HelloWorld();

        // Act
        String result = helloWorld.getHtml();

        // Assert
        String expected = "<html lang=\"en\"><body><h1>Hello, World!!</h1></body></html>";
        assertEquals(expected, result);
    }
}