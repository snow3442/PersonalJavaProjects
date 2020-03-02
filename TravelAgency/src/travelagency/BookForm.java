package travelagency;

/*Client booking form with input validations*/
import java.util.Arrays;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BookForm extends GridPane {

    public final String[] _months = {"January", "February", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December"};
    private Text txtPackName = new Text("");
    private TextField tfFirstName = new TextField(" ");
    private TextField tfLastName = new TextField(" ");
    //DO I use a date picker for birthday?
    private TextField tfAddressLine1 = new TextField(" ");
    private TextField tfAddressLine2 = new TextField(" ");
    private TextField tfPhone = new TextField(" ");
    private TextField tfPassPort = new TextField(" ");
    private Button btConfirm = new Button("Confirm Payment");
    private Button btCancel = new Button("Cancel Booking");

    //Radio button group to be added for start date
    public BookForm() {
        setVgap(20);
        setHgap(25);
        //radioButtonPane for trip selection
        VBox hbRBdepart = new VBox(6);
        RadioButton rb1 = new RadioButton("Spring March 25th");
        RadioButton rb2 = new RadioButton("Summer June 13th");
        RadioButton rb3 = new RadioButton("Fall September 22nd");
        ToggleGroup group = new ToggleGroup();
        rb1.setToggleGroup(group);
        rb2.setToggleGroup(group);
        rb3.setToggleGroup(group);
        hbRBdepart.getChildren().addAll(rb1, rb2, rb3);
        // comboBox for birthday picking
        HBox hbBirthDayPicker = new HBox(2.5);
        Text txtYear = new Text("Year");
        ComboBox<Integer> cbYear = new ComboBox<>();
        for (int i = 1900; i < 2018; i++) {
            cbYear.getItems().add(i);
        }
        Text txtMonth = new Text("Month");
        ComboBox<String> cbMonth = new ComboBox<>();
        cbMonth.getItems().addAll(Arrays.asList(_months));
        Text txtDay = new Text("Day");
        ComboBox<Integer> cbDay = new ComboBox<>();
        for (int j = 1; j < 31; j++) {
            cbDay.getItems().add(j);
        }
        hbBirthDayPicker.getChildren().addAll(txtYear, cbYear, txtMonth, cbMonth, txtDay, cbDay);
        //Fixed texts
        Text txtFirstName = new Text("First Name: ");
        Text txtLastName = new Text("Last Name: ");
        Text txtPhone = new Text("Phone Number: ");
        Text txtPassPort = new Text("PassPort Number: ");
        Text txtAddressLine1 = new Text("Address Line 1: ");
        Text txtAddressLine2 = new Text("Address Line 2: ");
        Text txtBirthDay = new Text("Your Birthday: ");
        Text txtDepartingDate = new Text("Select your trip: ");
        //we wrapped the buttons inside two panes to
        //set good padding for confirm and cancel buttons
        btConfirm.getStyleClass().add("btConfirmAndCancel");
        btCancel.getStyleClass().add("btConfirmAndCancel");
        //add All UI to the pane
        this.add(new Text("Selected Trip Name: "),0,0);
        this.add(txtPackName,1,0);
        this.add(txtFirstName, 0, 1);
        this.add(tfFirstName, 1, 1);
        this.add(txtLastName, 0, 2);
        this.add(tfLastName, 1, 2);
        this.add(txtPhone, 0, 3);
        this.add(tfPhone, 1, 3);
        this.add(txtAddressLine1, 0, 4);
        this.add(tfAddressLine1, 1, 4);
        this.add(txtAddressLine2, 0, 5);
        this.add(tfAddressLine2, 1, 5);
        this.add(txtBirthDay, 0, 6);
        this.add(hbBirthDayPicker, 1, 6);
        this.add(txtDepartingDate, 0, 7);
        this.add(hbRBdepart, 1, 7);
        this.add(btConfirm, 0, 8);
        this.add(btCancel, 1, 8);
        //set styles for buttons

    }

    public TextField getTfFirstName() {
        return tfFirstName;
    }

    public void setTfFirstName(TextField tfFirstName) {
        this.tfFirstName = tfFirstName;
    }

    public TextField getTfLastName() {
        return tfLastName;
    }

    public void setTfLastName(TextField tfLastName) {
        this.tfLastName = tfLastName;
    }

    public TextField getTfAddressLine1() {
        return tfAddressLine1;
    }

    public void setTfAddressLine1(TextField tfAddressLine1) {
        this.tfAddressLine1 = tfAddressLine1;
    }

    public TextField getTfAddressLine2() {
        return tfAddressLine2;
    }

    public void setTfAddressLine2(TextField tfAddressLine2) {
        this.tfAddressLine2 = tfAddressLine2;
    }

    public TextField getTfPhone() {
        return tfPhone;
    }

    public void setTfPhone(TextField tfPhone) {
        this.tfPhone = tfPhone;
    }

    public TextField getTfPassPort() {
        return tfPassPort;
    }

    public void setTfPassPort(TextField tfPassPort) {
        this.tfPassPort = tfPassPort;
    }

    public Button getBtConfirm() {
        return btConfirm;
    }

    public void setBtConfirm(Button btConfirm) {
        this.btConfirm = btConfirm;
    }

    public Button getBtCancel() {
        return btCancel;
    }

    public void setBtCancel(Button btCancel) {
        this.btCancel = btCancel;
    }

    public Text getTxtPackName() {
        return txtPackName;
    }

    public void setTxtPackName(Text txtPackName) {
        this.txtPackName = txtPackName;
    }
    
    

}
