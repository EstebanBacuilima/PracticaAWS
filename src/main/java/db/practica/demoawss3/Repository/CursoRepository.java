package db.practica.demoawss3.Repository;

import db.practica.demoawss3.Models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository <Curso, Integer> {
//
//    public Curso save(Curso curso);
//
//    public List<Curso> findByAll();
}
