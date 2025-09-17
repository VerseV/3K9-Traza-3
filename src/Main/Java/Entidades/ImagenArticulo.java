package Main.Java.Entidades;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ImagenArticulo {
    private Long id;
    private String name;
    private String url;

}
