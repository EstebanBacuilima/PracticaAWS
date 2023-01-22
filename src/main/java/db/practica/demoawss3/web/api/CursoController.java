package db.practica.demoawss3.web.api;

import db.practica.demoawss3.Models.Curso;
import db.practica.demoawss3.Repository.CursoRepository;
import db.practica.demoawss3.Service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/curso/")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    List<Curso> getAll(){
        return cursoRepository.findAll()
                .stream()
                .peek(curso -> curso.setImagenUrl(s3Service.getObjetcUrl(curso.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    Curso crear(@RequestBody Curso curso) {
        cursoRepository.save(curso);
        curso.setImagenUrl(s3Service.getObjetcUrl(curso.getImagenPath()));
        return curso;
    }
}
