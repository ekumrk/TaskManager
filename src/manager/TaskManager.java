package manager;
import tasks.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TaskManager {

    private int nextId = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();

    public int createNewTask(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return (task.getId());
    }

    public void updateTask(Task task) {
        tasks.put(task.getId(),task);
    }

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> taskArrayList = new ArrayList<>(tasks.values());
        return taskArrayList;
    }

    public void deleteAllTasks() {
            tasks.clear();
    }

    public Task getTaskFromId(int id) {
        return tasks.get(id);
    }

    public void deleteTaskFromId(int id) {
        tasks.remove(id);
    }

    public int createNewEpic(Epic epic) {
        epic.setId(nextId);
        nextId++;
        epics.put(epic.getId(), epic);
        return (epic.getId());
    }

    public ArrayList<Epic> getEpicList() {
        ArrayList<Epic> epicArrayList = new ArrayList<>(epics.values());
        return epicArrayList;
    }

    public Epic getEpicFromId(int id) {
        return epics.get(id);
    }

    public ArrayList<Subtask> getSubtasksFromEpicId (int id) {
        ArrayList<Subtask> subtasksFromEpicId = new ArrayList<>();

        Epic epic = epics.get(id);
        for (Integer subtId : epic.getSubtasksId()) {
            Subtask subtask = subtasks.get(subtId);
            subtasksFromEpicId.add(subtask);
        }
        return subtasksFromEpicId;
        }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteEpicFromId(int id) {
        Epic epic = epics.remove(id);
        for (Integer subtaskId : epic.getSubtasksId()) {
            subtasks.remove(subtaskId);
        }
    }
     //Статус эпика обновляется автоматически при обновлении сабтасков

    public Integer createNewSubtask(Subtask subtask) {
        subtask.setId(nextId);
        nextId++;
        subtasks.put(subtask.getId(), subtask);

        Epic epic = epics.get(subtask.getEpicId());
        if (epic == null) {
            return null;
        }
        epic.getSubtasksId().add(subtask.getId());
        updateEpicStatus(epic);
        return (subtask.getId());
    }


    public ArrayList<Subtask> getSubtaskList() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>(subtasks.values());
        return subtaskArrayList;
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksId().clear();
            updateEpicStatus(epic);
        }
        subtasks.clear();
    }

    private void updateEpicStatus(Epic epic) {
        ArrayList<String> epicStatuses = new ArrayList<>();

        for (Integer subTaskId : epic.getSubtasksId()) {
            Subtask subTs = subtasks.get(subTaskId);
            if (subTs.getStatus().equals("NEW")) {
                epic.setStatus("NEW");
            } else if (subTs.getStatus().equals("IN PROGRESS")) {
                epic.setStatus("IN PROGRESS");
                epicStatuses.add(subTs.getStatus());
                break;
            } else if (subTs.getStatus().equals("DONE")) {
                epicStatuses.add(subTs.getStatus());
            }
        }

        for (String stat : epicStatuses) {
        int counter = 0;
        int size = epicStatuses.size();
            if (stat.equals("DONE")) {
                counter++;
            }
            if (counter == size) {
                epic.setStatus("DONE");
            }
        }
    }

    public Subtask getSubtaskFromId(int id) {
        return subtasks.get(id);
    }

    public void deleteSubtaskFromId(int id) {
        for (Epic epic : epics.values()) {
            if (epic.getSubtasksId().contains(id)) {
                epic.getSubtasksId().remove(id);
                updateEpicStatus(epic);
            }
            subtasks.remove(id);
        }
    }

    public void updateSubtask (Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        int epId = subtask.getEpicId();
        Epic epic = epics.get(epId);
        updateEpicStatus(epic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskManager that = (TaskManager) o;
        return nextId == that.nextId && Objects.equals(tasks, that.tasks) && Objects.equals(subtasks, that.subtasks) && Objects.equals(epics, that.epics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextId, tasks, subtasks, epics);
    }
}


