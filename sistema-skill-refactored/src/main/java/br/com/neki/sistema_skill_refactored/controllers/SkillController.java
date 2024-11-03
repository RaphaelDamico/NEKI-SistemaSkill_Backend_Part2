package br.com.neki.sistema_skill_refactored.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.SkillAssignExistingModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.model.input.SkillCreateInput;
import br.com.neki.sistema_skill_refactored.services.SkillService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	@Autowired
	SkillService skillService;
	
	@Operation(summary = "Este método captura todas as skills cadastradas no banco e também filtra por nome caso o consumidor da API deseje, retornando o skillId, skillName, descrição e o url da imagem da skill.", method = "GET")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@GetMapping
	public List<SkillModel> getAllSkills(
	    @RequestParam(value = "skillNameFilter", required = false) String skillNameFilter) {
	    return skillService.findAll(skillNameFilter);
	}
	
	@Operation(summary = "Este método cadastra uma skill no banco, garantindo que o nome da skill seja único, que possua descrição e que além de possuir url, ele seja válido.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Void> save(@RequestBody @Valid SkillCreateInput skillCreateModel) {
		skillService.save(skillCreateModel);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@Operation(summary = "Este método cadastra uma skill no banco e a atribui ao usuário que a cadastrou, garantindo que o nome da skill seja único, que possua descrição e que além de possuir url, ele seja válido. Além disso, neste método, o level é requisitado, devendo ser entre 1 e 5, retornando o nome da skill, a descrição, o url da imagem e o level da skill na lista de skills do usuário.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/create-add")
	public ResponseEntity<SkillCreateInput> saveAndAddToUser(@RequestBody @Valid SkillCreateAndAssignModel skillCreateAndAssignModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveAndAddToUser(skillCreateAndAssignModel));
	}
	
	@Operation(summary = "Este método adiciona uma skill existente à lista de skills do usuário. Ao passar o ID do usuário e o ID da skill, ela é adicionada à lista, retornando o userSkillId, a skill adicionada com seu ID, nome, descrição, url da imagem e o level setado como 1, pressupondo que ao adicionar a skill em sua lista o usuário já possua um nível básico de conhecimento da skill.", method = "POST")
	@PreAuthorize("hasAnyRole('ROLE_SIMPLE', 'ROLE_ADMIN')")
	@PostMapping("/add-existing")
    public ResponseEntity<List<UserSkill>> addExistingSkillToUser(@RequestBody @Valid List<SkillAssignExistingModel> listSkillAssignExistingModel) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.addExistingSkillToUser(listSkillAssignExistingModel));
    }
	
}