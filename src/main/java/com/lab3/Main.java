package com.lab3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Menu consoleMenu = context.getBean(Menu.class);

        consoleMenu.displayMenu();

        ((AnnotationConfigApplicationContext) context).close();
    }
}