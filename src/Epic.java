import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    protected ArrayList<Integer> subtasksId = new ArrayList<>();
    public Epic(String title, String content, String status, int id) {
        super(title, content, status, id);
    }

    public ArrayList<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
