package andProject.task_self.service;

import andProject.task_self.dto.personDTO.CreatePersonDTO;
import andProject.task_self.dto.personDTO.GetResponsePersonDTO;
import andProject.task_self.dto.personDTO.UpdatePersonDTO;
import andProject.task_self.model.Person;
import andProject.task_self.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;

    public GetResponsePersonDTO createPerson(CreatePersonDTO request){
        var person =modelMapper.map(request, Person.class);
        person.setCreation(LocalDateTime.now());
        person.setActive(true);
        personRepository.save(person);
        var response =modelMapper.map(person,GetResponsePersonDTO.class);
        return response;
    }

    public GetResponsePersonDTO getPerson(Long id) {
        var person=personRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        var response= modelMapper.map(person,GetResponsePersonDTO.class);
        return response;
    }

    public void deletePerson(Long id){
        var person=personRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        person.setActive(false);
        personRepository.save(person);
    }

    public GetResponsePersonDTO updatePerson(UpdatePersonDTO request){
        var person=personRepository.findById(request.getId()).orElseThrow(()-> new EntityNotFoundException());

        if(request.getName()!=null){
            person.setName(request.getName());
        }
        if (request.getBirthDate()!=null) {
            person.setBirthDate(request.getBirthDate());
        }

        personRepository.save(person);
        var response=modelMapper.map(person,GetResponsePersonDTO.class);
        return response;
    }




}
