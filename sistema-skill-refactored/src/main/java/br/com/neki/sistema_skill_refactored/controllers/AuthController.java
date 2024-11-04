package br.com.neki.sistema_skill_refactored.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.neki.sistema_skill_refactored.records.JwtTokenRecord;
import br.com.neki.sistema_skill_refactored.records.LoginCredentialsRecord;
import br.com.neki.sistema_skill_refactored.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authenticator", description = "Autentica o usuário para acessar a aplicação")
public class AuthController {

    @Autowired
    UserService userService;
    
    @Operation(summary = "Este método autentica um usuário cadastrado para que ele possa ter acesso restrito retornando o token e o userID.", method = "POST")
    @PostMapping("/signin")
    public ResponseEntity<JwtTokenRecord> signin(@RequestBody LoginCredentialsRecord loginCredentialsRecord) {
        JwtTokenRecord jwtToken = userService.authenticateUser(loginCredentialsRecord);
        return ResponseEntity.status(HttpStatus.OK).body(jwtToken);
    }

}
