package web.service;

import web.model.Employe;

import java.util.List;

public interface EmployeService {
    List<Employe> getAllEmploye();

    void remove(int id);

    void create(Employe employe);

    void update(Employe employe);
    Employe findById(int id);
}
