package com.will.os.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.will.os.domain.Cliente;
import com.will.os.dtos.ClienteDTO;
import com.will.os.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	/*
	 * Busca cliente por id
	 */

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {

		Cliente obj = service.findById(id);
		ClienteDTO objDTO = new ClienteDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}
	
	/*
	 * Lista todos os clientes
	 */

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {

		

		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = new ArrayList<>();

		for (Cliente obj : list) {
			listDTO.add(new ClienteDTO(obj));
		}
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	/*
	 * Cria novo cliente com validação de campos e excessões 
	 */
	
	@PostMapping
	public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO objDTO) {
		Cliente newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	/*
	 * Atualiza cliente com validação de campos e excessões
	 */
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDTO) {
		ClienteDTO newObj = new ClienteDTO(service.update(id, objDTO));
		
		return ResponseEntity.ok().body(newObj);
	}
	
	/*
	 * Deleta cliente
	 */
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
