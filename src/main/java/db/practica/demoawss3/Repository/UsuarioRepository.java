package db.practica.demoawss3.Repository;

import db.practica.demoawss3.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
