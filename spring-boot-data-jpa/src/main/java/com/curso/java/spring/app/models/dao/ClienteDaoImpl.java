package com.curso.java.spring.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.curso.java.spring.app.models.entity.Cliente;

//@Repository("clienteDaoJPA")
@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional
	public void createCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		if(cliente.getId() > 0) {
			em.merge(cliente);
		}else {
			em.persist(cliente);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void delete(long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}
}
