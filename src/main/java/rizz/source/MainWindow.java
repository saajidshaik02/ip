package rizz.source;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Rizz rizz;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image rizzImage = new Image(this.getClass().getResourceAsStream("/images/DaRizz.jpg"));

    @FXML
    public void initialize() {
        this.scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setRizz(Rizz r) {
        this.rizz = r;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        String response = rizz.getResponse(input);
        this.dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, rizzImage)
        );
        this.userInput.clear();
    }
}
