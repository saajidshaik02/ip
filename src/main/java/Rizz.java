import java.util.Scanner;
import java.util.ArrayList;

public class Rizz {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> arrList = new ArrayList<>();

    private void greet() {
        String str = "\t____________________________________________________________\n" +
                "\tHello! I'm Rizz\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void exit() {
        String str =  "\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________\n";
        System.out.println(str);
    }

    private void outputList() {
        if (arrList.isEmpty()) {
            System.out.println("\tNo items in the list.\n");
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < arrList.size(); i++) {
                strBuilder.append("\t").append(i + 1).append(". ").append(arrList.get(i)).append("\n");
            }
            String str = "\t____________________________________________________________\n" +
                    "\tHere are the tasks in your list:\n" + strBuilder +
                    "\t____________________________________________________________\n";
            System.out.println(str);
        }
    }

    private void markTask(int index) {
        if (index >= 1 && index <= arrList.size()) {
            Task task = arrList.get(index - 1);
            task.markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + task + "\n");
        } else {
            System.out.println("\tInvalid task number.\n");
        }
    }

    private void unmarkTask(int index) {
        if (index >= 1 && index <= arrList.size()) {
            Task task = arrList.get(index - 1);
            task.unmarkAsDone();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t\t" + task + "\n");
        } else {
            System.out.println("\tInvalid task number.\n");
        }
    }

    private void caseCheck() {
        while (true) {
            String textInput = this.input.nextLine();
            if (textInput.equals("bye")) {
                this.exit();
                break;

            } else if (textInput.equals("list")) {
                this.outputList();

            } else if (textInput.startsWith("mark ")) {
                int index = Integer.parseInt(textInput.split(" ")[1]);
                this.markTask(index);

            } else if (textInput.startsWith("unmark ")) {
                int index = Integer.parseInt(textInput.split(" ")[1]);
                this.unmarkTask(index);

            } else if (textInput.startsWith("todo ")) {
                String text = textInput.substring(5).trim();
                this.addToDo(text);

            } else if (textInput.startsWith("deadline ")) {
                String[] parts = textInput.substring(9).split("/by", 2);
                String description = parts[0].trim();
                String by = parts[1].trim();
                this.addDeadline(description, by);

            } else if (textInput.startsWith("event ")) {
                String[] parts = textInput.substring(6).split("/from|/to", 3);
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                this.addEvent(description, from, to);

            } else {
                System.out.println("\tInvalid Intput\n");
                //Remove Echo
            }
        }
    }

    private void addEvent(String text, String from, String to) {
        this.arrList.add(new Event(text, from, to));
        System.out.println("\tadded event: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    private void addToDo(String text) {
        this.arrList.add(new ToDo(text));
        System.out.println("\tadded todo: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    private void addDeadline(String text, String time) {
        this.arrList.add(new Deadline(text, time));
        System.out.println("\tadded deadline: " + text);
        System.out.printf("\tYou have %d tasks in the list.\n", this.arrList.size());
    }

    public static void main(String[] args) {
        Rizz rizz = new Rizz();
        rizz.greet();
        rizz.caseCheck();
    }
}
