package Main.Java.Entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class SucursalArticulo {

    private Long id;
    private Integer stockActual;
    private Integer stockMinimo;
    private Integer stockMaximo;

    private Sucursal sucursal;   // Relación con sucursal
    private Articulo articulo;   // Relación con artículo
}

