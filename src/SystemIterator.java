import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class SystemIterator implements Iterator<SystemBuilder> {
    private List<Motherboard> motherboards;
    private List<CPU> cpus;
    private List<GPU> gpus;
    private int motherboardIndex = 0;
    private int cpuIndex = 0;
    private int gpuIndex = 0;

    public SystemIterator(List<Motherboard> motherboards, List<CPU> cpus, List<GPU> gpus) {
        this.motherboards = motherboards;
        this.cpus = cpus;
        this.gpus = gpus;
    }

    @Override
    public boolean hasNext() {
        return motherboardIndex < motherboards.size() &&
                cpuIndex < cpus.size() &&
                gpuIndex < gpus.size();
    }

    @Override
    public SystemBuilder next() {
        Motherboard currentMotherboard = motherboards.get(motherboardIndex);
        CPU currentCPU = cpus.get(cpuIndex);
        GPU currentGPU = gpus.get(gpuIndex);

        gpuIndex++;
        if (gpuIndex >= gpus.size()) {
            gpuIndex = 0;
            cpuIndex++;
            if (cpuIndex >= cpus.size()) {
                cpuIndex = 0;
                motherboardIndex++;
                if (motherboardIndex >= motherboards.size()) {
                    return null;
                }
            }
        }

        if (isCompatible(currentMotherboard, currentCPU, currentGPU)) {
            return new SystemBuilder()
                    .addMotherboard(currentMotherboard)
                    .addCPU(currentCPU)
                    .addGPU(currentGPU);
        }

        return null;
    }

    private boolean isCompatible(Motherboard motherboard, CPU cpu, GPU gpu) {
        return motherboard.getSocket().equals(cpu.getSocket()) &&
                motherboard.getPort().equals(gpu.getPort());
    }

    public void parallelIterateAndBuild(ComponentVisitor visitor, int numThreads) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        CompletionService<PcSystem> completionService = new ExecutorCompletionService<>(executor);

        for (int i = 0; i < numThreads; i++) {
            completionService.submit(() -> {
                while (hasNext()) {
                    SystemBuilder builder = next();
                    if (builder != null) {
                        return builder.build();
                    }
                }
                return null;
            });
        }

        for (int i = 0; i < numThreads; i++) {
            Future<PcSystem> future = completionService.take();
            try {
                PcSystem pcSystem = future.get();
                if (pcSystem != null) {
                    visitor.visit(pcSystem);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}