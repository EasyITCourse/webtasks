package services.impl;

import dao.DisciplineDao;
import dao.impl.DicsiplineDaoImpl;
import entity.Discipline;
import services.DisciplineServise;

import java.util.List;

/**
 * Created by admin on 21.04.2015.
 */
public class DisciplineServiceImpl implements DisciplineServise {

     private DisciplineDao disciplineDao =new DicsiplineDaoImpl();


    @Override
    public boolean modifing(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean add(int idS) {
        return false;
    }

    @Override
    public List<Discipline> getDisciplines() {

        //Логика проверки на null, варианты предоставления информации
        return disciplineDao.getDisciplines();
    }

    @Override
    public Discipline getById(int id) {
        return null;
    }
}
