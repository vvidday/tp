package modtrekt.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Task;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on ModuleList level 4</a>
     */

    public final Task person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCode} with the given {@code Module} and index to display.
     */
    public TaskCard(Task t, int displayedIndex) {
        super(FXML);
        this.person = t;
        id.setText(displayedIndex + ". ");
        name.setText(person.toString());
        email.setText("");
        if (t instanceof Deadline) {
            email.setText("Due by: " + ((Deadline) t).getDueDate().toString());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}