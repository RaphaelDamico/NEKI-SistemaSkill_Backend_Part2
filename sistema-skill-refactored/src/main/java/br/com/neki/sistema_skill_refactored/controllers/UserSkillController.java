package br.com.neki.sistema_skill_refactored.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.model.UserSkillModel;
import br.com.neki.sistema_skill_refactored.model.UserSkillUpdateLevelModel;
import br.com.neki.sistema_skill_refactored.services.UserSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user-skills")
@Tag(name = "User Skills", description = "Atualiza e deleta as skills associadas ao usuário")
public class UserSkillController {

	@Autowired
	UserSkillService userSkillService;
	
	 @Operation(
		        summary = "Este método captura todas as UserSkills do usuário autenticado, com opção de filtro por nome da skill e suporte a paginação.",
		        method = "GET"
		    )
		    @PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
		    @GetMapping
		    public ResponseEntity<Page<UserSkillModel>> getAllUserSkills(
		            @Parameter(name = "skillNameFilter", description = "Filtra as skills pelo nome") 
		            @RequestParam(value = "skillNameFilter", required = false) String skillNameFilter,
		            
		            @Parameter(description = "Parâmetros de paginação e ordenação", 
		                       schema = @Schema(type = "object", example = "{\"page\": 0, \"size\": 5, \"sort\": \"skill.skillName,asc\"}")) 
		            Pageable pageable) {
		        Page<UserSkillModel> userSkills = userSkillService.findAll(skillNameFilter, pageable);
		        return userSkills.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(userSkills);      
		    }
	 
	@Operation(summary = "Este método atualiza o level da skill cadastrada na lista de skills do usuário, garantindo que seu level seja no mínimo 1 e no máximo 5 e retornando as informações da skill com o level atualizado.", method = "PUT")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PutMapping("/level")
	public ResponseEntity<UserSkillModel> updateUserSkillLevel(
			@RequestBody @Valid UserSkillUpdateLevelModel userSkillUpdateLevelModel) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userSkillService.updateUserSkillLevel(userSkillUpdateLevelModel));
	}
	
	@Operation(summary = "Este método deleta a skill da lista de skills do usuário pelo seu userSkillID", method = "DELETE")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description="Id de uma skill associada a um usuário", example="3fa85f64-5717-4562-b3fc-2c963f66afa6")@PathVariable UUID id) {
		userSkillService.deleteUserSkillById(id);
		return ResponseEntity.noContent().build();
	}
}
