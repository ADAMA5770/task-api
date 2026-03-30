package sn.isi.l3gl.api.service;

import sn.isi.l3gl.api.model.Task;
import sn.isi.l3gl.api.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> listTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    public Task createTask(String title, String description) {
        logger.info("Creating task: {}", title);
        Task task = new Task(title, description, "TODO");
        return taskRepository.save(task);
    }

    public Task updateStatus(Long id, String status) {
        logger.info("Updating task {} to status {}", id, status);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public long countCompletedTasks() {
        logger.info("Counting completed tasks");
        return taskRepository.countByStatus("DONE");
    }
}
