package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.Usuario;
import com.test.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listar(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	public Usuario buscar(Long id){
		return usuarioRepository.findOne(id);
	}
	
	public Usuario salvar(Usuario usuario){
		return usuarioRepository.save(usuario);
	}
	
	public void deletar(Long id){
		usuarioRepository.delete(id);
	}
	
}
