package jap.reflection.classes;

import java.io.Console;

class RetrievingPassword {
    public static void main(String[] args) {
        Class<? extends Console> cl = System.console().getClass();
        System.out.println(cl);
        Console console = System.console();
        char[] password = console.readPassword("Enter password: ");
        console.printf(String.valueOf(password) + "\n");
    }
}
