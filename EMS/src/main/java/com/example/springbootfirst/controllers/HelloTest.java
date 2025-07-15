package com.example.springbootfirst.controllers;

public class HelloTest {
    @Test
    public void testHelloTest(){
    Hello hell = new Hello();
    String str = hell.helloTest();
        System.out.println(str);
    assertEquals("Hello Test",str);
 }

}
