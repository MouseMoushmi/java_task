import Controller.TaskController;
import Enum.Status;
import Enum.Priority;
import Model.Task;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskController taskController = new TaskController();

        boolean running = true;
        while (running) {
            System.out.println("\nTask Manager Menu:");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Filter Tasks by Priority");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    addTask(scanner, taskController);
                    break;
                case 2:
                    editTask(scanner, taskController);
                    break;
                case 3:
                    deleteTask(scanner, taskController);
                    break;
                case 4:
                    taskController.viewAllTasks();
                    break;
                case 5:
                    filterTasksByPriority(scanner, taskController);
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static int getValidChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void addTask(Scanner scanner, TaskController taskController) {
        scanner.nextLine();
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Priority priority = getValidPriority(scanner);
        Status status = getValidStatus(scanner);
        Task newTask = new Task(taskController.getTasks().size() + 1, title, description, priority, status);
        taskController.addTask(newTask);
        System.out.println("Task added successfully. ID: " + newTask.getId());
    }

    private static void editTask(Scanner scanner, TaskController taskController) {
        System.out.print("Enter task ID to edit: ");
        int editId = getValidChoice(scanner);
        Task taskToEdit = taskController.getTaskById(editId);
        if (taskToEdit != null) {
            scanner.nextLine();
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();
            Priority newPriority = getValidPriority(scanner);
            Status newStatus = getValidStatus(scanner);
            taskController.editTask(editId, newTitle, newDescription, newPriority, newStatus);
            System.out.println("Task edited successfully.");
        } else {
            System.out.println("Task with ID " + editId + " not found.");
        }
    }

    private static void deleteTask(Scanner scanner, TaskController taskController) {
        System.out.print("Enter task ID to delete: ");
        int deleteId = getValidChoice(scanner);
        Task taskToDelete = taskController.getTaskById(deleteId);
        if (taskToDelete != null) {
            taskController.deleteTask(deleteId);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task with ID " + deleteId + " not found.");
        }
    }

    private static Priority getValidPriority(Scanner scanner) {
        while (true) {
            System.out.print("Enter task priority (HIGH/MEDIUM/LOW): ");
            try {
                String priorityStr = scanner.next().toUpperCase();
                return Priority.valueOf(priorityStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid priority. Please enter HIGH, MEDIUM, or LOW.");
            }
        }
    }

    private static Status getValidStatus(Scanner scanner) {
        while (true) {
            System.out.print("Enter task status (PENDING/IN_PROGRESS/COMPLETED): ");
            try {
                String statusStr = scanner.next().toUpperCase();
                return Status.valueOf(statusStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter PENDING, IN_PROGRESS, or COMPLETED.");
            }
        }
    }

    private static void filterTasksByPriority(Scanner scanner, TaskController taskController) {
        Priority filterPriority = getValidPriority(scanner);
        taskController.filterTasksByPriority(filterPriority);
    }
}
