package com.curso.java.spring.app.service;

import java.util.List;

import com.curso.java.spring.app.models.entity.Cliente;

public interface IClienteService {

public List<Cliente> findAll();
	
	public void createCliente(Cliente cliente);
	
	public Cliente findOne(long id);
	
	public void delete(long id);
}
