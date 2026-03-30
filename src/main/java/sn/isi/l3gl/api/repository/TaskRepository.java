package sn.isi.l3gl.api.repository;

import sn.isi.l3gl.api.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    long countByStatus(String status);
}
