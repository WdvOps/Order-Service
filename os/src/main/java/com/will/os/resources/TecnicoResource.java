package com.will.os.resources;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.will.os.domain.Tecnico;
import com.will.os.dtos.TecnicoDTO;
import com.will.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

		Tecnico obj = service.findById(id);
		TecnicoDTO objDTO = new TecnicoDTO(obj);
		return ResponseEntity.ok().body(objDTO);
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		

		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = new ArrayList<>();

		for (Tecnico obj : list) {
			listDTO.add(new TecnicoDTO(obj));
		}

		list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	
}
