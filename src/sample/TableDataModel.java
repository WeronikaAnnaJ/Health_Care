package sample;


import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.List;

public class TableDataModel {
    public String name;
    public String specializations;
    public Integer rating;
    public Doctor doctor;



    public TableDataModel(Doctor doctor) throws Exception {
        this.doctor = doctor;
        this.name = doctor.getName() + " " + doctor.getLastName();
        System.out.println("Table data model");

        List<Specialization> specializationList = Specialization.getSpecializations(doctor);
        System.out.println("Specialization list");
        for (Specialization s :
                specializationList) {
            System.out.println(s);
        }

        String specialization = "";
        for (Specialization s : specializationList) {
            System.out.println(s);
            specialization += Specialization.getStringMedicalSpecialization(s.getSpecialization()) + "\n";
            System.out.println(specialization);
        }
        //  this.specializations = specialization;
        this.specializations = specialization;
        Double doctorRating = doctor.getRating();

        if (doctorRating == null) {
            this.rating = null;
        } else {
            this.rating = Math.toIntExact(Math.round(doctorRating));
        }


    }

    public String getSpecializations() {
        return specializations;
    }


    public String getName() {
        return name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }


    public Doctor getDoctor() {
        return doctor;
    }


}

