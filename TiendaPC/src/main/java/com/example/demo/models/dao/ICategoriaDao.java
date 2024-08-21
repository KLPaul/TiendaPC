package com.example.demo.models.dao;

import java.util.List;

import com.example.demo.models.entity.Categoria;


public interface ICategoriaDao {
	
	public List<Categoria> findAll();
	
	public void save(Categoria categoria);
	
	public Categoria findOne(Long id);
	
	public void delete(Long id);
	
	

}
