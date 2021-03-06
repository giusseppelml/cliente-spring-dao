package com.curso.java.spring.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.java.spring.app.models.dao.IClienteDao;
import com.curso.java.spring.app.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDao clienteDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return clienteDao.findAll();
	}

	@Override
	@Transactional
	public void createCliente(Cliente cliente) {
		clienteDao.createCliente(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(long id) {
		return clienteDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(long id) {
		clienteDao.delete(id);
		
	}

	
}
