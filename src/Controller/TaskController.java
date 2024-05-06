package Controller;

import Model.Task;
import Enum.Status;
import Enum.Priority;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private List<Task> tasks;

    public TaskController() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void editTask(int id, String title, String description, Priority priority, Status status) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(title);
                task.setDescription(description);
                task.setPriority(priority);
                task.setStatus(status);
                return;
            }
        }
        System.out.println("Task with ID " + id + " not found.");
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (Task task : tasks) {
                System.out.println(task);
                System.out.println("------------------------------");
            }
        }
    }

    public void filterTasksByPriority(Priority priority) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                System.out.println(task);
                System.out.println("------------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks with priority " + priority + " found.");
        }
    }
}
