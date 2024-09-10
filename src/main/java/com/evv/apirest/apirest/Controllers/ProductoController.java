package com.evv.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evv.apirest.apirest.Entities.Producto;
import com.evv.apirest.apirest.Repositories.ProductoRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/Productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoPorId(@RequestParam Long id) {
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el Id: " + id));
    }
    

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto detalleProducto) {
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el Id: " + id));

        producto.setNombre(detalleProducto.getNombre());
        producto.setPrecio(detalleProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontro el Producto con el Id: " + id));

        productoRepository.delete(producto);

        return "El Producto con el Id: " + id + " fue eliminado correctamente.";

        /*si se quiere devolver el producto se cambiaria el tipo de valor a retornar del metodo a Producto 
          y el return quedaria así 
          return productoRepository.delete(producto);
          esto devuelve el producto
        */
    }
}
