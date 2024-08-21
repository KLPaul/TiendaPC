package com.example.demo.models.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProducto;
	
	private String nombreProducto;
	private Long cantidad;
	private String ubicacion;
	private Date fechaCaducidad;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCategoria")
    private Categoria categoria;
	
	


	public Producto() {
		
	}


	public Producto(Long idProducto, String nombreProducto, Long cantidad, String ubicacion, Date fechaCaducidad,
			Categoria categoria) {
		
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.ubicacion = ubicacion;
		this.fechaCaducidad = fechaCaducidad;
		this.categoria = categoria;
	}


	public Long getIdProducto() {
		return idProducto;
	}


	public String getNombreProducto() {
		return nombreProducto;
	}


	public Long getCantidad() {
		return cantidad;
	}


	public String getUbicacion() {
		return ubicacion;
	}


	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}


	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}


	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}


	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}


	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	

}
