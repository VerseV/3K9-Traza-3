package Main.Java.Entidades;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString(exclude = "sucursalArticulos") // evita recursión infinita


public abstract class Articulo {
    protected Long id;
    protected String denominacion;



    @Builder.Default

    protected Set<ImagenArticulo> imagenes = new HashSet<>();


    protected UnidadMedida unidadMedida;


    @Builder.Default
    private Set<SucursalArticulo> sucursalArticulos = new HashSet<>();
    private Categoria categoria;


}

