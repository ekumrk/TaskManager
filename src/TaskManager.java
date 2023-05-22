import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private int nextId = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();

    public int createTask (Task task) {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return (task.getId());
    }

    public void updateTask (Task task) {
        tasks.put(task.getId(), task);
    }

    public int createEpic (Epic epic) {
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        return (epic.getId());
    }

    public int createSubTask(SubTask subTask) {
        subTask.setId(nextId);
        nextId++;
        subTasks.put(subTask.getId(), subTask);
        return (subTask.getId());
    }

    public void updateSubTask (SubTask subTask) {
        subTasks.put(subTask.getId(), subTask);

        int epId = subTask.getEpicId();
        Epic epic = epics.get(epId);

        ArrayList<String> epicStatuses = new ArrayList<>();
        for (Integer subTaskId : epic.getSubtasksId()) {
            SubTask subTs = subTasks.get(subTaskId);
            if (subTs.status == "NEW") {
                epic.setStatus("NEW");
                continue;
            } else if (subTs.status == "IN PROGRESS") {
                epic.setStatus("IN PROGRESS");
                epicStatuses.add(subTs.status);
                continue;
            } else if (subTs.status == "DONE") {
                epicStatuses.add(subTs.status);
                continue;
            }
        }
        for (String stat : epicStatuses) {
            int counter = 0;
            int size = epicStatuses.size();
            if (stat == "DONE") {
                counter++;
            }
            if (counter == size) {
                epic.setStatus("DONE");
            }
            }
        }
}


