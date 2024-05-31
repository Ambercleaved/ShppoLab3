package com.lab3;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Menu {
    private SystemFacade systemFacade;
    private List<Motherboard> motherboards;
    private List<CPU> cpus;
    private List<GPU> gpus;
    private Scanner scanner;


    public Menu(SystemFacade systemFacade, List<Motherboard> motherboards, List<CPU> cpus, List<GPU> gpus) {
        this.systemFacade = systemFacade;
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.gpus = gpus;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
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
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить компонент");
            System.out.println("2. Вывести информацию о всех компонентах");
            System.out.println("3. Вывести все возможные варианты сборки систем");
            System.out.println("4. Выйти");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addComponent();
                        break;
                    case 2:
                        displayComponentInfo();
                        break;
                    case 3:
                        buildSystems();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Неверный выбор. Пожалуйста, выберите существующую опцию.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: Вы должны ввести целое число.");
                scanner.nextLine(); // Очистить буфер ввода
            }
        }
    }

    private void addComponent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип компонента:");
        System.out.println("1. Процессор");
        System.out.println("2. Видеокарта");
        System.out.println("3. Материнская плата");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCPU();
                    break;
                case 2:
                    addGPU();
                    break;
                case 3:
                    addMotherboard();
                    break;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите существующий тип компонента.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Вы должны ввести целое число.");
            scanner.nextLine(); // Очистить буфер ввода
        }
    }
    private void addCPU() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите производителя:");
        String manufacturer = scanner.nextLine().trim();

        System.out.println("Введите название:");
        String name = scanner.nextLine().trim();

        System.out.println("Введите количество ядер:");
        while (!scanner.hasNextInt()) {
            System.out.println("Неверный ввод. Пожалуйста, введите целое число для количества ядер.");
            System.out.print("Введите количество ядер: ");
            scanner.next();
        }
        int amountCores = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите сокет:");
        String socket = scanner.nextLine().trim();

        System.out.println("Введите частоту:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный ввод. Пожалуйста, введите число для частоты.");
            System.out.print("Введите частоту: ");
            scanner.next();
        }
        double clock = scanner.nextDouble();
        scanner.nextLine();


        CPU cpu = Factory.createCPU(manufacturer, name, amountCores, socket, clock);
        systemFacade.addCPU(cpu);
        System.out.println("Процессор успешно добавлен.");
    }

    private void addGPU() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите производителя:");
        String manufacturer = scanner.nextLine().trim();

        System.out.println("Введите название:");
        String name = scanner.nextLine().trim();

        System.out.println("Введите порт:");
        String port = scanner.nextLine().trim();

        System.out.println("Введите частоту:");
        while (!scanner.hasNextDouble()) {
            System.out.println("Неверный ввод. Пожалуйста, введите число для частоты.");
            System.out.print("Введите частоту: ");
            scanner.next();
        }
        double clock = scanner.nextDouble();
        scanner.nextLine();


        GPU gpu = Factory.createGPU(manufacturer, name, port, clock);
        systemFacade.addGPU(gpu);
        System.out.println("Видеокарта успешно добавлена.");
    }

    private void addMotherboard() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите производителя:");
        String manufacturer = scanner.nextLine().trim();

        System.out.println("Введите название:");
        String name = scanner.nextLine().trim();

        System.out.println("Введите порт:");
        String port = scanner.nextLine().trim();

        System.out.println("Введите сокет:");
        String socket = scanner.nextLine().trim();

        Motherboard mb = Factory.createMotherboard(manufacturer, name, port, socket);
        systemFacade.addMotherboard(mb);
        System.out.println("Материнская карта успешно добавлена.");
    }

    private void displayComponentInfo() {
        ComponentVisitor visitor = new ComponentVisitor();
        systemFacade.displayComponentInfo(visitor);

    }
    private void buildSystems() {
        ComponentVisitor visitor = new ComponentVisitor();
        systemFacade.buildAndOutputSystems(visitor);
    }
}