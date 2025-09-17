package Main.Java.Entidades;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString


@Builder
public class ArticuloManufacturadoDetalle {

    private Long id;
    private Integer cantidad;


    private ArticuloInsumo articuloInsumo;
}
