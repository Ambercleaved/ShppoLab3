package com.lab3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SystemFacade systemFacade = context.getBean(SystemFacade.class);

        CPU cpu1 = Factory.createCPU("Intel","i5-8400",6,"Lga" ,3.6);
        GPU gpu1 = Factory.createGPU("Nvidia", "RTX 2060s","pci 3.0", 16000);
        Motherboard mb1 = Factory.createMotherboard("Asus","Asrock z345","pci 3.0","Lga");
        CPU cpu2 = Factory.createCPU("AMD","Ryzen 7 5800X",8,"AM4",4.7);
        GPU gpu2 = Factory.createGPU("AMD", "RX 6700 XT","pci 4.0", 24200);
        Motherboard mb2 = Factory.createMotherboard("Gigabyte","B550 Aorus Elite","pci 4.0","AM4");
        CPU cpu3 = Factory.createCPU("Intel","I7 - 11500K",10,"Lga",4.2);
        GPU gpu3 = Factory.createGPU("Nvidia", "GTX 1660","pci 3.0", 10000);
        Motherboard mb3 = Factory.createMotherboard("Lenovo","L 564","pci 3.0","Lga");

        systemFacade.addCPU(cpu1);
        systemFacade.addCPU(cpu2);
        systemFacade.addCPU(cpu3);
        systemFacade.addGPU(gpu1);
        systemFacade.addGPU(gpu2);
        systemFacade.addGPU(gpu3);
        systemFacade.addMotherboard(mb1);
        systemFacade.addMotherboard(mb2);
        systemFacade.addMotherboard(mb3);
        Menu consoleMenu = context.getBean(Menu.class);

        consoleMenu.displayMenu();

        ((AnnotationConfigApplicationContext) context).close();
    }
}