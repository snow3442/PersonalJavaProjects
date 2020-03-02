/*
Home Page of the Travel Agency
Developped by Jianqiu Chen
Start Date: 2018/09/26
 */
package travelagency;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TravelAgency extends Application {

    FadeTransition ft;
    public DataBase database;
    final String carouselImagesUrl = "C:/PortfolioSnow/Snow3442Portfolio/TravelAgency/src/images";
    Map<Image, String> ImageToUrlmap = new HashMap<Image, String>();
    private List<Image> images = new ArrayList<Image>();
    String currentTxtStr = "";
    ResultSet tempRset;
    Text currentText;
    int j = 0;
    Button lbutton, rButton;
    ImageView imageView;

    @Override
    public void start(Stage primaryStage) throws SQLException {
        database = new DataBase();
        BorderPane mainPane = new BorderPane();
        /**
         * **********MenuBar**********
         */
        HBox menuBox = new HBox();
        menuBox.setSpacing(5);
        menuBox.getStyleClass().add("taMenuBox");
        Button btHome = new Button("Home");
        btHome.getStyleClass().add("menuButton");
        Button btHowWorks = new Button("How it Works");
        btHowWorks.getStyleClass().add("menuButton");
        Button btSpecials = new Button("Specials");
        btHowWorks.getStyleClass().add("menuButton");
        Button btDestination = new Button("Destination");
        btDestination.getStyleClass().add("menuButton");
        Button btAbout = new Button("About Us");
        btAbout.getStyleClass().add("menuButton");
        Button btHelp = new Button("Help");
        btHelp.getStyleClass().add("menuButton");
        menuBox.getChildren().addAll(btHome, btHowWorks, btDestination, btAbout, btHelp);
        menuBox.setAlignment(Pos.CENTER);
        mainPane.setTop(menuBox);
        //end of MenuBox

        //slider Buttons
        lbutton = new Button("<");
        lbutton.getStyleClass().add("carouselLeftRightButton");
        rButton = new Button(">");
        rButton.getStyleClass().add("carouselLeftRightButton");
        //end of slider Buttons
        //Content Pane
        GridPane contentPane = new GridPane();
        ScrollPane logScrollPane = new ScrollPane();
        logScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        logScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        logScrollPane.setContent(contentPane);
        contentPane.getStyleClass().add("bookpane");
        mainPane.setCenter(logScrollPane);
        contentPane.setAlignment(Pos.CENTER);
        //properties binding: make sure the content Pane spans the scrollPane
        contentPane.prefWidthProperty().bind(logScrollPane.widthProperty());
        contentPane.prefHeightProperty().bind(logScrollPane.heightProperty());
        loadSlider(contentPane);
        //End of Content Pane

        //Show the scene
        Scene scene = new Scene(mainPane, 800, 600);
        scene.getStylesheets().add("css/main.css");
        primaryStage.setTitle("Welcome to QiuQiu Travel Agency");
        primaryStage.setScene(scene);
        primaryStage.show();

        //event listeners
        btHome.setOnMousePressed(e -> {
            try {
                contentPane.getChildren().clear();
                loadSlider(contentPane);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
    //end of start method

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * loadFiles loads all files to the Image list
     *
     * @param contentPane
     */
    protected void loadSlider(GridPane contentPane) throws SQLException {
        File path = new File(carouselImagesUrl);
        File[] carouselfiles = path.listFiles();
        for (int i = 0; i < carouselfiles.length; i++) {
            if (carouselfiles[i].isFile()) {
                try {
                    File file = new File(String.valueOf(carouselfiles[i]));
                    LocatedImage currentImage = new LocatedImage(file.toURI().toURL().toExternalForm());
                    ImageToUrlmap.put(currentImage, file.toURI().toURL().toExternalForm());
                    images.add(currentImage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        //create fadetransition effects for carousel

        //end of fadetransition
        StackPane currentStackPane = new StackPane();
        currentStackPane.getStyleClass().add("carousel_image_wrapper");
        imageView = new ImageView(images.get(j));
        String currentUrl = ImageToUrlmap.get(images.get(j));
        ResultSet tempRset = database.getResultByImageUrl(currentUrl);
        if (tempRset.next()) {
            currentTxtStr = tempRset.getString(9);
        }
        currentText = new Text(currentTxtStr);
        currentText.getStyleClass().add("carouselTextDark");
        currentStackPane.getChildren().addAll(imageView, currentText);
        StackPane.setAlignment(currentText, Pos.TOP_CENTER);
        StackPane.setMargin(currentText, new Insets(20, 0, 10, 0));
        imageView.setOpacity(0.35);
        imageView.fitWidthProperty().bind(contentPane.widthProperty().multiply(0.70));
        imageView.fitHeightProperty().bind(contentPane.heightProperty().multiply(0.8));
        contentPane.add(lbutton, 0, 0);
        contentPane.add(currentStackPane, 1, 0);
        contentPane.add(rButton, 2, 0);
        imageView.setCursor(Cursor.CLOSED_HAND);
        imageView.setOnMouseEntered(e -> {
            ft = new FadeTransition(Duration.millis(1600), imageView);
            ft.setFromValue(0.35);
            ft.setToValue(0.90);
            ft.setAutoReverse(false);
            ft.play();
            currentText.setText("");
        });

        imageView.setOnMouseExited(e -> {
            ft = new FadeTransition(Duration.millis(1600), imageView);
            ft.setFromValue(0.90);
            ft.setToValue(0.35);
            ft.setAutoReverse(false);
            ft.play();
            currentText.setText(currentTxtStr);
            currentText.getStyleClass().add("carouselTextDark");
        });
        //load booking information
        imageView.setOnMousePressed(e -> {
            try {
                contentPane.getChildren().clear();
                //loading the booking info
                contentPane.getStyleClass().add("bookpane");
                contentPane.getChildren().add(loadBookingPane(contentPane));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        rButton.setOnAction(e -> {
            try {
                j = j + 1;
                if (j == images.size()) {
                    j = 0;
                }
                imageView.setImage(images.get(j));
                carouselTextSettings();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        lbutton.setOnAction(e -> {
            try {
                j = j - 1;
                if (j == 0 || j > images.size() + 1 || j == -1) {
                    j = images.size() - 1;
                }
                imageView.setImage(images.get(j));
                carouselTextSettings();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void carouselTextSettings() throws SQLException {
        String currentUrl = ImageToUrlmap.get(images.get(j));
        ResultSet tempRset = database.getResultByImageUrl(currentUrl);
        if (tempRset.next()) {
            currentTxtStr = tempRset.getString(9);
            currentText.setText(currentTxtStr);
        }
    }

    /**
     * loads the contentPane based on what user has clicked on
     *
     * @return
     * @throws SQLException
     */
    private VBox loadBookingPane(GridPane contentPane) throws SQLException {
        //create booking Pane     
        VBox bookPane = new VBox(10);
        ArrayList<HBox> arrListHBox = new ArrayList<HBox>();
        ArrayList<Text> arrListTexts = new ArrayList<Text>();
        ArrayList<Text> arrListFixedTexts = new ArrayList<Text>();
        //retrieve from database
        ResultSet tempRset = database.getResultByImageUrl(ImageToUrlmap.get(images.get(j)));
        if (tempRset.next()) {
            String packName = tempRset.getString(2);
            String destination = tempRset.getString(3);
            String price = tempRset.getString(5) + "$";
            String description = tempRset.getString(6);
            String duration = tempRset.getString(7);
            String[] touristsArray = tempRset.getString(8).split(",");
            String carouselLine = tempRset.getString(9);
            //package name
            HBox hbPackName = new HBox(25);
            arrListHBox.add(hbPackName);
            Text txt1 = new Text("Package: ");
            arrListFixedTexts.add(txt1);
            Text txtPackName = new Text(packName);
            arrListTexts.add(txtPackName);
            hbPackName.getChildren().addAll(txt1, txtPackName);
            bookPane.getChildren().add(hbPackName);
            //end of package

            // price 
            HBox hbPrice = new HBox(25);
            arrListHBox.add(hbPackName);
            Text txt2 = new Text("Price: ");
            arrListFixedTexts.add(txt2);
            Text txtPrice = new Text(price);
            arrListTexts.add(txtPrice);
            hbPrice.getChildren().addAll(txt2, txtPrice);
            bookPane.getChildren().add(hbPrice);
            //end of price

            //destination
            HBox hbDestination = new HBox(25);
            arrListHBox.add(hbPackName);
            Text txt3 = new Text("Destination: ");
            arrListFixedTexts.add(txt3);
            Text txtDestination = new Text(destination);
            arrListTexts.add(txtDestination);
            hbDestination.getChildren().addAll(txt3, txtDestination);
            bookPane.getChildren().add(hbDestination);
            //end of destination
            HBox hbDescription = new HBox(25);
            arrListHBox.add(hbPackName);
            Text txt4 = new Text("Description: ");
            arrListFixedTexts.add(txt4);
            Text txtDescription = new Text(description);
            arrListTexts.add(txtDescription);
            hbDescription.getChildren().addAll(txt4, txtDescription);
            bookPane.getChildren().add(hbDescription);
            //duration
            HBox hbDuration = new HBox(25);
            arrListHBox.add(hbDuration);
            Text txt5 = new Text("Duration: ");
            arrListFixedTexts.add(txt5);
            Text txtDuration = new Text(duration);
            arrListTexts.add(txtDuration);
            hbDuration.getChildren().addAll(txt5, txtDuration);
            bookPane.getChildren().add(hbDuration);
            //end of duration

            //Tourist Guide
            HBox hbTourists = new HBox(25);
            arrListHBox.add(hbTourists);
            Text txt6 = new Text("Tourist Guides: ");
            arrListFixedTexts.add(txt6);
            String guides = "";
            for (String s : touristsArray) {
                guides = guides + s + " ";
            }
            Text txtGuides = new Text(guides);
            arrListTexts.add(txtGuides);
            hbTourists.getChildren().addAll(txt6, txtGuides);
            bookPane.getChildren().add(hbTourists);
            //end of Tourist Guide

            //book button and return to home button 
            HBox hbBtBox = new HBox(120);
            hbBtBox.setPadding(new Insets(30, 0, 0, 0));
            Button btBook = new Button("Book Trip");
            btBook.getStyleClass().add("btBookandReturn");
            Button btReturn = new Button("Go Back");
            btReturn.getStyleClass().add("btBookandReturn");
            hbBtBox.getChildren().addAll(btBook, btReturn);
            bookPane.getChildren().add(hbBtBox);
            //end of book and return buttons
            btReturn.setOnMousePressed(e -> {
                try {
                    contentPane.getChildren().clear();
                    loadSlider(contentPane);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            btBook.setOnMousePressed(e -> {
                contentPane.getChildren().clear();
                BookForm bookForm = new BookForm();
                bookForm.getTxtPackName().setText(packName);
                contentPane.getChildren().add(bookForm);

            });
            //Styling the HBoxes and Texts
            for (Text txt : arrListTexts) {
                txt.setWrappingWidth(400);
                txt.getStyleClass().add("descriptionText");
            }

            for (Text txt : arrListFixedTexts) {
                txt.getStyleClass().add("descriptionText");
            }
        }
        return bookPane;
    }

}
