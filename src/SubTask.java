public class SubTask extends Task {

    protected int epicId;
    public SubTask(String title, String content, String status, int id) {
        super(title, content, status, id);
    }

    public int getEpicId() {
        return epicId;
    }
}
