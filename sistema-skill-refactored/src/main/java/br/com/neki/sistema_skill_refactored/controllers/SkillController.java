package br.com.neki.sistema_skill_refactored.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.domain.UserSkill;
import br.com.neki.sistema_skill_refactored.model.SkillAssignExistingModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateModel;
import br.com.neki.sistema_skill_refactored.model.SkillModel;
import br.com.neki.sistema_skill_refactored.services.SkillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skills")
public class SkillController {
	
	private final SkillService skillService;

	public SkillController(SkillService skillService) {
		this.skillService = skillService;
	}
	
	@GetMapping
	public ResponseEntity<List<SkillModel>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(skillService.findAll());
	}
	
	@PostMapping
	public ResponseEntity<SkillCreateModel> save(@RequestBody @Valid SkillCreateModel skillCreateModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.save(skillCreateModel));
	}
	
	@PostMapping("/create-add")
	public ResponseEntity<SkillCreateModel> saveAndAddToUser(@RequestBody @Valid SkillCreateAndAssignModel skillCreateAndAssignModel) {
		return ResponseEntity.status(HttpStatus.CREATED).body(skillService.saveAndAddToUser(skillCreateAndAssignModel));
	}
	
	@PostMapping("/add-existing")
    public ResponseEntity<List<UserSkill>> addExistingSkillToUser(@RequestBody @Valid List<SkillAssignExistingModel> listSkillAssignExistingModel) {
        return ResponseEntity.status(HttpStatus.OK).body(skillService.addExistingSkillToUser(listSkillAssignExistingModel));
    }

}