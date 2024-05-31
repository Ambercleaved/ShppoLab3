package com.lab3;

public interface IVisitor {
    void visit(GPU gpu);
    void visit(CPU cpu);
    void visit(Motherboard mb);
}

