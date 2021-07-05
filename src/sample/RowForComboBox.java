package sample;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RowForComboBox extends ObjectAssociation{
    private  Doctor doctor;
    private LocalDateTime localDateTime;

    RowForComboBox(Doctor doctor, LocalDateTime localDateTime){
        super();
        this.doctor=doctor;
        this.localDateTime=localDateTime;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "RowForComboBox{" +
                "doctor=" + doctor +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
