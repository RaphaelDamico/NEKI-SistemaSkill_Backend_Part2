package br.com.neki.sistema_skill_refactored.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.sistema_skill_refactored.domain.User;
import br.com.neki.sistema_skill_refactored.domain.enums.AccessType;
import br.com.neki.sistema_skill_refactored.exceptions.UserNotFoundException;
import br.com.neki.sistema_skill_refactored.exceptions.UsernameAlreadyExistsException;
import br.com.neki.sistema_skill_refactored.mappers.UserMapper;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.model.input.UserCreateInput;
import br.com.neki.sistema_skill_refactored.records.JwtTokenRecord;
import br.com.neki.sistema_skill_refactored.records.LoginCredentialsRecord;
import br.com.neki.sistema_skill_refactored.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	private final PasswordEncoder encryptedPassword = new BCryptPasswordEncoder();
	
	public List<UserDetailsModel> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDetailsModel> userDetailsModel = new ArrayList<>();
		for (User user : users) {
			userDetailsModel.add(UserMapper.INSTANCE.toUserModel(user));
		}
		return userDetailsModel;
	}

	public void createSimpleUser(UserCreateInput userCreateInput) {
		Optional<User> existingUser = userRepository.findByUsername(userCreateInput.getUsername());
		if (existingUser.isPresent())
			throw new UsernameAlreadyExistsException("The username" + userCreateInput.getUsername() + " already exists.");
		User userSave = new User(userCreateInput);
		userSave.setPassword(encryptedPassword.encode(userCreateInput.getPassword()));
		userSave.setAccessType(AccessType.ROLE_SIMPLE);
		userRepository.save(userSave);
	}

	public JwtTokenRecord authenticateUser(LoginCredentialsRecord loginCredentialsRecord) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginCredentialsRecord.username(), loginCredentialsRecord.password());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		User userDetails = (User) authentication.getPrincipal();
		return new JwtTokenRecord(jwtTokenService.generateToken(userDetails), userDetails.getId());
	}

	

	public UserDetailsModel findById(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
		return UserMapper.INSTANCE.toUserModel(user);
	}

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Incorrect username or password!"));
		if (user == null) {
			throw new UserNotFoundException("User not found!");
		}
		return user;
	}

}

