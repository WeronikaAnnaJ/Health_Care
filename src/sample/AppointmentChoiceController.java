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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class AppointmentChoiceController implements Initializable {


Patient patientExample = Patient.getPatientExample();
MedicalFacility medicalFacilityExample= MedicalFacility.getMedicalFacilityExample();

List<RowForComboBox> rows;
List<String> rowsString;

 @FXML
 private Label InformationSpecializationLabel;

 @FXML
 private Label InformationDateLabel;

 @FXML
 private ComboBox<String> AppointmentDateComboBox;

 @FXML
 private Button confirmButton;

 @FXML
 private Button cancelButton;


 @FXML
 private ComboBox<String> specializationComboBox;

 ObservableList<String> options =
         FXCollections.observableArrayList(
                 "Option 1",
                 "Option 2",
                 "Option 3"
         );

;

private Map<String,MedicalSpecialist > specializationDictionary = Doctor.getSpecializationsDicrionary();

 @FXML
public void specializationChoice(){

  MedicalSpecialist medicalSpecialist= specializationDictionary.get(specializationComboBox.getValue());
  System.out.println(medicalSpecialist);

  if(medicalSpecialist==MedicalSpecialist.FamilyDoctor){

     try {
      List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalFacilityExample,medicalSpecialist);
      rows=rowsForComboBox;
      if(rowsForComboBox.isEmpty()){
       AppointmentDateComboBox.getItems().clear();
       AppointmentDateComboBox.getItems().add("No available appointments");

      }else {
       List<String> rowsStringForComboBox = new ArrayList<>();
       rowsString=rowsStringForComboBox;


       for (RowForComboBox row : rowsForComboBox) {
        String doctorName = row.getDoctor().getName() + " " + row.getDoctor().getLastName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm");
        String dateAndTime = row.getLocalDateTime().format(formatter);
        String stringRow = dateAndTime + "     " + doctorName;
        rowsStringForComboBox.add(stringRow);
       }


       for (String s : rowsStringForComboBox
       ) {
        System.out.println(s);
       }

       ObservableList<String> list = FXCollections.observableArrayList(rowsStringForComboBox);
       AppointmentDateComboBox.getItems().clear();
       AppointmentDateComboBox.setItems(list);
      }
     } catch (Exception e) {
      e.printStackTrace();
      //what if there is no data for doctors or appointmens
      AppointmentDateComboBox.getItems().clear();
      AppointmentDateComboBox.getItems().add("No available dates");

   }

  }else {


   List<Referral> referrals = null;
   try {
    referrals = Referral.getActualReferralForPacient(patientExample, medicalSpecialist);
    Referral referral = Referral.getMostUrgentReferral(referrals);
    System.out.println("reffreal");


    try {
     List<RowForComboBox> rowsForComboBox = Doctor.getSegregatedAvailableDatesForDoctors(medicalFacilityExample, medicalSpecialist);
     rows=rowsForComboBox;

     if (rowsForComboBox.isEmpty()) {
      AppointmentDateComboBox.getItems().clear();
      AppointmentDateComboBox.getItems().add("No available appointments");

     } else {
      List<String> rowsStringForComboBox = new ArrayList<>();
      rowsString=rowsStringForComboBox;
      for (RowForComboBox row : rowsForComboBox) {
       String doctorName = row.getDoctor().getName() + " " + row.getDoctor().getLastName();
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
       String dateAndTime = row.getLocalDateTime().format(formatter);
       String stringRow = dateAndTime + " " + doctorName;
       rowsStringForComboBox.add(stringRow);
      }


      for (String s : rowsStringForComboBox
      ) {
       System.out.println(s);
      }

      ObservableList<String> list = FXCollections.observableArrayList(rowsStringForComboBox);
      AppointmentDateComboBox.getItems().clear();
      AppointmentDateComboBox.setItems(list);
     }
    } catch (Exception e) {
     e.printStackTrace();
     //what if there is no data for doctors or appointmens
     AppointmentDateComboBox.getItems().clear();
     AppointmentDateComboBox.getItems().add("No available dates");

    }


   } catch (Exception e) {
    System.out.println("no refferal");
    AppointmentDateComboBox.getItems().clear();
    AppointmentDateComboBox.getItems().add("Not available");
   }
  }
  //wybierz najnowszą


  //AppointmentDateComboBox.setItems(options);

 }


 //canfirm button



 public void  setSetConfirmButtonAction(ActionEvent event ) throws IOException {
  System.out.println("Zatwierdz");

  try {
   RowForComboBox rowForComboBox = null;
   for (int i = 0; i < rowsString.size(); i++) {
    if (rowsString.get(i) == AppointmentDateComboBox.getValue()) {
     rowForComboBox = rows.get(i);
    }
   }


   System.out.println(rowForComboBox);
   //set appointmemt term not available anymore
   rowForComboBox.getDoctor().changeDateAvailability(rowForComboBox.getLocalDateTime(), false);
   //utworz wizyte i polacz z pacjentem

   Appointment appointment= new Appointment(rowForComboBox.getLocalDateTime(), patientExample);
   Appointment.getAppointments(patientExample);

   String doctorName = rowForComboBox.getDoctor().getName() + " " + rowForComboBox.getDoctor().getLastName();
   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
   String dateAndTime = rowForComboBox.getLocalDateTime().format(formatter);

   Alert alert = new Alert(Alert.AlertType.INFORMATION);
   alert.setTitle("Potwierdzenie wizyty");
   alert.setHeaderText("Wybrana wizyta");
   alert.setContentText("Lekarz: " + dateAndTime + "\nLekarz: " + doctorName);

   Optional<ButtonType> result = alert.showAndWait();
  if (result.get() == ButtonType.OK){
   Parent functionalityChoiceParent= FXMLLoader.load(getClass().getResource("AppointmentChoice.fxml"));
   Scene funtionalityChoiceScene= new Scene(functionalityChoiceParent);
   Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
   stage.hide();
   stage.setScene(funtionalityChoiceScene);
   stage.show();


  } else {
   // ... user chose CANCEL or closed the dialog
  }

   //show
  }catch (Exception exception){

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Wystąpił błąd");
    alert.setHeaderText("Nie wybrano odpowiednich danych do zatwierdzenia wizyty");
    alert.setContentText("Uzupełnij wymagane pola");
    alert.showAndWait();
   }

 }



 public void  setCancelButtonAction(ActionEvent event ) throws IOException {
  Parent functionalityChoiceParent= FXMLLoader.load(getClass().getResource("sample.fxml"));
  Scene funtionalityChoiceScene= new Scene(functionalityChoiceParent);
  Stage stage = (Stage) (((Node)event.getSource()).getScene().getWindow());
  stage.hide();
  stage.setScene(funtionalityChoiceScene);
  stage.show();

 }



 @Override
 public void initialize(URL url, ResourceBundle resourceBundle) {

  List<String> specializationsList= new ArrayList<>(specializationDictionary.keySet());
  ObservableList<String> list =  FXCollections.observableArrayList(specializationsList);
  specializationComboBox.setItems(list);
  AppointmentDateComboBox.getItems().add("Brak danych");



 }

 //sprawdzenie czy wybrana specjaliza jest rowna rodzinny jak tak to juz szukasz terminow
 //jeśli nie to sprawdź dla danego pacjenta skierowania
 //zobacz czy skierowanie jest dla niego do danego specjalisty
 //zobacz  czy skierowanie jest ważne
 //jeśli nie wyświetl infromacje
 //jeśli tak wyszukaj terminy





}
