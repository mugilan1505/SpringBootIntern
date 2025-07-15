package com.example.springbootfirst.controllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        int result = calc.add(15, 13);
        System.out.println("Add: " + result);
        assertEquals(28, result);
    }
    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        int result = calc.subtract(30, 20);
        System.out.println("Subtract: " + result);
        assertEquals(10, result);
    }
    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        int result = calc.multiply(7, 8);
        System.out.println("Multiply: " + result);
        assertEquals(56, result);
    }
    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        int result = calc.divide(20, 5);
        System.out.println("Divide: " + result);
        assertEquals(4, result);
    }
    @Test
    public void testDivideByZero() {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}