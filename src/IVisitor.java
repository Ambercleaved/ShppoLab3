public interface IVisitor {
    void visit(GPU gpu);
    void visit(CPU cpu);
    void visit(Motherboard mb);
    public void visit(PcSystem system);
}
