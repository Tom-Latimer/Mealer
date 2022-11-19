package com.example.mealer;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectCreationTest {

    public Client_Class createClient(String firstName,String lastName,String password,String address,String unitNumber,String email,String ccNumber,String securityCode,String expirationDate) {
        Verification_Class verify = new Verification_Class();

        String firstNameError = verify.checkFirstName(firstName);
        if (!firstNameError.equals("")) {

            firstName = "ERROR";
        }

        String lastNameError = verify.checkLastName(lastName);
        if (!lastNameError.equals("")) {

            lastName = "ERROR";
        }

        String passwordError = verify.checkPassword(password);
        if (!passwordError.equals("")) {

            password = "ERROR";
        }

        String addressError = verify.checkAddress(address);
        if (!addressError.equals("")) {

            address = "Error";
        }

        String unitError = verify.checkUnitNum(unitNumber);
        if (!unitError.equals("")) {

            unitNumber = "ERROR";
        }

        /*String emailError = verify.checkEmail(email);
        if (!emailError.equals("")) {

            email = "Error";
        }*/

        String ccNumberError = verify.checkCCNumber(ccNumber);
        if (!ccNumberError.equals("")) {

            ccNumber = "ERROR";
        }

        String securityCodeError = verify.checkSecurityCode(securityCode);
        if (!securityCodeError.equals("")) {

            securityCode = "ERROR";
        }

        String expirationDateError = verify.checkExpirationDate(expirationDate);
        if (!expirationDateError.equals("")) {

            expirationDate = "ERROR";
        }

        return new Client_Class(firstName,lastName,email,password,address,"CLIENT",ccNumber+securityCode+expirationDate);
    }

    @Test
    public void client1 () {
        String firstName = "Clyde";
        String lastName = "House";
        String password = "Cl1d3Hou3";
        String address = "1390 Walkey Ridge";
        String unitNumber = "22";
        String email = "chouse@gmail.com";
        String ccNumber = "1234567891234567";
        String securityCode = "333";
        String expirationDate = "04/10";

        Client_Class client = createClient(firstName,lastName,password,address,unitNumber,email,ccNumber,securityCode,expirationDate);

        assertEquals(firstName,client.get_name() );
        assertEquals(lastName,client.get_last_name());
        assertEquals(password, client.get_password());
        assertEquals(address, client.get_address());
        assertEquals(email, client.get_email());
        assertEquals(ccNumber+securityCode+expirationDate,client.get_cc_information());

    }
}
