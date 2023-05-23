public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        Task task;
        Epic epic;
        Subtask subtask;


        //Тестирование
        //1. Создайте 2 задачи, один эпик с 2 подзадачами, а другой эпик с 1 подзадачей.
        task = new Task("Почитать", "Гарри Поттера", "NEW");
        taskManager.createNewTask(task);
        task = new Task("Отдохнуть", "Полежать на диване", "NEW");
        taskManager.createNewTask(task);

        epic = new Epic("Посмотреть летом", "фильмы", "New");
        taskManager.createNewEpic(epic);
        subtask = new Subtask("Гарри Поттер", "все части","New",3);
        taskManager.createNewSubtask(subtask);
        subtask = new Subtask("Властелин колец", "все части", "NEW",3);
        taskManager.createNewSubtask(subtask);

        epic = new Epic("Посетить культурные места","в разных городах","NEW");
        taskManager.createNewEpic(epic);
        subtask = new Subtask("В Москве","Третьяковская галерея","NEW",6);
        taskManager.createNewSubtask(subtask);

        System.out.println("Тест 2:");
        //2. Распечатайте списки эпиков, задач и подзадач
        taskManager.getTaskList();
        taskManager.getEpicList();
        taskManager.getSubtaskList();

        System.out.println("Тест 3:");
        //3. Измените статусы созданных объектов, распечатайте.
        task = new Task("Почитать", "Гарри Поттера", "DONE");
        taskManager.updateTask(task);
        subtask = new Subtask("Властелин колец", "все части", "IN PROGRESS",3);
        taskManager.updateSubtask(subtask);

        taskManager.getTaskList();
        taskManager.getEpicList();
        taskManager.getSubtaskList();

        System.out.println("Тест 4:");
        //4. И, наконец, попробуйте удалить одну из задач и один из эпиков.
        taskManager.deleteEpicFromId(6);
        taskManager.deleteTaskFromId(2);

        taskManager.getTaskList();
        taskManager.getEpicList();
        taskManager.getSubtaskList();

    }
}
