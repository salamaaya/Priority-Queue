//Aya Salama
//I pledge my honor that I have abided by the Stevens Honor System.

import java.util.Iterator;

public class TaskList<E> {
    private ListQueue<E> all;
    private ListQueue<E> active;
    private ListQueue <E> completed;
    private int LOW_PRIORITY = Integer.MAX_VALUE;
    private int HIGH_PRIORITY = 1;

    /**
     * constructor
     */
    public TaskList(){
        all = new ListQueue<>();
        active = new ListQueue<>();
        completed = new ListQueue<>();
    }

    /**
     * adds the item into active and all queues with default priority LOW_PRIORITY
     * @param item
     * @return false if item is not null, true otherwise
     */
    public boolean createTask(E item){
        if(item == null){
            return false;
        }
        else{
            active.offer(item, LOW_PRIORITY);
            all.offer(item, LOW_PRIORITY);
            return true;
        }
    }

    /**
     * adds the item into active and all queues
     * @param item
     * @param priority
     * @return false if item is null, true otherwise
     */
    public boolean createTask(E item, int priority){
        if(item == null){
            return false;
        }
        else{
            active.offer(item, priority);
            all.offer(item, priority);
            return true;
        }
    }

    /**
     * prints the top three highest priority tasks
     */
    public void printTopThreeTasks(){
        Iterator<E> iter = active.iterator();
        int i = 1;
        while(i <= 3 && iter.hasNext()){
            System.out.println(i + ". " + iter.next());
            i++;
        }
    }

    /**
     *prints the tasks in active
     */
    public void showActiveTasks(){
        printTasks(active);
    }

    /**
     * prints the tasks in completed
     */
    public void showCompletedTasks(){
        printTasks(completed);
    }

    /**
     * prints the tasks in all
     */
    public void showAllTasks(){
        printTasks(all);
    }

    /**
     * uses the Iter class to print all the tasks in queue
     * @param queue
     */
    private void printTasks(ListQueue<E> queue) {
        Iterator<E> iter = queue.iterator();
        for (int i = 1; i <= queue.getSize(); i++) {
            System.out.println(i + ". " + iter.next());
        }
    }

    /**
     * removes the highest priority task from the queue
     * @return false if queue is empty, true otherwise
     */
    public boolean crossOffMostUrgent(){
         if(active.getSize() == 0){
             return false;
         }
         else{
             completed.addRear(active.poll());
             return true;
         }
    }

    /**
     * removes the task at the location identified by taskNumber
     * @param taskNumber
     * @return false if taskNumber is greater than queue size or negative, true otherwise
     */
    public boolean crossOffTask(int taskNumber){
        if(taskNumber > active.getSize() || taskNumber <= 0){
            return false;
        }
        else{
            ListQueue.Node<E> current = active.getFront();
            for (int i = 0; i < taskNumber - 1; i++){
                current = current.getNext();
            }
            active.remove(current);
            completed.addRear(current.getData());
        }
        return true;
    }

    /**
     * returns queue all
     * @return all
     */
    public ListQueue<E> getAll(){
        return all;
    }

    /**
     * returns queue completed
     * @return completed
     */
    public ListQueue<E> getCompleted(){
        return completed;
    }

    /**
     * returns queue active
     * @return active
     */
    public ListQueue<E> getActive(){
        return active;
    }
}