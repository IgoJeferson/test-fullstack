package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.test.model.api.response.StatusReponse;
import com.test.service.UsuarioService;

@RestController
@RequestMapping(value = "api/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioResource {

	private final static Logger LOGGER = LoggerFactory.getLogger(UsuarioResource.class);

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() {
		LOGGER.debug("## Listando usuários");
		List<Usuario> usuarios = new ArrayList<>();
		try {
			usuarios = usuarioService.listar();

		} catch (Exception e) {
			LOGGER.error("ERROR: ", e);
			return ResponseEntity.badRequest().body(usuarios);
		}
		return ResponseEntity.ok().body(usuarios);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
		LOGGER.debug("## Buscando usuário pelo id {}", id);
		Usuario usuario = new Usuario();
		try {
			usuario = usuarioService.buscar(id);

		} catch (Exception e) {
			LOGGER.error("ERROR: ", e);
			return ResponseEntity.badRequest().body(usuario);
		}

		return ResponseEntity.ok().body(usuario);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		LOGGER.debug("## Salvando {} ", usuario);
		Usuario novoUsuario = new Usuario();
		try {
			novoUsuario = usuarioService.salvar(usuario);

		} catch (Exception e) {
			LOGGER.error("ERROR: ", e);
			return ResponseEntity.badRequest().body(novoUsuario);
		}

		return ResponseEntity.ok().body(novoUsuario);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<StatusReponse> deletar(@PathVariable Long id) {
		LOGGER.debug("## Removendo usuário com id {}", id);
		StatusReponse statusReponse = new StatusReponse();
		try {
			usuarioService.deletar(id);

		} catch (Exception e) {
			LOGGER.error("ERROR: ", e);
			statusReponse.setSuccess(false);
			return ResponseEntity.badRequest().body(statusReponse);
		}

		return ResponseEntity.ok().body(statusReponse);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> editar(@PathVariable Long id, @RequestBody Usuario usuario) {
		LOGGER.debug("## Alterando usuário com id {}", id);
		Usuario updatedUser = new Usuario();
		try {
			Usuario changeUser = usuarioService.buscar(id);

			if (changeUser != null) {
				changeUser.setNome(usuario.getNome());
				changeUser.setSexo(usuario.getSexo());
				changeUser.setTelefone(usuario.getTelefone());
				changeUser.setEmail(usuario.getEmail());

				updatedUser = usuarioService.salvar(changeUser);
			}

		} catch (Exception e) {
			LOGGER.error("ERROR: ", e);
			return ResponseEntity.badRequest().body(updatedUser);
		}

		return ResponseEntity.ok().body(updatedUser);
	}
}
