package andProject.task_self.service;

import andProject.task_self.dto.categoryDTO.CreateCategoryDTO;
import andProject.task_self.dto.categoryDTO.GetResponseCategoryDTO;
import andProject.task_self.dto.categoryDTO.UpdateCategoryDTO;
import andProject.task_self.model.Category;
import andProject.task_self.repository.CategoryRepository;
import andProject.task_self.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper modelMapper;


    public GetResponseCategoryDTO createCategory(CreateCategoryDTO request){
        var category = modelMapper.map(request, Category.class);
        var person = personRepository.findById(request.getPerson()).orElseThrow(()->new EntityNotFoundException());
        category.setPerson(person);
        category.setActive(true);
        category.setCreation(LocalDateTime.now());
        categoryRepository.save(category);
        var response=modelMapper.map(category,GetResponseCategoryDTO.class);
        return response;
    }

    public GetResponseCategoryDTO getOneCategory(Long id){
        var category=categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        var response=modelMapper.map(category,GetResponseCategoryDTO.class);
        return response;
    }

    public void deleteCategory(Long id){
        var category=categoryRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        category.setActive(false);
        category.setDeadline(LocalDateTime.now());
        categoryRepository.save(category);
    }

    public GetResponseCategoryDTO updateCategory(UpdateCategoryDTO request){
        var category=categoryRepository.findById(request.getId()).orElseThrow(()->new EntityNotFoundException());

        if(request.getName()!=null){
            category.setName(request.getName());
        }
        if (request.getDescription()!=null) {
            category.setDescription(request.getDescription());
        }

        categoryRepository.save(category);
        var response = modelMapper.map(category, GetResponseCategoryDTO.class);
        return response;
    }








}
