package com.hanami.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanami.dto.PostagemDto;
import com.hanami.dto.PostagemUpdateDto;
import com.hanami.entity.Postagem;
import com.hanami.service.PostagemService;

//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	/*
	 * @GetMapping("/all") public List<Postagem> getAllPostagens() { return
	 * postagemService.getAllPostagens(); }
	 * 
	 * @GetMapping("/{id}") public ResponseEntity<Postagem>
	 * getPostagemById(@PathVariable Long id){ return
	 * ResponseEntity.ok(postagemService.getPostagemById(id)); }
	 */
	
	@GetMapping("/all")
	public List<PostagemDto> getAllPostagens() {
		List<Postagem> postagens = postagemService.getAllPostagens();
	    return postagens.stream()
	            .map(postagem -> {
	                PostagemDto postagemDTO = new PostagemDto();
	                postagemDTO.setId(postagem.getId());
	                postagemDTO.setTitulo(postagem.getTitulo());
	                postagemDTO.setConteudo(postagem.getConteudo());
	                return postagemDTO;
	            })
	            .toList();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostagemDto> getPostagemById(@PathVariable Long id) {
		Postagem postagem = postagemService.getPostagemById(id);
		PostagemDto postagemDTO = new PostagemDto();
		postagemDTO.setId(postagem.getId());
		postagemDTO.setTitulo(postagem.getTitulo());
		postagemDTO.setConteudo(postagem.getConteudo());
		return ResponseEntity.ok(postagemDTO);
		
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<Postagem> createPostagem(@RequestBody PostagemDto postagemDto) {
		Postagem postagem = new Postagem();
		postagem.setTitulo(postagemDto.getTitulo());
		postagem.setConteudo(postagemDto.getConteudo());
		
		Postagem savedPostagem = postagemService.createPostagem(postagem);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedPostagem);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Postagem> deletePostagem(@PathVariable Long id) {
		postagemService.deletePostagem(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PostagemDto> updatePostagem(@PathVariable Long id, @RequestBody PostagemUpdateDto postagemUpdateDto) {
		Postagem postagemExistente = postagemService.getPostagemById(id);
		
		postagemExistente.setTitulo(postagemUpdateDto.getTitulo());
	    postagemExistente.setConteudo(postagemUpdateDto.getConteudo());
	    
	    Postagem updatedPostagem = postagemService.updatePostagem(postagemExistente);
	    
	    PostagemDto postagemDTO = new PostagemDto();
	    postagemDTO.setId(updatedPostagem.getId());
	    postagemDTO.setTitulo(updatedPostagem.getTitulo());
	    postagemDTO.setConteudo(updatedPostagem.getConteudo());
	    
	    return ResponseEntity.ok(postagemDTO);
	}
	
	
}
