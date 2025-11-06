import java.time.LocalDate;

public class Task {

    private final String name;
    private final String resumeDescription;
    private final String description;
    private final LocalDate deliveryDate;
    private boolean statusCompleted;

    // CONSTRUCTOR


    public Task(String name, String resumeDescription, String description, LocalDate deliveryDate) {
        this.name = name;
        this.resumeDescription = resumeDescription;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }

    // METHOD GETS FOR ACCESS
    public String getName() {
        return name;
    }

    public String getResumeDescription() {
        return resumeDescription;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isStatusCompleted() {
        return statusCompleted;
    }

    //METHOD SET


    public void setStatusCompleted(boolean completed ) {
        this.statusCompleted = completed;
    }

    @Override
    public String toString() {
        String status = this.statusCompleted ? "Completed" : "Pending";

        return String.format(
                """
                |--------------------------------------------------------------|\s
                |TAREFA: %s\s
                |RESUMO: %s\s
                |DATA DE ENTREGA: %s\s
                |STATUS: %s\s
                |DETALHES: %s\s
                |--------------------------------------------------------------|\s
                """,
                name, resumeDescription, deliveryDate, statusCompleted, description
        );
    }
}
