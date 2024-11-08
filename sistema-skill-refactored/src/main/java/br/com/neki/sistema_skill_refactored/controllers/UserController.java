package br.com.neki.sistema_skill_refactored.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.core.validation.ValidationSequence;
import br.com.neki.sistema_skill_refactored.model.UserCreateModel;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Cadastra e lista todos os usuários, ou lista especificamente pelo Id")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Operation(summary = "Este método captura todos os usuários cadastrados, retornando o userId, username e suas listas de skills.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDetailsModel>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@Operation(summary = "Este método captura o usuário cadastrado pelo seu ID, retornando o userId, username e sua lista de skills.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDetailsModel> findById(@Parameter(description = "Id de um usuário", example= "3fa85f64-5717-4562-b3fc-2c963f66afa6")@PathVariable UUID userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
	}
	
	@Operation(summary = "Este método cadastra um usuário com acesso restrito, garantindo que o nome de usuário seja único e retornando o username e o password criptografado.", method = "POST")
	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody @Validated(ValidationSequence.class) UserCreateModel userCreateModel) {
	    userService.createSimpleUser(userCreateModel);
	    return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
