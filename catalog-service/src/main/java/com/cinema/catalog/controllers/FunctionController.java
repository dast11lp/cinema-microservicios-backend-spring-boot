package com.cinema.catalog.controllers;

import com.cinema.catalog.entities.Function;
import com.cinema.catalog.services.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/functions")
public class FunctionController {

	@Autowired
	private FunctionService functionService;
	
	@GetMapping("list")
	public List<Function> getListFunctions () {
        return this.functionService.getFunctions();
	} 
	
	@GetMapping("{id}")
	public ResponseEntity<Function> Function (@PathVariable Long id) {
        return ResponseEntity.ok(this.functionService.getFunction(id));
	}
}
