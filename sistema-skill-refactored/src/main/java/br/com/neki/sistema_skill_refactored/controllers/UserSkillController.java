package br.com.neki.sistema_skill_refactored.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.model.input.UserSkillUpdateLevelInput;
import br.com.neki.sistema_skill_refactored.services.UserSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-skills")
@Tag(name = "User Skills", description = "Atualiza e deleta as skills associadas ao usuário")
public class UserSkillController {

	@Autowired
	UserSkillService userSkillService;

	@Operation(summary = "Este método atualiza o level da skill cadastrada na lista de skills do usuário, garantindo que seu level seja no mínimo 1 e no máximo 5 e retornando as informações da skill com o level atualizado.", method = "PUT")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PutMapping("/level")
	public ResponseEntity<UserSkillModel> updateUserSkillLevel(
			@RequestBody @Valid UserSkillUpdateLevelInput userSkillUpdateLevelInput) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userSkillService.updateUserSkillLevel(userSkillUpdateLevelInput));
	}
	
	@Operation(summary = "Este método deleta a skill da lista de skills do usuário pelo seu userSkillID", method = "DELETE")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description="Id de uma skill associada a um usuário", example="3fa85f64-5717-4562-b3fc-2c963f66afa6")@PathVariable UUID id) {
		userSkillService.deleteUserSkillById(id);
		return ResponseEntity.noContent().build();
	}
}
