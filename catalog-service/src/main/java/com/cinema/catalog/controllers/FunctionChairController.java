package com.cinema.catalog.controllers;

import com.cinema.catalog.entities.Function;
import com.cinema.catalog.entities.FunctionChair;
import com.cinema.catalog.services.FunctionChairService;
import com.cinema.catalog.services.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("functionChair")
public class FunctionChairController {
	
	@Autowired 
	private FunctionChairService chairService;

	@Autowired
	private FunctionService functionService;
	
	@GetMapping("chairsList")
	public List<FunctionChair> list() {
		return this.chairService.findAll();
	}
	
	@GetMapping("chair/{id}")
	public FunctionChair findById(@PathVariable Long id) {
		return this.chairService.findById(id);
	}

	@GetMapping("chairs-by-function/{idFun}")
	public ResponseEntity<?> chairsByFunction (@PathVariable Long idFun) {
		Function function = this.functionService.getFunction(idFun);
		if(function == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(this.chairService.findByFunction(function));
	}

	@PutMapping("/occupy")
	public ResponseEntity<?> reserve (@RequestBody List<Long> chairIds) {
		return ResponseEntity.ok(this.chairService.reserveChairs(chairIds));
	}
}
