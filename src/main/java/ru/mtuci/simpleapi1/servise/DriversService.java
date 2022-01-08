package ru.mtuci.simpleapi1.servise;

import ru.mtuci.simpleapi1.model.Drivers;
import java.util.List;

public interface DriversService {
    Drivers get(Long id); //прописать исключения надо
    List<Drivers> getAll();
    Drivers save(Drivers drivers);
    void delete(Long id);
}
