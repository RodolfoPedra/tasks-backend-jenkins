package br.ce.wcaquino.taskbackend.controller;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

public class TaskControllerTest {

  @Mock
  private TaskRepo repo;

  @InjectMocks
  private TaskController controller;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void naoDeveSalvarSemDescricaoNull() {
    Task task = new Task();
    task.setDueDate(LocalDate.now());
    task.setTask(null);
    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Fill the task description", e.getMessage());
    }
  }

    @Test
  public void naoDeveSalvarSemDescricaoEmpty() {
    Task task = new Task();
    task.setDueDate(LocalDate.now());
    task.setTask("");
    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Fill the task description", e.getMessage());
    }
  }

  @Test
  public void naoDeveSalvarSemData() {
    Task task = new Task();
    task.setTask("tarefa 1");
    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Fill the due date", e.getMessage());
    }
  }

  @Test
  public void naoDeveSalvarComDataPassada() {
    Task task = new Task();
    task.setTask("tarefa 1");
    task.setDueDate(LocalDate.of(2024, 10, 20));
    try {
      controller.save(task);
    } catch (ValidationException e) {
      Assert.assertEquals("Due date must not be in past", e.getMessage());
    }
  }

  @Test
  public void deveSalvarComSucesso() throws ValidationException {
    Task task = new Task();
    task.setTask("tarefa 1");
    task.setDueDate(LocalDate.of(2025, 10, 20));
    controller.save(task);
  }

  @Test
  public void findAll() {
    controller.findAll();
  }
}
