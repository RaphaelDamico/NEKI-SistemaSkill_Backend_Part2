package br.com.neki.sistema_skill_refactored.domain;

import java.util.UUID;

import br.com.neki.sistema_skill_refactored.model.SkillCreateAndAssignModel;
import br.com.neki.sistema_skill_refactored.model.SkillCreateModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_skills")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Skill {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="skill_cd_id")
	private UUID id;

	@Column(name = "skill_tx_skill_name", unique = true)
	private String skillName;

	@Column(name = "skill_tx_description")
	private String description;
	
	@Column(name = "skill_tx_image")
    private String image;
	
	public Skill(SkillCreateModel skillCreateModel) {
		this.skillName = skillCreateModel.getSkillName();
		this.description = skillCreateModel.getDescription();
		this.image = skillCreateModel.getImage();
	}
	
	public Skill(SkillCreateAndAssignModel skillCreateAndAssignModel) {
		this.skillName = skillCreateAndAssignModel.getSkillName();
		this.description = skillCreateAndAssignModel.getDescription();
		this.image = skillCreateAndAssignModel.getImage();
	}

}
