package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Categoria;

public interface ICategoriaService {
	
	public List<Categoria> findAll();
	
	public void save(Categoria categoria);
	
	public Categoria findOne(Long id);
	
	public void delete(Long id);
	

}
