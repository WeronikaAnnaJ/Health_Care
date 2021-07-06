package sample;

import java.time.LocalDate;
import java.util.List;

public class MedicalExamination extends AssociationConstraint {

    private String name;
    private LocalDate date;
    private String interpretation;
    private String description;
    private String result;
    private String norm;

    MedicalExamination(String name, LocalDate date) {
        super();
        this.name = name;
        this.date = date;

    }

    public void setInterpretation(String interpretation) throws Exception {
        if (description == null || result == null) {
            throw new Exception("Cannot set interpretation when description or result is null.");
        }
        this.interpretation = interpretation;
    }


    public void setDescription(String description) {
        this.description = description;
    }



    public void serResult(String result, String norm) {
        this.result = result;
        this.norm = norm;
    }



    public String getResult() {
        return result;
    }

    public static MedicalExamination getLatestExamination() {
        return null;
    }


    public static List<String> getExaminations(String name) {
        return null;
    }










}






