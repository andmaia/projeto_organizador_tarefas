package andProject.task_self.service;

import andProject.task_self.dto.personCredentials.AuthenticationPersonDto;
import andProject.task_self.dto.personCredentials.CreatePersonCredentialDTO;
import andProject.task_self.dto.personCredentials.GetPersonCredentials;
import andProject.task_self.model.PersonCredentials;
import andProject.task_self.repository.PersonCredentialsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonCredentialsService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PersonCredentialsRepository personCredentialsRepository;
    @Autowired
    private PasswordEncoder encoder;


    public void create(CreatePersonCredentialDTO request){
        if(personCredentialsRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("The email already exists.");
        }
        var personCredentials = modelMapper.map(request, PersonCredentials.class);
        var hashPassword =encoder.encode(personCredentials.getPassword());
        personCredentials.setPassword(hashPassword);
        personCredentialsRepository.save(personCredentials);

    }



    public GetPersonCredentials get(Long id){
        var personCredentials = personCredentialsRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        var response = modelMapper.map(personCredentials, GetPersonCredentials.class);
        return  response;
    }






}
