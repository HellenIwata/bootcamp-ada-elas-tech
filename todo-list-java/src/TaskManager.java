import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
        List<Task> listTask = new ArrayList<>();

    private final Scanner sc;

    // CONSTRUCTOR
    public TaskManager(Scanner sc) {
        this.sc = sc;
    }

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private void verifyEmpty(){
        if (listTask.isEmpty()){
            System.out.println("\tNO TASK REGISTERED");
        }
    }

    private void getListAllIndex() {
        verifyEmpty();
        System.out.println("------------------------- CURRENT TASK -----------------------");
        for (int i = 0; i < listTask.size(); i++){
            Task t = listTask.get(i);
            String status = t.isStatusCompleted() ? "COMPLETED" : "PENDING";
            System.out.printf("%d. [%s] %s\n\tRESUME: %s\n\tDELIVERY DATE: %s",(i+1), status, t.getName(), t.getResumeDescription(), t.getDeliveryDate().format(DATE_FORMATTER));
        }
        System.out.println("------------------------------------------------------------------");
    }

    private int choiceIndexAllTask() {
        int choice = -1;
        boolean validChoice = false;

        while (!validChoice) {
            System.out.println("Enter number of the task to be completed: ");
            try {
                String input = sc.nextLine().trim();

                if (input.isEmpty()){
                    System.err.println("INVALID ENTER");
                    continue;
                }

                choice = Integer.parseInt(input);

                if (choice == 0) {
                    System.out.println("Operation canceled");
                    return -1;
                }

                if (choice > 0 && choice <= listTask.size()) {
                    validChoice = true;
                } else {
                    System.err.println("INVALID NUMBER.\nChoose between 1 and "+ listTask.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.err.println("INVALID ENTER.");
            }
        }
        return choice - 1;
    }


    public void insertNewTask() {
        String more = "y";

        do {
            System.out.println("Enter a name for the task: ");
            String name = sc.nextLine();

            System.out.println("Enter a simplified description for the task: ");
            String resumeDescription = sc.nextLine();

            System.out.println("Enter complete descriptions (Details): ");
            String description = sc.nextLine();

            LocalDate deliveryDate = null;
            boolean validDate = false;

            while (!validDate) {
                System.out.println("Enter a delivery date (format: 2025/10/25): ");
                String dataString = sc.nextLine();

                try {
                    deliveryDate = LocalDate.parse(dataString, DATE_FORMATTER);
                    if (deliveryDate.isBefore(LocalDate.now())) {
                        System.err.println("\n\tINVALID DATE:");
                    } else {
                        validDate = true;
                    }
                } catch (DateTimeParseException e) {
                    System.err.println("\n\tINVALID FOMARTTER");
                }
            }

            Task task = new Task(name, resumeDescription, description, deliveryDate);
            listTask.add(task);

            System.out.println("Registration successful!");

            boolean validResponse = false;
            while (!validResponse) {
                System.out.println("Enter for insert a new task (y/n): ");
                more = sc.nextLine().trim().toLowerCase();

                if (more.equals("y") || more.equals("n")) {
                    validResponse = true;
                } else {
                    System.err.println("\n\tINVALID RESPONSE");
                }
            }
        } while (more.equalsIgnoreCase("y"));

        System.out.println("\n==== REGISTRATION CLOSED ====\n");
        if (listTask.isEmpty()) {
            System.out.println("\tNO TASK REGISTERED");
        }
    }

    public void getAllByPending() {
        verifyEmpty();

        List<Task> pendingTask = listTask.stream()
                .filter(task -> !task.isStatusCompleted())
                .toList();

        if (pendingTask.isEmpty()){
            System.out.println("\n NO PENDING TASK");
            return;
        }

        System.out.printf("\n|-------- TOTAL PENDING TASK --------|\n|\t %d PENDING: ",
                pendingTask.size());

        for (int i = 0; i < pendingTask.size(); i++){
            Task t = pendingTask.get(i);
            System.out.printf("%d. [PENDING] %s (Deliver: %s)\n",
                    (i+1), t.getName(),
                    t.getDeliveryDate()
                            .format(DATE_FORMATTER));
        }
    }

    public void getByName() {
        verifyEmpty();

        System.out.println("Enter a name for the task: ");

        String nameQuery = sc.nextLine();

        List<Task> foundTasks = listTask.stream()
                .filter(task -> task.getName().equalsIgnoreCase(nameQuery))
                .toList();

        if (foundTasks.isEmpty()){
            System.err.println("NO TASK FOUND WITH THE NAME: "+nameQuery);
            return;
        }
        System.out.printf("\n|-------- TOTAL TASK FOUND WITH NAME '%s' --------|\n|\t %d FOUND: ",
                nameQuery,
                foundTasks.size());
        for (Task tName : foundTasks){
            System.out.println(tName);
        }
        System.out.println("---------------------------------------------------------------------");
    }

    public void markCompleted() {

        verifyEmpty();

        System.out.println("------- TASK TO MARK AS COMPLETED-------");
        getListAllIndex();

        int indexToMark = choiceIndexAllTask();

        if (indexToMark == -1){
            return;
        }

        Task selectedTask = listTask.get(indexToMark);

        if (!selectedTask.isStatusCompleted()) {
            selectedTask.setStatusCompleted(true);
            System.out.println("Task \"" + selectedTask.getName() + "\" successful COMPLETED");
        }

        System.out.println("-----------------------------------------");

    }

    public void deleteTask() {
        verifyEmpty();
        if (listTask.isEmpty()) return;

        System.out.println("\n---------------- TASK TO DELETE ----------------");
        getListAllIndex();

        int indexToDelete = choiceIndexAllTask();

        if (indexToDelete == -1){
            return;
        }

        Task deletedTask = listTask.remove(indexToDelete);

        System.out.println("TASK \""+deletedTask.getName() + "\" successful DELETED!");
    }
}

