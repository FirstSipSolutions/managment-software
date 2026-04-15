package org.keyin.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {

    @org.junit.jupiter.api.Test
    void hashPassword() {

        // adding first test for confirming hashed output must NOT match plan password text

        String hashed = PasswordUtil.hashPassword("Password123!");
        assertNotEquals("Password123!", hashed);


    }

    @org.junit.jupiter.api.Test
    void checkPassword() {


        // confirming correct password when passing bycrpt check

        String hashed = PasswordUtil.hashPassword("Password123!");
        assertTrue(PasswordUtil.checkPassword("Password123!", hashed));


    }

    // correction ticket was typed but meant to point in the direction of password not ticket
    // adding a confirm test for when a password is wrong
    // not sure how I will build this out but im going to push my work and debug


    @Test
    void checkWrongPassword() {
        // this is considered a negative test

        String hashed = PasswordUtil.hashPassword("Password123");
        assertFalse(PasswordUtil.checkPassword("wrongpassword", hashed));



    }
}