package com.example.mealer;
import org.junit.Test;

import static org.junit.Assert.*;

public class verificationTest {

    @Test
    public void testVerificationPassword() {

        // create verification class to verify
        Verification_Class verification = new Verification_Class();

        // verify password and return error if
        String password = "123";
        String actual = verification.checkPassword(password);

        // expected error
        String expected = "Password must be at least 6 characters";

        assertEquals(expected, password);
    }
}
