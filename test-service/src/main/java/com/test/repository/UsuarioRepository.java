package com.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.test.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
