package Main.Java;

import Main.Java.Entidades.*;
import Main.Java.Repositorio.InMemoryRepository;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // ==============================
        //   REPOSITORIOS
        // ==============================
        InMemoryRepository<Pais> paisRepo = new InMemoryRepository<>();
        InMemoryRepository<Provincia> provinciaRepo = new InMemoryRepository<>();
        InMemoryRepository<Localidad> localidadRepo = new InMemoryRepository<>();
        InMemoryRepository<Domicilio> domicilioRepo = new InMemoryRepository<>();
        InMemoryRepository<Sucursal> sucursalRepo = new InMemoryRepository<>();
        InMemoryRepository<Empresa> empresaRepo = new InMemoryRepository<>();
        InMemoryRepository<Categoria> categoriaRepo = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> insumoRepo = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> manufacturadoRepo = new InMemoryRepository<>();

        // ==============================
        //   PARTE TRAZA 1 (UBICACIÓN)
        // ==============================
        Pais argentina = Pais.builder().nombre("Argentina").build();
        paisRepo.save(argentina);

        Provincia buenosAires = Provincia.builder().nombre("Buenos Aires").pais(argentina).build();
        Provincia cordoba = Provincia.builder().nombre("Córdoba").pais(argentina).build();
        provinciaRepo.save(buenosAires);
        provinciaRepo.save(cordoba);
        argentina.getProvincias().add(buenosAires);
        argentina.getProvincias().add(cordoba);

        Localidad caba = Localidad.builder().nombre("CABA").provincia(buenosAires).build();
        Localidad laPlata = Localidad.builder().nombre("La Plata").provincia(buenosAires).build();
        Localidad cordobaCapital = Localidad.builder().nombre("Córdoba Capital").provincia(cordoba).build();
        Localidad villaCarlosPaz = Localidad.builder().nombre("Villa Carlos Paz").provincia(cordoba).build();
        localidadRepo.save(caba);
        localidadRepo.save(laPlata);
        localidadRepo.save(cordobaCapital);
        localidadRepo.save(villaCarlosPaz);
        buenosAires.getLocalidades().add(caba);
        buenosAires.getLocalidades().add(laPlata);
        cordoba.getLocalidades().add(cordobaCapital);
        cordoba.getLocalidades().add(villaCarlosPaz);

        Domicilio domCaba = Domicilio.builder().calle("Av. Corrientes").numero(1000).cp(1000).localidad(caba).build();
        Domicilio domLaPlata = Domicilio.builder().calle("Calle 50").numero(200).cp(1900).localidad(laPlata).build();
        Domicilio domCordobaCap = Domicilio.builder().calle("Bv. San Juan").numero(500).cp(5000).localidad(cordobaCapital).build();
        Domicilio domVCP = Domicilio.builder().calle("Av. San Martín").numero(300).cp(5152).localidad(villaCarlosPaz).build();
        domicilioRepo.save(domCaba);
        domicilioRepo.save(domLaPlata);
        domicilioRepo.save(domCordobaCap);
        domicilioRepo.save(domVCP);

        Sucursal suc1 = Sucursal.builder().nombre("Sucursal1 - CABA").horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0)).esCasaMatriz(true).domicilio(domCaba).build();
        Sucursal suc2 = Sucursal.builder().nombre("Sucursal2 - La Plata").horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0)).esCasaMatriz(false).domicilio(domLaPlata).build();
        Sucursal suc3 = Sucursal.builder().nombre("Sucursal3 - Córdoba Capital").horarioApertura(LocalTime.of(8, 30))
                .horarioCierre(LocalTime.of(17, 30)).esCasaMatriz(true).domicilio(domCordobaCap).build();
        Sucursal suc4 = Sucursal.builder().nombre("Sucursal4 - Villa Carlos Paz").horarioApertura(LocalTime.of(8, 30))
                .horarioCierre(LocalTime.of(17, 30)).esCasaMatriz(false).domicilio(domVCP).build();
        sucursalRepo.save(suc1);
        sucursalRepo.save(suc2);
        sucursalRepo.save(suc3);
        sucursalRepo.save(suc4);

        Empresa empresa1 = Empresa.builder().nombre("Empresa1").razonSocial("Empresa Uno SRL").cuil(20300123456L).build();
        Empresa empresa2 = Empresa.builder().nombre("Empresa2").razonSocial("Empresa Dos SA").cuil(27300987654L).build();
        empresa1.getSucursales().add(suc1);
        empresa1.getSucursales().add(suc2);
        empresa2.getSucursales().add(suc3);
        empresa2.getSucursales().add(suc4);
        suc1.setEmpresa(empresa1);
        suc2.setEmpresa(empresa1);
        suc3.setEmpresa(empresa2);
        suc4.setEmpresa(empresa2);
        empresaRepo.save(empresa1);
        empresaRepo.save(empresa2);

        // ==============================
        //   PARTE TRAZA 2 (ARTÍCULOS)
        // ==============================
        Categoria pizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).build();
        Categoria sandwiches = Categoria.builder().denominacion("Sandwiches").esInsumo(false).build();
        Categoria bebidas = Categoria.builder().denominacion("Bebidas").esInsumo(false).build();
        Categoria insumos = Categoria.builder().denominacion("Insumos").esInsumo(true).build();
        categoriaRepo.save(pizzas);
        categoriaRepo.save(sandwiches);
        categoriaRepo.save(bebidas);
        categoriaRepo.save(insumos);

        UnidadMedida kg = UnidadMedida.builder().denominacion("Kg").build();
        UnidadMedida litro = UnidadMedida.builder().denominacion("Litro").build();
        UnidadMedida gramos = UnidadMedida.builder().denominacion("Gramos").build();

        ArticuloInsumo sal = ArticuloInsumo.builder().denominacion("Sal").precioCompra(1.0).stockActual(100)
                .stockMinimo(10).stockMaximo(200).esParaElaborar(true).unidadMedida(gramos).categoria(insumos).build();
        ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").precioCompra(0.5).stockActual(50)
                .stockMinimo(5).stockMaximo(100).esParaElaborar(true).unidadMedida(kg).categoria(insumos).build();
        ArticuloInsumo aceite = ArticuloInsumo.builder().denominacion("Aceite").precioCompra(3.0).stockActual(30)
                .stockMinimo(3).stockMaximo(60).esParaElaborar(true).unidadMedida(litro).categoria(insumos).build();
        ArticuloInsumo carne = ArticuloInsumo.builder().denominacion("Carne").precioCompra(5.0).stockActual(20)
                .stockMinimo(2).stockMaximo(40).esParaElaborar(true).unidadMedida(kg).categoria(insumos).build();
        insumoRepo.save(sal);
        insumoRepo.save(harina);
        insumoRepo.save(aceite);
        insumoRepo.save(carne);

        ImagenArticulo img1 = ImagenArticulo.builder().name("HawaianaPizza1").url("http://example.com/pizza1").build();
        ImagenArticulo img2 = ImagenArticulo.builder().name("HawaianaPizza2").url("http://example.com/pizza2").build();
        ImagenArticulo img3 = ImagenArticulo.builder().name("HawaianaPizza3").url("http://example.com/pizza3").build();
        ImagenArticulo img4 = ImagenArticulo.builder().name("LomoCompleto1").url("http://example.com/lomo1").build();
        ImagenArticulo img5 = ImagenArticulo.builder().name("LomoCompleto2").url("http://example.com/lomo2").build();
        ImagenArticulo img6 = ImagenArticulo.builder().name("LomoCompleto3").url("http://example.com/lomo3").build();

        ArticuloManufacturadoDetalle det1Pizza = ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(sal).build();
        ArticuloManufacturadoDetalle det2Pizza = ArticuloManufacturadoDetalle.builder().cantidad(2).articuloInsumo(harina).build();
        ArticuloManufacturadoDetalle det3Pizza = ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(aceite).build();
        ArticuloManufacturadoDetalle det1Lomo = ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(sal).build();
        ArticuloManufacturadoDetalle det2Lomo = ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(aceite).build();
        ArticuloManufacturadoDetalle det3Lomo = ArticuloManufacturadoDetalle.builder().cantidad(2).articuloInsumo(carne).build();

        ArticuloManufacturado pizzaHawaina = ArticuloManufacturado.builder().denominacion("Pizza Hawaiana").precioVenta(12.0)
                .descripcion("Pizza con piña y jamón").tiempoEstimadoMinutos(20).preparacion("Hornear 20 min")
                .categoria(pizzas).unidadMedida(kg)
                .imagenes(new HashSet<>(Set.of(img1, img2, img3)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(det1Pizza, det2Pizza, det3Pizza))).build();

        ArticuloManufacturado lomoCompleto = ArticuloManufacturado.builder().denominacion("Lomo Completo").precioVenta(15.0)
                .descripcion("Lomo con todo").tiempoEstimadoMinutos(25).preparacion("Parrilla 25 min")
                .categoria(sandwiches).unidadMedida(kg)
                .imagenes(new HashSet<>(Set.of(img4, img5, img6)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(det1Lomo, det2Lomo, det3Lomo))).build();

        manufacturadoRepo.save(pizzaHawaina);
        manufacturadoRepo.save(lomoCompleto);

        // ==============================
        //   UNIFICACIÓN (SucursalArticulo)
        // ==============================
        SucursalArticulo sa1 = SucursalArticulo.builder().sucursal(suc1).articulo(pizzaHawaina)
                .stockActual(30).stockMinimo(5).stockMaximo(50).build();
        SucursalArticulo sa2 = SucursalArticulo.builder().sucursal(suc2).articulo(lomoCompleto)
                .stockActual(15).stockMinimo(3).stockMaximo(30).build();
        SucursalArticulo sa3 = SucursalArticulo.builder().sucursal(suc3).articulo(harina)
                .stockActual(80).stockMinimo(10).stockMaximo(150).build();

        suc1.getSucursalArticulos().add(sa1);
        suc2.getSucursalArticulos().add(sa2);
        suc3.getSucursalArticulos().add(sa3);
        pizzaHawaina.getSucursalArticulos().add(sa1);
        lomoCompleto.getSucursalArticulos().add(sa2);
        harina.getSucursalArticulos().add(sa3);

        // ==============================
        //   MOSTRAR RESULTADOS
        // ==============================
        System.out.println("=== Todas las Empresas ===");
        empresaRepo.findAll().forEach(System.out::println);

        System.out.println("\n=== Todas las Categorías ===");
        categoriaRepo.findAll().forEach(System.out::println);

        System.out.println("\n=== Artículos Insumos ===");
        insumoRepo.findAll().forEach(System.out::println);

        System.out.println("\n=== Artículos Manufacturados ===");
        manufacturadoRepo.findAll().forEach(System.out::println);

        System.out.println("\n=== Artículos por Sucursal ===");
        sucursalRepo.findAll().forEach(s -> {
            System.out.println("Sucursal: " + s.getNombre());
            s.getSucursalArticulos().forEach(sa -> {
                System.out.println(" - " + sa.getArticulo().getDenominacion() +
                        " | Stock: " + sa.getStockActual() +
                        " | Precio: " + sa.getArticulo().getPrecioVenta());
            });
        });
    }
}

