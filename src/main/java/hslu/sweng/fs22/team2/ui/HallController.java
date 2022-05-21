package hslu.sweng.fs22.team2.ui;

import hslu.sweng.fs22.team2.Hall;
import hslu.sweng.fs22.team2.Management;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Pattern;

public class HallController {
    public TextField hallNumberField;
    public TextField hallWidthField;
    public TextField hallHeightField;
    public TextField normalRowPriceField;
    public TextField lastRowPriceField;
    public Button addHallButton;

    ValidationSupport validationSupport;

    public void initialize() throws SQLException, ParseException {
        validationSupport = new ValidationSupport();

        validationSupport.registerValidator(hallNumberField, Validator.createRegexValidator("Must be a number!", Pattern.compile("\\d+"), Severity.ERROR));
        validationSupport.registerValidator(hallHeightField, Validator.createRegexValidator("Must be a number!", Pattern.compile("\\d+"), Severity.ERROR));
        validationSupport.registerValidator(hallWidthField, Validator.createRegexValidator("Must be a number!", Pattern.compile("\\d+"), Severity.ERROR));
        validationSupport.registerValidator(normalRowPriceField, Validator.createRegexValidator("Must be a number!", Pattern.compile("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"), Severity.ERROR));
        validationSupport.registerValidator(lastRowPriceField, Validator.createRegexValidator("Must be a number!", Pattern.compile("^(-?)(0|([1-9][0-9]*))(\\.[0-9]+)?$"), Severity.ERROR));
        addHallButton.disableProperty().bind(validationSupport.invalidProperty());
    }

    public void addHall() throws SQLException {
        Hall hall = new Hall(hallNumberField.getText(), Integer.parseInt(hallWidthField.getText()), Integer.parseInt(hallHeightField.getText()), Double.parseDouble(normalRowPriceField.getText()), Double.parseDouble(lastRowPriceField.getText()));
        int returnCode = hall.saveHall();
        if (!validationSupport.isInvalid() && returnCode == 1) {
            Stage toKill = (Stage) addHallButton.getScene().getWindow();
            toKill.hide();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            System.out.println("ERror code:" + returnCode + "hallid: " + hall.getHallNumber());
            alert.setHeaderText("There was an error while adding the movie!");
            alert.setTitle("Error");
            alert.setContentText(returnCode == -1 ? "A hall with that number already exists!" : "THERE IS NO HELP Hall ID Clash Error Code: " + returnCode + " HallID: " + hall.getHallNumber());
            alert.showAndWait();
        }
    }
}
