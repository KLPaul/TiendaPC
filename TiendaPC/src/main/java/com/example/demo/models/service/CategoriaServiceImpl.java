package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.ICategoriaDao;
import com.example.demo.models.entity.Categoria;

@Service
public class CategoriaServiceImpl implements ICategoriaService{
	
	@Autowired
	private ICategoriaDao catDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findAll() {
	
		return catDao.findAll();
	}
	
	@Transactional
	@Override
	public void save(Categoria categoria) {
		
		catDao.save(categoria);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public Categoria findOne(Long id) {
		
		return catDao.findOne(id);
	}

	@Override
	public void delete(Long id) {
		
		catDao.delete(id);
		
	}

}
