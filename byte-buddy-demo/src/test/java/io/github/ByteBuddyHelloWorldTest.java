package io.github;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple ByteBuddyHelloWorld.
 */
public class ByteBuddyHelloWorldTest {

    @Test
    public void toString01() {
        ByteBuddyHelloWorld bbhw = new ByteBuddyHelloWorld();
        assertEquals("Hello world!", bbhw.toString());
    }
}
