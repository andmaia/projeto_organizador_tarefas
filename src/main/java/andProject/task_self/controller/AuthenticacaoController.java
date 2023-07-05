package andProject.task_self.controller;


import andProject.task_self.dto.personCredentials.AuthenticationPersonDto;
import andProject.task_self.model.PersonCredentials;
import andProject.task_self.service.TokenService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;

@RestController
@RequestMapping("/api/login")
public class AuthenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity login (@RequestBody @Valid AuthenticationPersonDto request){
        var token = new UsernamePasswordAuthenticationToken(request.email(),request.password());
        var authentication = authenticationManager.authenticate(token);
        var tokenResponse = tokenService.gerarToken((PersonCredentials) authentication.getPrincipal());
        System.out.println(tokenResponse);
        return ResponseEntity.ok(tokenResponse);

    }


}
