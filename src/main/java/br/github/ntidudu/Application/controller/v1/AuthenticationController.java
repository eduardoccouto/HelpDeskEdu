package br.github.ntidudu.Application.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.github.ntidudu.Application.service.AuthenticationService;



@RestController
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @PostMapping("login")
  public String authenticate(Authentication authentication) {
    return authenticationService.authenticate(authentication);
  }
}
