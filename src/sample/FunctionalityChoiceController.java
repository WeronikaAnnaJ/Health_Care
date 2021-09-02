package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import  javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import  java.util.ResourceBundle;
import  java.io.File;
import javafx.scene.image.Image;



public class FunctionalityChoiceController implements Initializable {

    @FXML
    private  Button setAppointmentButton;

    @FXML
    private  Button cancelAppointmentButton;

    @FXML
    private ImageView graphicImageView;

    @FXML
    private Pane balckgroundPane;

    @FXML
    private Pane backgrouncPaneMain;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File graphicFile= new File(".idea/Images/NoteFunctionalityChoice.png");
        Image image= new Image(graphicFile.toURI().toString());
        graphicImageView.setImage(image);

        File imageFile= new File(".idea/Images/medical_bacground_1.jpg");
        Image imageImage= new Image(imageFile.toURI().toString());
        /// background.setImage(imageImage);

        BackgroundImage backgroundimage = new BackgroundImage(imageImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        //balckgroundPane.setBackground(background);
        backgrouncPaneMain.setBackground(background);
    }





    public void  setSetAppointmentButtonAction(ActionEvent event ) throws IOException {
        System.out.println("Umów wizytę");
        Parent functionalityChoiceParent= FXMLLoader.load(getClass().getResource("AppointmentChoice.fxml"));
        Scene funtionalityChoiceScene= new Scene(functionalityChoiceParent);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.hide();
        stage.setScene(funtionalityChoiceScene);
        stage.show();
    }

    @FXML
    void checkInformationButtonAction(ActionEvent event) throws IOException {
        Parent functionalityChoiceParent= FXMLLoader.load(getClass().getResource("Information.fxml"));
        Scene funtionalityChoiceScene= new Scene(functionalityChoiceParent);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.hide();
        stage.setScene(funtionalityChoiceScene);
        stage.show();
     //   funtionalityChoiceScene.getStylesheets().add("sample/CSS/tableStylesheet.css");
    }



    public void  setCancelAppointmentButtonAction(ActionEvent event ){
        System.out.println("Odwolaj wizytę");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Usuń wizytę - Nie należy do realizowanego przypadku użycia");
        alert.setContentText("Wróć i Umów wizytę");
        alert.showAndWait();
    }




}
