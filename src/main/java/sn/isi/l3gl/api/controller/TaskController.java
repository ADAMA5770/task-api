package sn.isi.l3gl.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.isi.l3gl.api.model.Task;
import sn.isi.l3gl.api.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> listTasks() {
        logger.info("GET /api/tasks");
        return ResponseEntity.ok(taskService.listTasks());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("POST /api/tasks - {}", task.getTitle());
        Task created = taskService.createTask(task.getTitle(), task.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(@PathVariable Long id,
                                             @RequestParam String status) {
        logger.info("PUT /api/tasks/{}/status - {}", id, status);
        return ResponseEntity.ok(taskService.updateStatus(id, status));
    }

    @GetMapping("/done/count")
    public ResponseEntity<Long> countCompleted() {
        logger.info("GET /api/tasks/done/count");
        return ResponseEntity.ok(taskService.countCompletedTasks());
    }
}
