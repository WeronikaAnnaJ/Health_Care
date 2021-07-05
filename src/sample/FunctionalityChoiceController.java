package sample;

import javafx.application.Application;
import javafx.fxml.FXML;

import java.awt.*;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import  javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import  java.util.ResourceBundle;
import  java.io.File;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;



public class FunctionalityChoiceController implements Initializable {

@FXML
private  Button setAppointmentButton;
@FXML
private  Button cancelAppointmentButton;
@FXML
private ImageView graphicImageView;

    private Stage stage;
    private Scene scene;
    private Parent root;




    @Override
   public void initialize(URL url, ResourceBundle resourceBundle){
         File graphicFile= new File(".idea/Images/GRAPHIC.jpg");
         Image image= new Image(graphicFile.toURI().toString());
         graphicImageView.setImage(image);

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



    public void  setCancelAppointmentButtonAction(ActionEvent event ){

        System.out.println("Odwolaj wizytę");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Usuń wizytę - Nie należy do realizowanego przypadku użycia");
        alert.setContentText("Wróć i Umów wizytę");

        alert.showAndWait();

    }


    public Button getSetAppointmentButton() {
        return setAppointmentButton;
    }


}
