package com.lab3;

import java.util.List;


public class SystemFacade {
    private List<Motherboard> motherboards;
    private List<CPU> cpus;
    private List<GPU> gpus;


    public SystemFacade(List<Motherboard> motherboards, List<CPU> cpus, List<GPU> gpus) {
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.gpus = gpus;
    }

    public void displayComponentInfo(ComponentVisitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("ComponentVisitor cannot be null");
        }
        System.out.println("Процессоры:");
        for (CPU cpu : cpus) {
            cpu.accept(visitor);
        }

        System.out.println("Видеокарты:");
        for (GPU gpu : gpus) {
            gpu.accept(visitor);
        }

        System.out.println("Материнские платы:");
        for (Motherboard mb : motherboards) {
            mb.accept(visitor);
        }
    }

    public void buildAndOutputSystems(ComponentVisitor visitor) {
        if (visitor == null) {
            throw new IllegalArgumentException("ComponentVisitor cannot be null");
        }

        // Use parallelIterateAndBuild to iterate and build systems in parallel
        try {
            SystemIterator systemIterator = new SystemIterator(motherboards, cpus, gpus);
            int numThreads = systemIterator.countCombinations();
            systemIterator.parallelIterateAndBuild(visitor, numThreads);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void addCPU(CPU cpu) {
        cpus.add(cpu);
    }

    public void addGPU(GPU gpu) {
        gpus.add(gpu);
    }

    public void addMotherboard(Motherboard motherboard) {
        motherboards.add(motherboard);
    }
}
