package ru.mtuci.simpleapi1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.mtuci.simpleapi1.dao.DriversRepository;

@Component

public class CommandLineAppStartupRunner implements CommandLineRunner {

    private final DriversRepository driversRepository;

    @Autowired
    public CommandLineAppStartupRunner(DriversRepository driverRepository)
    {
        this.driversRepository = driverRepository;
    }
    @Override
    public void run(String...args) throws Exception
    {
        //System.out.println(driversRepository.findById(1L).get());
    }
}
