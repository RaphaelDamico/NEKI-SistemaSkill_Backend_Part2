package br.com.neki.sistema_skill_refactored.domain;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tbl_user_skill")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserSkill {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_skill_cd_id")
	private UUID id;
	

	@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_cd_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "skill_cd_id")
	private Skill skill;

	@Column(name = "user_skill_int_level")
	private Integer level;

}
