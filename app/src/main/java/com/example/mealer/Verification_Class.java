package com.example.mealer;

import android.net.Uri;
import android.util.Patterns;

public class Verification_Class {

    public String checkFirstName(String firstName){
        String error = "";
        if (firstName.isEmpty()) {
            error = "First Name Required";
        }
        return error;
    }

    public String checkLastName(String lastName){
        String error = "";
        if (lastName.isEmpty()) {
            error = "Last Name Required";
        }
        return error;
    }

    public String checkPassword(String password){
        String error = "";
        if (password.isEmpty()) {
            error = "Please enter a password";
        } else if (password.length() < 6) {
            error = "Password must be at least 6 characters";
        }
        return error;
    }

    public String checkEmail(String email){
        String error = "";
        if (email.isEmpty()) {
            error = "Email Required";
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = "Invalid Email";
        }
        return error;
    }

    public String checkCCNumber(String ccNumber){
        String error = "";
        if (ccNumber.isEmpty()) {
            error = "Field Empty";
        } else if (ccNumber.length() < 16) {
            error = "Please enter all numbers";
        }
        return error;
    }

    public String checkSecurityCode(String securityCode){
        String error = "";
        if (securityCode.isEmpty()) {
            error = "Field Empty";
        } else if (securityCode.length() < 3) {
            error = "Please enter all numbers";
        }
        return error;
    }

    public String checkExpirationDate(String expDate){
        String error = "";
        if (expDate.isEmpty()) {
            error = "Field Empty";
        } else if (expDate.length() < 5) {
            error = "Please enter a date";
        }
        return error;
    }

    public String checkAddress(String address){
        String error = "";
        if (address.isEmpty()) {
            error = "Field Empty";
        }
        return error;
    }

    public String checkUnitNum(String unitNum){
        String error = "";

        return error;
    }

    public String checkPostalCode(String postalCode){
        String error = "";
        if (postalCode.isEmpty()) {
            error = "Postal Code Required";
        }
        else if (postalCode.length() != 6) {
            error = "Please enter a valid postal code (ex: A1A1A1)";
        }
        else {
            for (int i = 0; i < 6; i++) {

                if (i % 2 == 0) {
                    if (!(Character.isLetter(postalCode.charAt(i)))) {
                        error = "Please enter a valid postal code (ex: A1A1A1)";
                    }
                }
                if (i % 2 == 1) {
                    if (!(Character.isDigit(postalCode.charAt(i)))) {
                        error = "Please enter a valid postal code (ex: A1A1A1)";
                    }
                }
            }
        }
        return error;
    }

    public String checkCountry(String country){
        String error = "";
        if (country.isEmpty()) {
            error = "Country Required";
        }
        return error;
    }

    public String checkVoidCheque(Uri voidCheque){
        String error = "";
        if (voidCheque == null){
            error = "Please upload an image of a void cheque";
        }
        return error;
    }

    public String checkDescription(String description){
        String error = "";
        if (description.isEmpty()) {
            error = "Description Required";
        }
        return error;
    }

    public String checkSuspensionLength(String suspensionLength){
        String error = "";
        if(suspensionLength.isEmpty()){
            error = "Suspension length required";
        }
        try{
            Integer.parseInt(suspensionLength);
        }catch(NumberFormatException e){
            error = "Please enter a valid suspension length (number)";
        }
        return error;
    }
}
