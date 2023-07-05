package andProject.task_self.service;

import andProject.task_self.dto.personDTO.CreatePersonDTO;
import andProject.task_self.dto.personDTO.GetResponsePersonDTO;
import andProject.task_self.dto.personDTO.UpdatePersonDTO;
import andProject.task_self.dto.taskDTO.GetTaskDTO;
import andProject.task_self.model.Person;
import andProject.task_self.model.Task;
import andProject.task_self.repository.PersonCredentialsRepository;
import andProject.task_self.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonCredentialsRepository personCredentialsRepository;
    @Autowired
    private ModelMapper modelMapper;

    private final TypeMap<Person, GetResponsePersonDTO> personToGetPersonDTO;

    @Autowired
    public PersonService(ModelMapper modelMapper) {
        this.personToGetPersonDTO = modelMapper.createTypeMap(Person.class, GetResponsePersonDTO.class);
        this.personToGetPersonDTO.addMappings(
                mapper -> mapper.map(src -> src.getPersonCredentials().getEmail(), GetResponsePersonDTO::setEmail)
        );
    }


    public GetResponsePersonDTO createPerson(CreatePersonDTO request){
        var person =modelMapper.map(request, Person.class);
        var credentials=personCredentialsRepository.findById(request.getCredential()).orElseThrow(()->new EntityNotFoundException());
        person.setCreation(LocalDateTime.now());
        person.setActive(true);
        person.setPersonCredentials(credentials);
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
