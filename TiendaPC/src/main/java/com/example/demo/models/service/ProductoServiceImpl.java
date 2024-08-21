package com.example.demo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.dao.IProductoDao;
import com.example.demo.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao prDao;
	
	@Transactional(readOnly = true)
	@Override
	public List<Producto> findAll() {
		
		return prDao.findAll();
	}
	
	@Transactional
	@Override
	public void save(Producto producto) {
		prDao.save(producto);
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public Producto findOne(Long id) {
		
		return prDao.findOne(id) ;
	}

	@Override
	public void delete(Long id) {
		
		prDao.delete(id);
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<Producto> findByNombreProducto(String nombreProducto) {
		
		return prDao.findByNombreProducto(nombreProducto);
	}

}
