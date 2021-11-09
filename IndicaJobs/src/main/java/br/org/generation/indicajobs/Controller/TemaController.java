package br.org.generation.indicajobs.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.indicajobs.Repository.TemaRepository;
import br.org.generation.indicajobs.model.Tema;

@RestController
@RequestMapping("/tema")
@CrossOrigin(origins="*", allowedHeaders="*")
public class TemaController {
	
	@Autowired
	private TemaRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	@GetMapping("/{idTema}")
	public ResponseEntity<Tema> getById(@PathVariable Long idTema) {
		return repository.findById(idTema).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	@GetMapping("/tituloTema/{tituloTema}")
	public ResponseEntity<List<Tema>> getByTituloTema(@PathVariable String tituloTema){
		return ResponseEntity.ok(repository.findAllByTituloTemaContainingIgnoreCase(tituloTema));	
	}
	@PostMapping
	public ResponseEntity<Tema> postTema(@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
	}
	@PutMapping
	public ResponseEntity<Tema> putTema(@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	@DeleteMapping
	public void deleteTema(@PathVariable Long idTema) {
		repository.deleteById(idTema);
	}
}
