package br.com.neki.sistema_skill_refactored.domain;

import java.util.UUID;

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
	private UUID skillId;

	@Column(name = "skill_tx_skill_name", unique = true, nullable = false)
	private String skillName;

	@Column(name = "skill_tx_description", length = 150, nullable = false)
	private String description;
	
	@Column(name = "skill_tx_image", length = 300, nullable = false)
    private String image;

}
