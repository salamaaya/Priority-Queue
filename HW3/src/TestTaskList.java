//Aya Salama
//I pledge my honor that I have abided by the Stevens Honor System

import java.util.Scanner;

public class TestTaskList<E> {

    private TaskList<E> toDoList;
    private Scanner scan;

    public static void main(String[] args){
        TestTaskList<String> tl = new TestTaskList<>();
        tl.printMenu();
    }

    /**
     * prints the menu using helper function processMenuItem()
     */
    public void printMenu(){
        System.out.println("~~~ TO-DO List Program, created by Aya S. ~~~");
        scan = new Scanner(System.in);
        toDoList = new TaskList<>();
        int userInput = 0;
        boolean loop = true;
        while(loop){
            if(toDoList.getActive().getSize() == 0){
                System.out.println("==> Currently there are NO items in the To-Do List");
            }
            else{
                System.out.println("Current TO-DO List:\n" +
                        "-------------------");
                toDoList.showActiveTasks();
            }
            System.out.println("To add a new task without priority information, press 1.\n" +
                    "To add a new task with a priority information, press 2.\n" +
                    "To cross off the task at the top of the list, press 3.\n" +
                    "To cross off a certain task in the list, press 4.\n" +
                    "To see the top 3 highest priority tasks, press 5.\n" +
                    "To see the completed tasks, press 6.\n" +
                    "To see the all tasks that has been completed or still active, press 7.\n" +
                    "To quit the program, press 8.");
            String stringInput = scan.nextLine();
            try{
                userInput = Integer.parseInt(stringInput);
                loop = processMenuItem(userInput);
            }
            catch(NumberFormatException e){
                userInput = 0;
                loop = processMenuItem(userInput);
            }
        }
    }

    /**
     * calls appropriate functions from use input
     * @param menuItem
     * @return boolean
     */
    private boolean processMenuItem(int menuItem){
        if(menuItem == 1){
            System.out.println("Please enter the task description:");
            E task = (E) scan.nextLine();
            toDoList.createTask(task);
            System.out.println("Successfully entered the task to the to-do list!");
        }
        else if(menuItem == 2){
            System.out.println("Please enter the task description:");
            E task = (E) scan.nextLine();
            System.out.println("Please enter a priority number (1 indicates highest priority, increasing numbers show lower priority) :");
            String priority = scan.nextLine();
            toDoList.createTask(task, Integer.parseInt(priority));
            System.out.println("Successfully entered the task to the to-do list!");
        }
        else if(menuItem == 3){
            if(toDoList.crossOffMostUrgent()){
                ListQueue.Node<E> current = toDoList.getCompleted().getFront();
                for(int i = 0; i < toDoList.getCompleted().getSize() - 1; i++){
                    current = current.getNext();
                }
                E mostUrgent = current.getData();
                System.out.println("Task is completed and removed from the list: " + mostUrgent);
                System.out.println("Successfully removed the most urgent task/top of the list task!");
            }
            else{
                System.out.println("No tasks in line.");
            }
        }
        else if(menuItem == 4){
            System.out.println("Please enter the task number you would like to cross off the list :");
            String taskNum = scan.nextLine();
            if(toDoList.crossOffTask(Integer.parseInt(taskNum))){
                ListQueue.Node<E> current = toDoList.getCompleted().getFront();
                for(int i = 0; i < toDoList.getCompleted().getSize() - 1; i++){
                    current = current.getNext();
                }
                E crossItem = current.getData();
                System.out.println("Task number " + taskNum + " is removed: " + crossItem);
                System.out.println("Successfully removed the task number: " + taskNum);
            }
            else{
                System.out.println("Unsuccessful operation! Please try again!");
            }
        }
        else if(menuItem == 5){
            System.out.println("Top 3 highest priority tasks:\n" +
                    "------------------------------\n" +
                    "Printing Top Three Tasks...");
            toDoList.printTopThreeTasks();
        }
        else if(menuItem == 6){
            System.out.println("Completed Tasks:\n" +
                    "----------------");
            toDoList.showCompletedTasks();
        }
        else if(menuItem == 7){
            System.out.println("All of the Tasks - Both completed and active:\n" +
                    "---------------------------------------------");
            toDoList.showAllTasks();
        }
        else if(menuItem == 8){
            return false;
        }
        else{
            System.out.println("ERROR! Please enter a number between 1 and 8 (included).");
        }
        return true;
    }
}
