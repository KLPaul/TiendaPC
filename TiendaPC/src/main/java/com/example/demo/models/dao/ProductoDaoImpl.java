package com.example.demo.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Producto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductoDaoImpl implements IProductoDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Producto> findAll() {
		
		return em.createQuery("from Producto").getResultList();
	}
	
	@Transactional
	@Override
	public void save(Producto producto) {
		
		if(producto.getIdProducto()!=null && producto.getIdProducto()>0) {
			em.merge(producto);
		}else {
			em.persist(producto);
		}
		
	}

	@Override
	public Producto findOne(Long id) {
		
		return em.find(Producto.class,id);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Producto> findByNombreProducto(String nombreProducto) {
		
		TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p WHERE p.nombreProducto LIKE :nombreProducto", Producto.class);
        query.setParameter("nombreProducto", "%" + nombreProducto + "%");
        return query.getResultList();
        
	}

}
