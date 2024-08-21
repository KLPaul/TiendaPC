package com.example.demo.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Categoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CategoriaDaoImpl implements ICategoriaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Categoria> findAll() {

		return em.createQuery("from Categoria").getResultList();
	}
	
	@Transactional
	@Override
	public void save(Categoria categoria) {
		
		if(categoria.getIdCategoria()!=null && categoria.getIdCategoria()>0) {
			em.merge(categoria);
		}else {
			em.persist(categoria);
		}
			
	}

	@Override
	public Categoria findOne(Long id) {
		
		return em.find(Categoria.class,id);
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		
		em.remove(findOne(id));
		
	}

}
