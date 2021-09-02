package sample;

import com.sun.javafx.collections.ObservableListWrapper;
import com.sun.jdi.Value;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Information implements Initializable {

    @FXML
    private Label noDoctorLabel;

    @FXML
    private AnchorPane backgroundPane;

    @FXML
    private TableColumn<TableDataModel, String> d;

    @FXML
    private TableColumn<TableDataModel, String> s;

    @FXML
    private TableColumn<TableDataModel, Integer> r;

    @FXML
    private Button confirmButton;

    @FXML
    private Button showButton;

    @FXML
    private Label noDoctorLabel1;

    @FXML
    private TableView<TableDataModel> tb;


    @FXML
    private Button specjalizationShowButton;
    private Map<String, MedicalSpecialization> specializationDictionary = Specialization.getSpecializationsDicrionary();

    public List<String> doctorsList = new ArrayList<>();
    public List<TableDataModel> tableDataModelList = new ArrayList<>();


    @FXML
    private ChoiceBox<String> doctorsForRating;

    @FXML
    private Slider rating;

    @FXML
    private ChoiceBox<String> fiterSpecialization;

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    void check(ActionEvent event) throws Exception {
        noDoctorLabel1.setVisible(false);
        String doctorsName = name.getText();
        String doctorsLastName = lastname.getText();
        boolean exist = false;
        Doctor doctor = null;
        doctorsList = new ArrayList<>();
        tableDataModelList = new ArrayList<>();

        ObservableList<TableDataModel> tableData = FXCollections.observableArrayList();
        List<Doctor> doctors = Doctor.getAllDoctors();
        for (Doctor d : doctors) {
            if (d.getName().equals(doctorsName) && d.getLastName().equals(doctorsLastName)) {
                doctor = d;
                exist = true;
            }
        }
        if (doctor != null && exist == true) {
            tb.setItems(null);
            ObservableList<TableDataModel> table = FXCollections.observableArrayList();
            table.add(new TableDataModel(doctor));
            tb.setItems(table);
        } else {
            noDoctorLabel1.setVisible(true);
        }

    }

    public ObservableList<TableDataModel> getDoctors() {
        doctorsList = new ArrayList<>();
        tableDataModelList = new ArrayList<>();
        ObservableList<TableDataModel> tableData = FXCollections.observableArrayList();
        List<Doctor> doctors = Doctor.getAllDoctors();
        for (Doctor d : doctors) {
            try {
                TableDataModel tableDataModel = new TableDataModel(d);
                tableData.add(tableDataModel);
                doctorsList.add(tableDataModel.getName());
                tableDataModelList.add(tableDataModel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return tableData;
    }


    public ObservableList<TableDataModel> getDoctors(MedicalSpecialization medicalSpecialization) throws Exception {
        doctorsList = new ArrayList<>();
        tableDataModelList = new ArrayList<>();
        ObservableList<TableDataModel> tableData = FXCollections.observableArrayList();
        List<Doctor> doctors = Specialization.getDoctors(Specialization.getSpecialization(medicalSpecialization));
        for (Doctor d : doctors) {
            try {
                TableDataModel tableDataModel = new TableDataModel(d);
                tableData.add(tableDataModel);
                doctorsList.add(tableDataModel.getName());
                tableDataModelList.add(tableDataModel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return tableData;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imageFile = new File(".idea/Images/Background_Login.jpg");
        javafx.scene.image.Image imageImage = new Image(imageFile.toURI().toString());
        BackgroundImage backgroundimage = new BackgroundImage(imageImage,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        backgroundPane.setBackground(background);
        System.out.println("cell value factory");


        d.setCellValueFactory(new PropertyValueFactory("name"));
        s.setCellValueFactory(new PropertyValueFactory("specializations"));
        r.setCellValueFactory(new PropertyValueFactory("rating"));
        System.out.println("get items");
        tb.setItems(getDoctors());
        //   Background bc=new Background()
        //     tb.setBackground();


        List<String> doctorsToRatingList = doctorsList;
        ObservableList<String> list = FXCollections.observableArrayList(doctorsToRatingList);
        doctorsForRating.setItems(list);

        List<String> specializationsList = new ArrayList<>(specializationDictionary.keySet());
        ObservableList<String> listSpec = FXCollections.observableArrayList(specializationsList);
        listSpec.add("Wszystkie");
        fiterSpecialization.setItems(listSpec);

    }

    @FXML
    void fiter(ActionEvent event) {
        noDoctorLabel1.setVisible(false);

        if (fiterSpecialization.getValue() == "Wszystkie") {
            tb.setItems(null);
            tb.setItems(getDoctors());
        } else {
            try {
                MedicalSpecialization medicalSpecialist = specializationDictionary.get(fiterSpecialization.getValue());
                System.out.println(medicalSpecialist);
                tb.setItems(null);
                tb.setItems(getDoctors(medicalSpecialist));
            } catch (Exception exception) {
                noDoctorLabel1.setVisible(true);
            }

        }

    }

    @FXML
    void setSetConfirmButtonAction(ActionEvent event) throws IOException {
        Parent functionalityChoiceParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene funtionalityChoiceScene = new Scene(functionalityChoiceParent);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.hide();
        stage.setScene(funtionalityChoiceScene);
        stage.show();
    }


    @FXML
    void setConfirmRatingButtonAction(ActionEvent event) {
        String doctorsName = doctorsForRating.getValue();
        Doctor doctor = null;
        for (TableDataModel tbm : tableDataModelList) {
            if (tbm.getName().equals(doctorsName)) {
                doctor = tbm.getDoctor();
            }
        }
        if (doctor == null) {
            noDoctorLabel.setVisible(true);

        } else {
            double ratingValue = rating.getValue();
            System.out.println(doctor + "\n " + ratingValue);
            doctor.addCValue(ratingValue);
            tb.setItems(null);
            tb.setItems(getDoctors());
        }
    }

}
