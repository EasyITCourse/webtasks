package dao;

import java.util.List;

import entity.Discipline;

public interface DisciplineDao {

	List<Discipline> getDisciplines();

	Discipline getById(int id);

	boolean update(int id);

	boolean deletee(int id);

}
