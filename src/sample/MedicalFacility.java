package sample;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class MedicalFacility extends AssociationConstraint{

    //ADRES MOZE JAKO KL
    private String address;
    private String name;
    private String email;
    private static String URLAddress="www.centrumMedyczne";
    private List<String> cooperatingFacilities;
    private LocalTime openingHours;
    private LocalTime closingHours;
    //dni otwarcia format?
    private String openOn;


    private static  MedicalFacility medicalFacilityExample;

    static {
        try {
            medicalFacilityExample =new MedicalFacility("Medyczna 5 Warszawa 15-876", "Centrum Medyczne");
            medicalFacilityExample.setEmail("centrum_medyczne@gmail.com");
            medicalFacilityExample.setOpeningHours(LocalTime.of(7,30));
            medicalFacilityExample.setClosingHours(LocalTime.of(20,00));
            medicalFacilityExample.setOpenOn("Pon - Pt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    MedicalFacility(String address, String name){
        super();
        this.address=address;
        this.name=name;
    }

    public void setEmail(String email) {
        this.email=email;
    }



    public void setOpenOn(String days){
        openOn=days;
    }

    public void setClosingHours(LocalTime closingHours){
        this.closingHours=closingHours;
    }

    public void setOpeningHours(LocalTime openingHours){
        this.openingHours=openingHours;
    }


    @Override
    public String toString() {
        return "MedicalFacility{" +
                "address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", URLAddress='" + URLAddress + '\'' +
                ", cooperatingFacilities=" + cooperatingFacilities +
                ", openingHours=" + openingHours +
                ", closingHours=" + closingHours +
                ", openOn='" + openOn + '\'' +
                '}';
    }

    public static MedicalFacility getMedicalFacilityExample() {
        return medicalFacilityExample;
    }


    //zwróc zatrudnionych lekarzy
}
