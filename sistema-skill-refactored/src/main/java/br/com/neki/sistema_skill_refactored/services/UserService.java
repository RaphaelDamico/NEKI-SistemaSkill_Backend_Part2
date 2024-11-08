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
import org.springframework.security.core.context.SecurityContextHolder;
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
import br.com.neki.sistema_skill_refactored.model.UserCreateModel;
import br.com.neki.sistema_skill_refactored.model.UserDetailsModel;
import br.com.neki.sistema_skill_refactored.records.JwtTokenRecord;
import br.com.neki.sistema_skill_refactored.records.LoginCredentialsRecord;
import br.com.neki.sistema_skill_refactored.repositories.UserRepository;
import br.com.neki.sistema_skill_refactored.security.service.JwtTokenService;

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

	@Autowired
	private UserMapper userMapper;

	private final PasswordEncoder encryptedPassword = new BCryptPasswordEncoder();

	public List<UserDetailsModel> findAll() {
		List<User> users = userRepository.findAll();
		List<UserDetailsModel> userDetailsModel = new ArrayList<>();
		for (User user : users) {
			userDetailsModel.add(userMapper.toUserModel(user));
		}
		return userDetailsModel;
	}
	
	public UserDetailsModel findById(UUID userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
		return userMapper.toUserModel(user);
	}

	public void createSimpleUser(UserCreateModel userCreateModel) {
		Optional<User> existingUser = userRepository.findByUsername(userCreateModel.getUsername());
		if (existingUser.isPresent())
			throw new UsernameAlreadyExistsException(
					"The username" + userCreateModel.getUsername() + " already exists.");
		User userSave = userMapper.toEntity(userCreateModel);
		userSave.setPassword(encryptedPassword.encode(userCreateModel.getPassword()));
		userSave.setAccessType(AccessType.ROLE_SIMPLE);
		userRepository.save(userSave);
	}

	public JwtTokenRecord authenticateUser(LoginCredentialsRecord loginCredentialsRecord) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				loginCredentialsRecord.username(), loginCredentialsRecord.password());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		User userDetails = (User) authentication.getPrincipal();
		return new JwtTokenRecord(jwtTokenService.generateToken(userDetails), userDetails.getUserId());
	}
	
	public UUID getAuthenticatedUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User)
	        return ((User) principal).getUserId();
	    throw new IllegalStateException("User not authenticated or authentication principal type is unexpected.");
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
