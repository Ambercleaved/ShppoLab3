package com.lab3;

import java.util.List;


import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("logger")
public class AppConfig {

    @Bean
    @Scope("singleton")
    public SystemFacade systemFacade(List<Motherboard> motherboards, List<CPU> cpus, List<GPU> gpus) {
        return new SystemFacade(motherboards, cpus, gpus);
    }

    @Bean
    @Scope("singleton")
    public Menu menu(SystemFacade systemFacade, List<Motherboard> motherboards, List<CPU> cpus, List<GPU> gpus) {
        return new Menu(systemFacade, motherboards, cpus, gpus);
    }
}
