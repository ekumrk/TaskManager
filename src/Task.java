public class Task {
    protected int id;
    protected String title;
    protected String content;
    protected String status;

    public Task (String title, String content, String status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
