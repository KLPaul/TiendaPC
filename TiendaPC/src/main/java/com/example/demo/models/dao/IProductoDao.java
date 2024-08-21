package com.example.demo.models.dao;

import java.util.List;

import com.example.demo.models.entity.Producto;

public interface IProductoDao {
	
public List<Producto> findAll();
	
	public void save(Producto producto);
	
	public Producto findOne(Long id);
	
	public void delete(Long id);
	
	public List<Producto> findByNombreProducto(String nombreProducto);

}
