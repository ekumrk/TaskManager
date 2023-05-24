import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subtasksId = new ArrayList<>();

    public Epic(String title, String content) {
        super(title, content);
    }

    public ArrayList<Integer> getSubtasksId() {
        System.out.println(subtasksId);
        return subtasksId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
