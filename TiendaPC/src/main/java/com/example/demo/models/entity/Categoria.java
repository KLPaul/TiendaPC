package com.example.demo.models.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;
	
	private String nombreCategoria;
	
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
    private List<Producto> productos;
	
	

	public Categoria() {
		
	}

	public Categoria(Long idCategoria, String nombreCategoria, List<Producto> productos) {
		
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.productos = productos;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	

}
