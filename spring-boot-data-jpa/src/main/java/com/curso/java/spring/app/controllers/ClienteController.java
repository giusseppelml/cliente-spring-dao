package com.curso.java.spring.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.curso.java.spring.app.models.entity.Cliente;
import com.curso.java.spring.app.service.IClienteService;

@Controller
@SessionAttributes("cliente") // lo use para el editar en este ejercicio
public class ClienteController {
	
	/* @Qualifier("clienteDaoJPA") en caso de aver otro igual usar esta anotacion para distinguir
	 el nombre que esta en el @Qualifier("clienteDaoJPA") tiene que ser el mismo que en el repository
	 se coloca abajo del @Autowired*/
	
	@Autowired
	private IClienteService clienteService;
	
	@RequestMapping(value="/listar", method=RequestMethod.GET) //url de nuestra vista
	public String listar(Model model) {
		model.addAttribute("titulo", "Clientes");
		model.addAttribute("clientelist", clienteService.findAll());
		return "listar"; //el nombre de nuestra vista
	}
	
	@RequestMapping(value="/form") //url de nuestra vista crear
	public String createCliente(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "formulario de Cliente");
		return "form"; //el nombre de nuestra vista
	}
	
	@RequestMapping(value="/form/{id}") //url de nuestra vista modificar
	public String updateCliente(@PathVariable(value="id") long id, Map<String, Object> model) {
		Cliente cliente = null;
		if(id > 0) {
			cliente = clienteService.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST) //url de nuestra vista
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "formulario de Cliente");
			return "form";
		}
		clienteService.createCliente(cliente);
		status.setComplete(); // con esta linea elimino mi variable de session
		return "redirect:listar"; //el nombre de nuestra vista
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") long id) {
		
		if(id > 0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
	}
}
