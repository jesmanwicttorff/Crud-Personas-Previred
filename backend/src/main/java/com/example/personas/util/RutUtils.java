package com.example.personas.util;

public class RutUtils {
    public static String clean(String rut) {
        return rut == null ? null : rut.replaceAll("[^0-9kK]", "").toUpperCase();
    }

    // validate Chilean RUT - implementation compatible with standard modulus 11
    public static boolean isValidRut(String rut) {
        if (rut == null) return false;
        String clean = clean(rut);
        if (clean.length() < 2) return false;
        String dv = clean.substring(clean.length()-1);
        String number = clean.substring(0, clean.length()-1);
        try {
            int m = 0, s = 1;
            int num = Integer.parseInt(number);
            while (num != 0) {
                s = (s + num % 10 * (9 - m++ % 6)) % 11;
                num /= 10;
            }
            String expected = (s == 0) ? "K" : String.valueOf((char) (s + 47));
            // expected may produce ascii-char, easier compute dv via standard algorithm:
            // we'll compute using classic algorithm:
        } catch (NumberFormatException e) {
            return false;
        }
        // use alternative correct algorithm below
        return checkRutAlgorithm(clean);
    }

    private static boolean checkRutAlgorithm(String clean) {
        try {
            String number = clean.substring(0, clean.length()-1);
            char dvChar = clean.charAt(clean.length()-1);
            int m = 0, sum = 0;
            for (int i = number.length()-1; i >= 0; i--) {
                int digit = Character.getNumericValue(number.charAt(i));
                sum += digit * (2 + (m % 6));
                m++;
            }
            int res = 11 - (sum % 11);
            char dvExpected;
            if (res == 11) dvExpected = '0';
            else if (res == 10) dvExpected = 'K';
            else dvExpected = Character.forDigit(res, 10);
            return Character.toUpperCase(dvChar) == dvExpected;
        } catch (Exception e) {
            return false;
        }
    }
}
