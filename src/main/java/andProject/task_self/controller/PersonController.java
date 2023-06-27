package andProject.task_self.controller;

import andProject.task_self.dto.personDTO.CreatePersonDTO;
import andProject.task_self.dto.personDTO.GetResponsePersonDTO;
import andProject.task_self.dto.personDTO.UpdatePersonDTO;
import andProject.task_self.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    @Transactional
    public ResponseEntity<GetResponsePersonDTO> create (@RequestBody @Valid CreatePersonDTO request, UriComponentsBuilder builder){
        var response = personService.createPerson(request);
        var uri = builder.path("api/person/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponsePersonDTO> getOnePerson(@PathVariable @NotNull Long id){
        var response = personService.getPerson(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity deletePerson(@PathVariable @NotNull long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetResponsePersonDTO> updatePerson(@RequestBody @Valid UpdatePersonDTO request){
        var person= personService.updatePerson(request);
        return ResponseEntity.ok(person);
    }
}
