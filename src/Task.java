import java.util.HashMap;

public class Task {
    protected int id;
    protected String title;
    protected String content;
    protected String status;

    public Task (String title, String content, String status,int id) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
