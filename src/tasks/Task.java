package tasks;

public class Task {
    protected int id;
    protected String title;
    protected String content;
    protected String status = "NEW";

    public Task (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }
}
