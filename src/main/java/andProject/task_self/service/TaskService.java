package andProject.task_self.service;

import andProject.task_self.dto.taskDTO.CreateTaskDTO;
import andProject.task_self.dto.taskDTO.GetTaskDTO;
import andProject.task_self.dto.taskDTO.UpdateTaskDTO;
import andProject.task_self.model.Task;
import andProject.task_self.repository.CategoryRepository;
import andProject.task_self.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    private final TypeMap<Task, GetTaskDTO> taskToGetTaskDTOMap;

    @Autowired
    public TaskService(ModelMapper modelMapper) {
        this.taskToGetTaskDTOMap = modelMapper.createTypeMap(Task.class, GetTaskDTO.class);
        this.taskToGetTaskDTOMap.addMappings(
                mapper -> mapper.map(src -> src.getCategory().getId(), GetTaskDTO::setCategory)
        );
    }




    public GetTaskDTO createTask(CreateTaskDTO request){


        var task = modelMapper.map(request, Task.class);
        var category =categoryRepository.findById(request.getCategory()).orElseThrow(()->new EntityNotFoundException());

        task.setActive(true);
        task.setCategory(category);
        task.setCreation(LocalDateTime.now());
        task.setFavorite(false);
        taskRepository.save(task);

        var response = modelMapper.map(task,GetTaskDTO.class);

        return response;
    }

    public GetTaskDTO getTask(Long id){
        var task =taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        var response = modelMapper.map(task,GetTaskDTO.class);
        return  response;
    }

    public GetTaskDTO updateTaskDto(UpdateTaskDTO request){
        var task =taskRepository.findById(request.getId()).orElseThrow(()->new EntityNotFoundException());

        if(request.getName()!=null){
            task.setName(request.getName());
        }
        if(request.getDescription()!=null){
            task.setDescription(request.getDescription());
        }
        if(request.getCategory()!=null){
            var newCategory = categoryRepository.findById(request.getCategory()).orElseThrow(()->new EntityNotFoundException());
            task.setCategory(newCategory);
        }

        taskRepository.save(task);
        var response = modelMapper.map(task, GetTaskDTO.class);

        return response;
    }

    public void deleteTask(Long id){
        var task =taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        task.setActive(false);
        task.setDeadline(LocalDateTime.now());
        taskRepository.save(task);
    }

    public void favoriTask(Long id){
        var task =taskRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        task.setFavorite(true);
        taskRepository.save(task);
    }

}
