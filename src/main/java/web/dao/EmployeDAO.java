package web.dao;


import web.model.Employe;

import java.util.List;

public interface EmployeDAO {
    List<Employe> getAllEmploye();
    void remove(int id);

    void create(Employe employe);

    void update(Employe employe);

    Employe findById(int id);

}
