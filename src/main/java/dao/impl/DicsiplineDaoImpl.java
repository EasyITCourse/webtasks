package dao.impl;

import dao.DisciplineDao;
import database.DataService;
import entity.Discipline;

import java.util.List;


public class DicsiplineDaoImpl implements DisciplineDao {
    private DataService dataService = new DataService();

    @Override
    public List<Discipline> getDisciplines() {
        // или MySQL или PSQL

        return dataService.getDisciplineList();
    }

    @Override
    public Discipline getById(int id) {
        return null;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean deletee(int id) {
        return false;
    }
}
