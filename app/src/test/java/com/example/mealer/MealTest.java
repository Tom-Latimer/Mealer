package com.example.mealer;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MealTest  {

    @Test
    public void isDigitTest() {
        String digit ="1";
        Boolean expected =true;
        AddMeal testCase=new AddMeal();
        Boolean result=testCase.isDigit(digit);
        assertEquals(expected, result);
    }
}
