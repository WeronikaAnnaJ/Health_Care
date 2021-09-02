package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class AppointmentChoiceController implements Initializable {

    private Map<String, MedicalSpecialization> specializationDictionary = Specialization.getSpecializationsDicrionary();
    private Patient patientExample = Patient.getPatientExample();
    private MedicalFacility medicalFacilityExample = MedicalFacility.getMedicalFacilityExample();
    private List<RowForComboBox> rows;
    private List<String> rowsString;

    @FXML
    private CheckBox checkBoxMedicalFacilitie;

    @FXML
    void wybierzPlacowke(ActionEvent event) {

    }


    @FXML
    private ComboBox<String> AppointmentDateComboBox;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<String> specializationComboBox;

    @FXML
    private Pane backgroundPane;

    @FXML
    private Label noRefferalLabel;

    @FXML
    private Label noAppointmentsLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> specializationsList = new ArrayList<>(specializationDictionary.keySet());
        ObservableList<String> list = FXCollections.observableArrayList(specializationsList);
        specializationComboBox.setItems(list);
        AppointmentDateComboBox.getItems().add("Brak danych");
        File imageFile = new File(".idea/Images/Background_Login.jpg");
        javafx.scene.image.Image imageImage = new Image(imageFile.toURI().toString());
        BackgroundImage backgroundimage = new BackgroundImage(imageImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        backgroundPane.setBackground(background);
    }


    @FXML
    public void specializationChoice() {
        noRefferalLabel.setVisible(false);
        noAppointmentsLabel.setVisible(false);
        MedicalSpecialization medicalSpecialist = specializationDictionary.get(specializationComboBox.getValue());
        System.out.println(medicalSpecialist);

        if (medicalSpecialist == MedicalSpecialization.FamilyDoctor) {

            try {
                //    List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalFacilityExample,medicalSpecialist);
                List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalSpecialist);

                rows = rowsForComboBox;
                if (rowsForComboBox.isEmpty()) {
                    AppointmentDateComboBox.getItems().clear();
                    AppointmentDateComboBox.getItems().add("Brak terminów");
                } else {
                    List<String> rowsStringForComboBox = new ArrayList<>();
                    rowsString = rowsStringForComboBox;
                    for (RowForComboBox row : rowsForComboBox) {
                        String doctorName = row.getDoctor().getName() + " " + row.getDoctor().getLastName();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm");
                        String dateAndTime = row.getLocalDateTime().format(formatter);
                        String stringRow = dateAndTime + "     " + doctorName;
                        rowsStringForComboBox.add(stringRow);
                    }
                    ObservableList<String> list = FXCollections.observableArrayList(rowsStringForComboBox);
                    AppointmentDateComboBox.getItems().clear();
                    AppointmentDateComboBox.setItems(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
                //what if there is no data for doctors or appointmens
                noAppointmentsLabel.setVisible(true);
                AppointmentDateComboBox.getItems().clear();
                AppointmentDateComboBox.getItems().add("Brak terminów");
            }

        } else {
            List<Referral> referrals = null;
            try {
                referrals = Referral.getActualReferralForPacient(patientExample, medicalSpecialist);
                Referral referral = Referral.getMostUrgentReferral(referrals);
                System.out.println("reffreal");
                try {
                    //  List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalFacilityExample, medicalSpecialist);
                    List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalSpecialist);


                    rows = rowsForComboBox;
                    if (rowsForComboBox.isEmpty()) {
                        noAppointmentsLabel.setVisible(true);
                        AppointmentDateComboBox.getItems().clear();
                        AppointmentDateComboBox.getItems().add("Brak terminów");
                    } else {
                        List<String> rowsStringForComboBox = new ArrayList<>();
                        rowsString = rowsStringForComboBox;

                        for (RowForComboBox row : rowsForComboBox) {
                            String doctorName = row.getDoctor().getName() + " " + row.getDoctor().getLastName();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            String dateAndTime = row.getLocalDateTime().format(formatter);
                            String stringRow = dateAndTime + " " + doctorName;
                            rowsStringForComboBox.add(stringRow);
                        }

                        ObservableList<String> list = FXCollections.observableArrayList(rowsStringForComboBox);
                        AppointmentDateComboBox.getItems().clear();
                        AppointmentDateComboBox.setItems(list);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    noAppointmentsLabel.setVisible(true);
                    AppointmentDateComboBox.getItems().clear();
                    AppointmentDateComboBox.getItems().add("Brak terminów");

                }
            } catch (Exception e) {
                System.out.println("no refferal");
                noRefferalLabel.setVisible(true);
                AppointmentDateComboBox.getItems().clear();
                AppointmentDateComboBox.getItems().add("Brak skierowaia - Terminy niedostępne");
            }
        }
    }


    public void setSetConfirmButtonAction(ActionEvent event) throws IOException {
        System.out.println("Zatwierdz");
        try {
            RowForComboBox rowForComboBox = null;
            for (int i = 0; i < rowsString.size(); i++) {
                if (rowsString.get(i) == AppointmentDateComboBox.getValue()) {
                    rowForComboBox = rows.get(i);
                }
            }
            System.out.println(rowForComboBox);
            rowForComboBox.getDoctor().changeDateAvailability(rowForComboBox.getLocalDateTime(), false);


            String doctorName = rowForComboBox.getDoctor().getName() + " " + rowForComboBox.getDoctor().getLastName();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String dateAndTime = rowForComboBox.getLocalDateTime().format(formatter);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Potwierdzenie wizyty");
            alert.setHeaderText("Wybrana wizyta");
            alert.setContentText("Lekarz: " + dateAndTime + "\nLekarz: " + doctorName);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                Parent functionalityChoiceParent = FXMLLoader.load(getClass().getResource("AppointmentChoice.fxml"));
                Scene funtionalityChoiceScene = new Scene(functionalityChoiceParent);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                stage.hide();
                stage.setScene(funtionalityChoiceScene);
                stage.show();
                Appointment appointment = new Appointment(rowForComboBox.getLocalDateTime(), patientExample);
                Appointment.getAppointments(patientExample);
                ExtendFile.writeExtends();

            }
        } catch (Exception exception) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wystąpił błąd");
            alert.setHeaderText("Nie wybrano odpowiednich danych do zatwierdzenia wizyty");
            alert.setContentText("Uzupełnij wymagane pola");
            alert.showAndWait();
        }
    }


    public void setCancelButtonAction(ActionEvent event) throws IOException {
        Parent functionalityChoiceParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene funtionalityChoiceScene = new Scene(functionalityChoiceParent);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.hide();
        stage.setScene(funtionalityChoiceScene);
        stage.show();
    }


}
