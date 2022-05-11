package com.generation.games.controller;

import com.generation.games.model.Produto;
import com.generation.games.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	public ProdutoController(ProdutoRepository repository) {
		this.repository = repository;
	}

	@GetMapping
    ResponseEntity<List<Produto>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
	
	@GetMapping("/{id}")
	ResponseEntity<Produto> GetById(@PathVariable long id) {
		return repository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByTitle(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto novoProduto){
		System.out.println(novoProduto.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(novoProduto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto novoProduto){

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(novoProduto));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
		
	}
	
	
	
}
