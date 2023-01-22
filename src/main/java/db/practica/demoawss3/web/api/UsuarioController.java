package db.practica.demoawss3.web.api;

import db.practica.demoawss3.Models.Usuario;
import db.practica.demoawss3.Service.S3Service;
import db.practica.demoawss3.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    private S3Service s3Service;
    
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> obtenerLista() {
        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
    }

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario c) {
        usuarioService.save(c);
        c.setImagenUrl(s3Service.getObjetcUrl(c.getImagenUrl()));
        c.setCedulaUrl(s3Service.getObjetcUrl(c.getCedulaUrl()));
        return c;
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioService.delete(id);
    }


    @PutMapping("actualizar/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Integer id, @RequestBody Usuario c) {
        if (usuarioService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        c.setNombre(c.getNombre());
        c.setClave(c.getClave());
        c.setEmail(c.getEmail());
        c.setEstado(c.isEstado());

        Usuario newObjeto = usuarioService.save(c);
        return ResponseEntity.ok(newObjeto);
    }

}
