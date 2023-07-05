package andProject.task_self.controller;

import andProject.task_self.dto.personCredentials.CreatePersonCredentialDTO;
import andProject.task_self.service.PersonCredentialsService;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/credemtials")
public class PersonCredentialsController {
    @Autowired
    private PersonCredentialsService  service;

    @PostMapping
    @Transactional
    public ResponseEntity<Class> create(@RequestBody @Valid CreatePersonCredentialDTO request){
            service.create(request);
            return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatePersonCredentialDTO> get(@PathVariable @NotNull Long id){
        var response = service.get(id);
        return ResponseEntity.ok(response);
    }

}
