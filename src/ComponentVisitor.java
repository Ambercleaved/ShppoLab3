public class ComponentVisitor implements IVisitor{
    @Override
    public void visit(CPU cpu){
        System.out.println("Id: " + cpu.getId() + " , type: " + cpu.getType() +
                " , manufacturer: " + cpu.getManufacturer()+ " , name: " + cpu.getName()
                + " , Amount of cores: " + cpu.getAmountCores() +" , Socket: " + cpu.getSocket()
                + " , Clock: " + cpu.getCLock());


    }
    @Override
    public void visit(GPU gpu){
        System.out.println("Id: " + gpu.getId() + " , type: " + gpu.getType() +
                " , manufacturer: " + gpu.getManufacturer()+ " , name: " + gpu.getName()
                +" , Port: " + gpu.getPort() + " , Clock: " + gpu.getCLock());


    }
    @Override
    public void visit(Motherboard mb){
        System.out.println("Id: " + mb.getId() + " , type: " + mb.getType() +
                " , manufacturer: " + mb.getManufacturer()+ " , name: " + mb.getName()
                +" , Port: " + mb.getPort() + " , Socket: " + mb.getSocket());


    }

    @Override
    public void visit(PcSystem system) {
        System.out.println("System Configuration:");
        system.getCPU().accept(this);
        system.getGPU().accept(this);
        system.getMotherboard().accept(this);
        System.out.println();
    }
}
