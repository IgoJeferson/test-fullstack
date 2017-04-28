package com.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Usuario;
import com.test.service.UsuarioService;

@RestController
@RequestMapping(value = "api/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioResource {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(UsuarioResource.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public List<Usuario> listar(){
		LOGGER.debug("## Listando usuários");
		return usuarioService.listar();
	}

	@GetMapping(value="/{id}")
	public Usuario buscar(@PathVariable Long id) {
		LOGGER.debug("## Buscando usuário pelo id {}", id);
		return usuarioService.buscar(id);
	}

	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public Usuario salvar(@RequestBody Usuario usuario) {
		LOGGER.debug("## Salvando {} ", usuario);
		return usuarioService.salvar(usuario);
	}

	@DeleteMapping(value="/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		LOGGER.debug("## Removendo usuário com id {}", id);
		usuarioService.deletar(id);
	}
	
	@PutMapping(value="/{id}")
	public Usuario editar(@PathVariable Long id, @RequestBody Usuario usuario) {
		LOGGER.debug("## Alterando usuário com id {}", id);
		Usuario changeUser = usuarioService.buscar(id);
		changeUser.setNome(usuario.getNome());
		changeUser.setSexo(usuario.getSexo());
		changeUser.setTelefone(usuario.getTelefone());
		changeUser.setEmail(usuario.getEmail());
		
		return usuarioService.salvar(changeUser);
	}
}
