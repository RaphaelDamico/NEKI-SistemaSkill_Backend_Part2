package br.com.neki.sistema_skill_refactored.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.neki.sistema_skill_refactored.domain.enums.AccessType;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.model.input.UserCreateInput;
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
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_users")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;

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
	
	private AccessType accessType;
	
	public User(UserCreateInput userCreateInput) {
		this.username = userCreateInput.getUsername();
		this.password = userCreateInput.getPassword();
	}
	
	public User(UserDetailsModel userDetailsModel) {
		this.id = userDetailsModel.getId();
		this.username = userDetailsModel.getUsername();
		this.userSkills = userDetailsModel.getUserSkills();
	}
		
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(accessType.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
