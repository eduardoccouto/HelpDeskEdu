package br.github.ntidudu.Application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.dto.loginDTO;
import br.github.ntidudu.Application.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
@Tag(name = "Autenticação")
@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  private final AuthenticationManager authenticationManager;

  

  public AuthenticationController(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Operation(summary = "Login via Basic Auth")
  @PostMapping("login")
  public String authenticate(Authentication authentication) {

    return authenticationService.authenticate(authentication);
  }

  @Operation(summary = "Login via Json Body")
  @PostMapping("/auth")
  public String authFormLogin(@RequestBody @Valid loginDTO login) {

   Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(login.username(), login.password());
   
   var auth = authenticationManager.authenticate(authentication);
   System.out.println(auth);

   return authenticationService.authenticate(auth);

  }

}
