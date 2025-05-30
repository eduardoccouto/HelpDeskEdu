package br.github.ntidudu.Application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.dto.loginDTO;
import br.github.ntidudu.Application.service.AuthenticationService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  private final AuthenticationManager authenticationManager;

  

  public AuthenticationController(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @PostMapping("login")
  public String authenticate(Authentication authentication) {

    return authenticationService.authenticate(authentication);
  }

  @PostMapping("/auth")
  public String authFormLogin(@RequestBody @Valid loginDTO login) {

   Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(login.username(), login.password());
   
   var auth = authenticationManager.authenticate(authentication);
   System.out.println(auth);

   return authenticationService.authenticate(auth);

  }

}
