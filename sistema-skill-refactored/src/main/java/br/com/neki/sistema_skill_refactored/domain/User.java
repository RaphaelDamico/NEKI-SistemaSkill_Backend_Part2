package br.com.neki.sistema_skill_refactored.domain;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tbl_users")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_cd_id")
	private UUID id;
	
	@Column(name = "user_tx_username", unique = true)
	private String username;
	
	@Column(name = "user_tx_password")
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<UserSkill> userSkills;
}
