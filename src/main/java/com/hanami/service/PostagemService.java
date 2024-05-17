package com.hanami.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanami.entity.Postagem;
import com.hanami.repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	
	public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }
	
	public List<Postagem> getAllPostagens() {
		return postagemRepository.findAll();
	}
	
	public Postagem createPostagem(Postagem postagem) {
        if (postagem.getTitulo().isEmpty() || postagem.getConteudo().isEmpty()) {
            throw new IllegalArgumentException("*** Título e / ou Conteúdo não devem estar vazios ou nulos! ***");
        }

        return postagemRepository.save(postagem);
    }
	
	public Postagem getPostagemById(Long id) {
		return postagemRepository.findById(id).get();
	}
	
	public void deletePostagem(Long id) {
		postagemRepository.deleteById(id);
	}
	
	public Postagem updatePostagem(Postagem postagem) {
	    Postagem postagemExistente = getPostagemById(postagem.getId());
	    
	    postagemExistente.setTitulo(postagem.getTitulo());
	    postagemExistente.setConteudo(postagem.getConteudo());
	    
	    if (postagemExistente.getTitulo().isEmpty() || postagemExistente.getConteudo().isEmpty()) {
	        throw new IllegalArgumentException("*** Título e/ou Conteúdo não devem estar vazios ou nulos! ***");
	    }
	    
	    return postagemRepository.save(postagemExistente);
	}
	
	
}
