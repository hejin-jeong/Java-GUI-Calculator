import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalc extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	Button btnEqual;
	Button btnAC;
	Button btnAdd;
	Button btnSub;
	ArrayList<Button> btnDigits;
	TextField txtF;

	// Stores the operands and operator
	String s1, s2, s3;

	/**
	 * Sets the stage for the application by initializing buttons, textfield and
	 * their layout.
	 */

	@Override
	public void start(Stage primaryStage) {

		// Create the textfield
		txtF = new TextField();

		// Create the buttons
		btnEqual = new Button();
		btnAC = new Button();
		btnAdd = new Button();
		btnSub = new Button();
		btnDigits = new ArrayList<Button>(10);

		// Initialize the operands and operators
		s1 = "";
		s2 = "";
		s3 = "";

		// Set labels for buttons.
		btnEqual.setText("=");
		btnAC.setText("AC");
		btnAdd.setText("+");
		btnSub.setText("-");


		// Add button objects to the array.
		for (int i = 0; i < 10; i++) {
			Button temp = new Button();
			temp.setText(String.valueOf(i));
			btnDigits.add(temp);
		}

		
		// Set the color of AC Button to yellow.
		btnAC.setStyle("-fx-background-color: yellow");

		// Right aligned the text.
		txtF.setAlignment(Pos.CENTER_RIGHT);
		
		// Disabled editing via typing.
		txtF.setEditable(false);
		
		// Set a width for text field.
		txtF.setPrefWidth(210);
		txtF.setMaxWidth(210);

		linkButtonsWithEvents();

		// Create a GridPane with spacing between cells
		GridPane pane = new GridPane();
		pane.setHgap(25);
		pane.setVgap(25);

		// Add textfield on 0th column, 0th row. It spans across 5 columns and 1 row
		pane.add(txtF, 0, 0, 5, 1);

		// Add btnAC on 4th column, 1st row.
		pane.add(btnAC, 4, 1);
		pane.add(btnAdd, 4, 2);
		pane.add(btnSub, 4, 3);
		pane.add(btnEqual, 4, 4);

		// Added digits 1-9 to the correct grid.
		for (int i = 0; i < btnDigits.size() - 1; i++) {
			Button digit = btnDigits.get(i + 1);
			pane.add(digit, i % 3, i / 3 + 1);
		}

		// Added digit 0.
		pane.add(btnDigits.get(0), 1, 4);

		// Add the layout pane to a scene
		Scene scene = new Scene(pane, 220, 250);

		// Finalize and show the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple Calculator");
		primaryStage.show();
	}

	/**
	 * Link each of the buttons with event handlers
	 */
	public void linkButtonsWithEvents() {

		btnAC.setOnAction(this::buttonClickReset);
		btnAdd.setOnAction(this::buttonClickAdd);
		btnSub.setOnAction(this::buttonClickSub);
		btnEqual.setOnAction(this::buttonClickEqual);

		
		// Set OnAction property for digit buttons.
		for (int i = 0; i < btnDigits.size(); i++) {
			btnDigits.get(i).setOnAction(this::buttonClickDigit);
		}

	}

	/**
	 * Reset the operands, operators and the textfield when AC is clicked
	 * 
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickReset(ActionEvent e) {

		// Clear the text field when the reset button is clicked.
		txtF.setText("");

		// Reset the operands and operator when the reset button is clicked.
		s3 = "";
		s2 = "";
		s1 = "";

	}

	/**
	 * Determine the operands when digits are clicked
	 * 
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickDigit(ActionEvent e) {

		// Concatenate digits to the correct operand when digits are clicked
		for (int i = 0; i < 10; i++) {
			if (e.getTarget() == btnDigits.get(i)) {

				// If there's no operator, then concatenate to s1.
				if (s2 == "") {
					s1 += btnDigits.get(i).getText();
					txtF.setText(s1); // Appear in the text field.
				}
				// If there's an operator, then concatenate to s3.
				else {
					s3 += btnDigits.get(i).getText();
					txtF.setText(s3); // Appear in the text field.
				}
			}
		}

	}

	/**
	 * Determine the operators when "+" is clicked
	 * 
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickAdd(ActionEvent e) {


		// Set s2 as a + operator.
		if (s1 != "") {
			s2 = "+";
		}

	}

	/**
	 * Determine the operator when "-" is clicked
	 * 
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickSub(ActionEvent e) {

		// Set s2 as a - operator.
		if (s1 != "") {
			s2 = "-";
		}


	}

	/**
	 * Determine the result in the textfield when "=" is clicked
	 * 
	 * @param the click event e
	 * @return void
	 */
	public void buttonClickEqual(ActionEvent e) {

		// Change the String into Integer
		if (s1 != "" && s2 != "" && s3 != "") {
			int s1Num = Integer.parseInt(s1);
			int s3Num = Integer.parseInt(s3);

			// Calculate the operation and show the result on the text field.
			if (s2 == "+") {
				txtF.setText(Integer.toString(s1Num + s3Num));
			} else {
				txtF.setText(Integer.toString(s1Num - s3Num));
			}
		}

		// Resetting the operands and operator after the result is calculated.
		s3 = "";
		s2 = "";
		s1 = "";
	}

}