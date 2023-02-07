package com.chatbot;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PasswordTest {
    
    byte[] expected = new byte[] {-70, 119, -79, -92, 57, 50, 68, -59,
        -113, 101, 75, -41, 43, 103, -70, 46, 120, -91, -84, -46, 24,
        -16, 86, 117, 85, -25, -3, -25, 44, -16, 14, -76};
    String password = "1234";
    byte[] salt = "1234".getBytes();

    @Test
    public void hashTest(){
        byte[] out = com.chatbot.Password.hash(password, salt);
        assertArrayEquals(expected, out);
    }

    @Test
    public void isCorrectPasswordTest(){
        boolean out = com.chatbot.Password.isCorrectPassword(password, salt, expected);
        assertTrue(out, "isCorrectPasswordTest()");
    }

}
