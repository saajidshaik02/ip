package rizz.command;
import rizz.source.TaskList;


public class DeleteCommand extends Command {
    private final int[] index;

    public DeleteCommand(int... index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {

        return "Noted. I've removed these task:\n" + tasks.deleteTask(index)
                + "Now you have " + tasks.getLength() + " tasks in the list.\n" + tasks.toString();
    }
}

