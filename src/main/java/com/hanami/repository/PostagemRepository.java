package com.hanami.repository;

import com.hanami.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
