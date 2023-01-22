package db.practica.demoawss3.Models;


import jdk.jfr.Enabled;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Enabled
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Curso {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    private String titulo;

    private String imagenPath;

    @Transient
    private String imagenUrl;

}
