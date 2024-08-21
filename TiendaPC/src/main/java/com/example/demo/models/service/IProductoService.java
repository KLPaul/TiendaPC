package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAll();
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> findByNombreProducto(String nombreProducto);
	

}
