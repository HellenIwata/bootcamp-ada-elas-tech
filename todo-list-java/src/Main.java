import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        TaskManager manager = new TaskManager(scanner);

        int choice = -1;

        System.out.println("|=========== WELCOME TO THE TO-DO MANAGER ===========|");

        do{
            System.out.println("\n========== SHOW MENU TO-DO ==========");
            System.out.println("1. REGISTER NEW TASK");
            System.out.println("2. LIST PENDING TASKS");
            System.out.println("3. MARK TASK AS COMPLETED");
            System.out.println("4. SEARCH FOR TASK BY NAME");
            System.out.println("5. DELETE TASK");
            System.out.println("0. EXIT");
            System.out.println("\n======================================");
            System.out.print("Choose an option: ");

            try{
                String input = scanner.nextLine().trim();
                if (input.isEmpty()){
                    continue;
                }
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.err.printf(String.valueOf(e), "e: ");
            }

            switch (choice){
                case 1:
                    System.out.println("========= REGISTERING A NEW TASK =========");
                    manager.insertNewTask();
                    break;
                case 2:
                    System.out.println("========= LISTING TASKS PENDING =========");
                    manager.getAllByPending();
                    break;
                case 3:
                    System.out.println("========= MARKING TASK AS COMPLETED =========");
                    manager.markCompleted();
                    break;
                case 4:
                    System.out.println("========= SEARCHING FOR TASKS BY NAME =========");
                    manager.getByName();
                    break;
                case 5:
                    System.out.println("========= DELETING TASKS =========");
                    manager.deleteTask();
                    break;
                case 0:
                    System.out.println("========= EXITING... =========");
                    System.out.println("Thanks for use!");
                    break;
                default:
                    System.out.println("INVALID CHOICE. TRY AGAIN.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
