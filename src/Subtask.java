public class Subtask extends Task {

    protected int epicId;
    public Subtask(String title, String content, String status, int epicId) {
        super(title, content, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
