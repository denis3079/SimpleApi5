package ru.mtuci.simpleapi1.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.simpleapi1.dao.DriversRepository;
import ru.mtuci.simpleapi1.model.Drivers;
import java.util.List;

@Service
public class DriversServiceImpl implements DriversService {

    private final DriversRepository driversRepository;

    @Autowired
    public DriversServiceImpl(DriversRepository driversRepository) {
        this.driversRepository = driversRepository;
    }

    @Override
    public Drivers get(Long id) {
        return driversRepository.findById(id).orElse(null);
    }

    @Override
    public List<Drivers> getAll() {
        return driversRepository.findAll();
    }

    @Override
    public Drivers save(Drivers drivers) {
        return driversRepository.save(drivers);
    }

    @Override
    public void delete(Long id) {
               driversRepository.delete(id);
    }
}
