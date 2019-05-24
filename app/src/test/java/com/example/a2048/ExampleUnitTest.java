package com.example.a2048;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        int negative1 = -5 ;
        int negative2 = 5;
        int positive = Math.abs(negative1 - negative2);

        assertEquals(10, positive);
    }
}