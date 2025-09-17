package Main.Java.Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(exclude = "empresa")  // Se excluye empresa para evitar la recursividad infinita
@SuperBuilder

public class Sucursal {

    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;

    @Builder.Default
    private Set<SucursalArticulo> sucursalArticulos = new HashSet<>();
    private Domicilio domicilio;
    private Empresa empresa;


}
