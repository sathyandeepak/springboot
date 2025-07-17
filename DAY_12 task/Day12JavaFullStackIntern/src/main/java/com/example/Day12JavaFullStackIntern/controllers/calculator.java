package com.example.Day11JavaFullStackIntern.controllers;

public class calculator {

    public int addition(int n, int m) {
        return n + m;
    }

    public int sub(int n, int m) {
        return n - m;
    }

    public int mul(int n, int m) {
        return n * m;
    }

    public int div(int n, int m) {
        if (m == 0) return 0;
        return n / m;
    }
}