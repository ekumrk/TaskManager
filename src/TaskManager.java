import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class TaskManager {

    private int nextId = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();

    Epic epic;

    public int createNewTask(Task task) {
        task.setId(nextId);
        nextId++;
        tasks.put(task.getId(), task);
        return (task.getId());
    }

    public void updateTask(Task task, String status) {
        task.status = status;
        tasks.put(task.getId(),task);
    }

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> taskArrayList = new ArrayList<>(tasks.values());
        return taskArrayList;
    }

    public void deleteAllTasks() {
        if (!tasks.isEmpty()) {
            tasks.clear();
        } else {
            System.out.println("Список задач и так пуст.");
        }
    }

    public void getTaskFromId(int id) {
        if (!tasks.containsKey(id) || tasks.isEmpty()) {
            System.out.println("Задача с таким идентификатором не найдена или список задач пуст.");
        } else {
            for (Integer taskId : tasks.keySet()) {
                if (taskId == id) {
                    Task task = tasks.get(taskId);
                    System.out.println("Вы выбрали задание с ID №: " + task.id);
                    System.out.println(task.title);
                    System.out.println(task.content);
                    System.out.println("Статус задания: " + task.status);
                }
            }
        }
    }

    public void deleteTaskFromId(int id) {
        if (!tasks.containsKey(id) || tasks.isEmpty()) {
            System.out.println("Задача с таким ключом не найдена или список задач пуст.");
        } else {
            tasks.remove(id);
        }
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

    public void getEpicFromId(int id) {
        if (!epics.containsKey(id) || epics.isEmpty()) {
            System.out.println("Эпик с таким идентификатором не найден или список эпиков пуст.");
        } else {
            for (Integer epId : epics.keySet()) {
                if (epId == id) {
                    Epic epic = epics.get(epId);
                    System.out.println("Вы выбрали эпик с ID №: " + epic.id);
                    System.out.println(epic.title);
                    System.out.println(epic.content);
                    System.out.println("Статус задания: " + epic.status);
                }
            }
        }
    }

    public void getEpicsSubtasksFromId (int id) {
        if (!epics.containsKey(id) || epics.isEmpty()) {
            System.out.println("Эпик с таким идентификатором не найден или список эпиков пуст.");
        } else {
            for (Integer epId : epics.keySet()) {
                if (epId == id) {
                    Epic epic = epics.get(epId);
                    System.out.println("Вы выбрали эпик с ID №: " + epic.id);
                    if (epic.subtasksId.isEmpty()) {
                        System.out.println("В данном эпике пока нет подзадач.");
                    } else {
                        System.out.println("Данный эпик включает в себя следующие подзадачи:");
                        for (Integer subtIdEpic : epic.subtasksId) {
                            for (Subtask subts : subtasks.values()) {
                                if (subts.epicId == subtIdEpic) {
                                    System.out.println("Подзадача c ID : " + subts.id + ". Название: " + subts.title);
                                    System.out.println("Описание подзадачи: " +  subts.content);
                                    System.out.println("Статус подзадачи: " + subts.status);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void deleteAllEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список эпиков и так пуст.");
        } else {
            for (Integer epicId : epics.keySet()) {
                Epic epic = epics.get(epicId);
                for (Integer subtEpId : epic.subtasksId) {
                    for (Integer subtSubtId : subtasks.keySet()) {
                        if (subtEpId == subtSubtId) {
                            subtasks.remove(subtSubtId);
                        }
                    }
                }
                epics.remove(epicId);
            }
        }
    }

    public void deleteEpicFromId(int id) {
        Epic epic = epics.remove(id);
        for (Integer subtaskId : epic.subtasksId) {
            subtasks.remove(subtaskId);
        }
    }
     //Статус эпика обновляется автоматически при обновлении сабтасков

    public int createNewSubtask(Subtask subtask) {
        subtask.setId(nextId);
        nextId++;
        subtasks.put(subtask.getId(), subtask);
        for (Integer epId : epics.keySet()) {
            if (subtask.epicId == epId) {
                epic = epics.get(epId);
                epic.subtasksId.add(subtask.id);
            } else {
                System.out.println("Невозможно добавить подзадачу в указанный Эпик, так как не существует эпика с таким ID");
            }
        }
        return (subtask.getId());
    }


    public ArrayList<Subtask> getSubtaskList() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>(subtasks.values());
        return subtaskArrayList;
    }

    public void deleteAllSubtasks() {
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач и так пуст.");
        } else {
            for (Epic epic : epics.values()) {
                for (Integer subtaskId : subtasks.keySet()) {
                    if (epic.subtasksId.contains(subtaskId)) {
                        epic.subtasksId.remove(subtaskId);
                    }
                    ArrayList<String> epicStatuses = new ArrayList<>();
                    for (Integer subTaskId : epic.getSubtasksId()) {
                        Subtask subTs = subtasks.get(subTaskId);
                        if (subTs.status.equals("NEW")) {
                            epic.setStatus("NEW");
                        } else if (subTs.status.equals("IN PROGRESS")) {
                            epic.setStatus("IN PROGRESS");
                            epicStatuses.add(subTs.status);
                            break;
                        } else if (subTs.status.equals("DONE")) {
                            epicStatuses.add(subTs.status);
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
                subtasks.clear();
            }
        }
    }

    public void getSubtaskFromId(int id) {
        if (!subtasks.containsKey(id) || subtasks.isEmpty()) {
            System.out.println("Подзадача с таким идентификатором не найдена или список задач пуст.");
        } else {
            for (Integer subtaskId : subtasks.keySet()) {
                if (subtaskId == id) {
                    Subtask subtask = subtasks.get(subtaskId);
                    System.out.println("Вы выбрали задание с ID №: " + subtask.id);
                    System.out.println(subtask.title);
                    System.out.println(subtask.content);
                    System.out.println("Статус задания: " + subtask.status);
                    System.out.println("Принадлежит эпику с ID: " + subtask.epicId);
                }
            }
        }
    }

    public void deleteSubtaskFromID(int id) {
        if (subtasks.isEmpty()) {
            System.out.println("Список подзадач пуст.");
        } else {
            for (Epic epic : epics.values()) {
                if (epic.subtasksId.contains(id)) {
                    epic.subtasksId.remove(id);
                }
                ArrayList<String> epicStatuses = new ArrayList<>();
                for (Integer subTaskId : epic.getSubtasksId()) {
                    Subtask subTs = subtasks.get(subTaskId);
                    if (subTs.status.equals("NEW")) {
                        epic.setStatus("NEW");
                    } else if (subTs.status.equals("IN PROGRESS")) {
                        epic.setStatus("IN PROGRESS");
                        epicStatuses.add(subTs.status);
                        break;
                    } else if (subTs.status.equals("DONE")) {
                        epicStatuses.add(subTs.status);
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
                subtasks.remove(id);
            }
        }
    }

    public void updateSubtask (Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);

        int epId = subtask.getEpicId();
        Epic epic = epics.get(epId);

        ArrayList<String> epicStatuses = new ArrayList<>();
        for (Integer subTaskId : epic.getSubtasksId()) {
            Subtask subTs = subtasks.get(subTaskId);
            if (subTs.status.equals("NEW")) {
                epic.setStatus("NEW");
            } else if (subTs.status.equals("IN PROGRESS")) {
                epic.setStatus("IN PROGRESS");
                epicStatuses.add(subTs.status);
                break;
            } else if (subTs.status.equals("DONE")) {
                epicStatuses.add(subTs.status);
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


