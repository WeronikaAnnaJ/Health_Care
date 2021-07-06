package sample;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


enum Status{
    WAITING,
    UNDARWAY,
    CANCELED,
    CONDUCTED
};


public class Appointment extends AssociationConstraint {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private float cost;
    private String recommendations;
    private Status currentStatus;



    Appointment(LocalDate date, LocalTime startTime, float cost) throws Exception {
        super();
        if(date == null || startTime == null){
            throw new Exception("Date and start tima cannot be null.");
        }
        this.date=date;
        this.startTime=startTime;
        this.cost=cost;
        currentStatus=Status.WAITING;
    }


    Appointment(LocalDateTime localDateTime, Patient patient) throws Exception {
        super();
        this.date=localDateTime.toLocalDate();
        this.startTime=localDateTime.toLocalTime();
        currentStatus=Status.WAITING;
        patient.addConnection("appointment", "patient", this);
    }


    public void setEndTime(LocalTime endTime){
        this.endTime=endTime;
        currentStatus=Status.CONDUCTED;
    }


    public void setRecommendations(String recommendations){
        this.recommendations=recommendations;
    }


    public void cancelAppointment(){

        currentStatus=Status.CANCELED;
    }



    public void registerStartingApoointment(){

        currentStatus=Status.UNDARWAY;
    }


    public static List<Appointment> getAppointments(Patient patient) throws Exception {
        List<Appointment> appointments= new ArrayList<>();
        ObjectAssociation [] objects= patient.getConnections("appointment");
        for (ObjectAssociation object: objects) {
            appointments.add((Appointment) object);
            System.out.println( (Appointment) object);
        }
        return appointments;
    }



    @Override
    public String toString() {
        return "Appointment{" +
                "date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", cost=" + cost +
                ", recommendations='" + recommendations + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}
