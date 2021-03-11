package com.company;

public class Main {

    public static void main(String[] args) {

        String str = "We learn Java";
        int i = 500;
        long l = 100500100L;
        boolean b = true;
        short s = 32000;
        char c = '$';
        byte bt = 65;
        float flt = 3.14f;
        double dbl = 3.1415;

        double pt = doPointThree(5.4f, 3.4f, 6.6f, 1.2f);
        System.out.println(pt);

        boolean pf = doPointFour(5,6);
        System.out.println(pf);


        isNumberPositive(100); // Point 5

        boolean ps = isNumberNegative(-5); // Point 6
        System.out.println(ps);

        greetings("World"); // Point 7


        isLeapYear(500); // Point 8
        isLeapYear(1600);
        isLeapYear(2000);
        isLeapYear(1700);
        isLeapYear(2100);
        isLeapYear(1800);
        isLeapYear(2200);
        isLeapYear(1900);
        isLeapYear(2300);


    }

    private static double doPointThree(float a, float b, float c, float d) {
        if (d == 0) return 0;
        return a * (b + (c / d));
    }

    private static boolean doPointFour(int a, int b) {
        int sum = a + b;
        return (sum >= 10 && sum <= 20);
    }

    private static void isNumberPositive(int a) {
        System.out.println("Number "+ a + " is " + (a >= 0 ? "positive" : "negative"));
    }

    private static boolean isNumberNegative(int a) {

        return a < 0;
    }

    private static void greetings(String name) {

        System.out.println("Hello, " + name);
    }

    private static void isLeapYear(int year) {
        var cmn = (year % 4 != 0) || ((year % 100 == 0) && (year % 400 != 0));
        System.out.println(year + " is " + (cmn ? "common" : "leap") + " year" );
    }
}
