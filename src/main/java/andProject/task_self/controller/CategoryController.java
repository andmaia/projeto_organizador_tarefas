package andProject.task_self.controller;

import andProject.task_self.dto.categoryDTO.CreateCategoryDTO;
import andProject.task_self.dto.categoryDTO.GetResponseCategoryDTO;
import andProject.task_self.dto.categoryDTO.UpdateCategoryDTO;
import andProject.task_self.dto.personDTO.GetResponsePersonDTO;
import andProject.task_self.service.CategoryService;
import andProject.task_self.service.PersonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.swing.text.TabExpander;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping
    @Transactional
    public ResponseEntity<GetResponseCategoryDTO> create (@RequestBody @Valid CreateCategoryDTO request, UriComponentsBuilder builder){
        var category =categoryService.createCategory(request);
        var uri =builder.path("/api/category/{id}").buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).body(category);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponseCategoryDTO> getOneCategory(@PathVariable @NotNull Long id){
        var category=categoryService.getOneCategory(id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCategory(@PathVariable @NotNull Long id){
        categoryService.deleteCategory(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GetResponseCategoryDTO> updateCategory(@RequestBody @Valid UpdateCategoryDTO request){
        var category = categoryService.updateCategory(request);
        return  ResponseEntity.ok(category);
    }

}
