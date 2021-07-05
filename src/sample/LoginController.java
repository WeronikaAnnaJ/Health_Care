package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{



    @FXML
    private PasswordField PassowrdField;

    @FXML
    private TextField adresEmailTextField;

    @FXML
    private Button LogInButton;

    @FXML
    private Button CancelButton;

    @FXML
    private ImageView userImageView;

    @FXML
    private ImageView lockImageView;

    private Stage stage;
    private Scene scene;
    private Parent root;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File lockFile= new File(".idea/Images/Lock.Icon.png");
        Image lockImage= new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);


        File userFile= new File(".idea/Images/Login.Icon.png");
        Image userImage= new Image(userFile.toURI().toString());
        userImageView.setImage(userImage);
    }



    public void  setAppointmentButtonAction(ActionEvent event ) throws IOException {
        System.out.println("Zaloguj się");
        Parent functionalityChoiceParent= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene funtionalityChoiceScene= new Scene(functionalityChoiceParent);
        Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
        stage.hide();
        stage.setScene(funtionalityChoiceScene);
        stage.show();


    }







}
