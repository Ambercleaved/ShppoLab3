package com.lab3;

import java.util.ArrayList;
import java.util.List;

public class PcSystem implements PComponent {
    private List<PComponent> components;

    public PcSystem() {
        this.components = new ArrayList<>();
    }

    public void addComponent(PComponent component) {
        components.add(component);
    }

    public void removeComponent(PComponent component) {
        components.remove(component);
    }

    public List<PComponent> getComponents() {
        return components;
    }

    @Override
    public int getId() {
        return 0; // Composite doesn't have a single ID
    }

    @Override
    public String getType() {
        return "PcSystem";
    }

    @Override
    public String getManufacturer() {
        return "Various";
    }

    @Override
    public String getName() {
        return "PcSystem";
    }

    @Override
    public void accept(ComponentVisitor visitor) {
        System.out.println("");
        for (PComponent component : components) {
            component.accept(visitor);
        }
    }
}
