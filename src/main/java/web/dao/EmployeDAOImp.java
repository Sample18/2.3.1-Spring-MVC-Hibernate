package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.Employe;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeDAOImp implements EmployeDAO {

    private final EntityManager entityManager;

    @Autowired
    public EmployeDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employe> getAllEmploye() {
        try {
            return entityManager.createQuery("from Employe", Employe.class).getResultList();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void remove(int id) {
        try {
            entityManager.getTransaction().begin();
            Employe employe = entityManager.find(Employe.class, id);
            if (employe != null) {
                entityManager.remove(employe);
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void create(Employe employe) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(employe);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public void update(Employe employe) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(employe);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }

    @Override
    public Employe findById(int id) {
        try {
            Employe employe;
            entityManager.getTransaction().begin();
            employe = entityManager.find(Employe.class, id);
            entityManager.getTransaction().commit();
            return employe;
        } catch (RuntimeException e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        }
    }
}
