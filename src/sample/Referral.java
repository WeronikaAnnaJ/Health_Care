package sample;

import java.time.LocalDate;
import java.util.*;

public class Referral extends ObjectAssociation{
    private LocalDate dateOfIssuing;
    private LocalDate expiryDate;
    private String medicalExamination;//albo lista w zaleznosci ile badań new ArrayList<>();//albo inna nazwa
    private String PESEL;
    private String firstName;
    private String lastName;
    private Doctor orderingDoctor;
    private MedicalSpecialization medicalSpecialist;

    Referral(LocalDate expiryDate, String medicalExamination, String PESEL, String firstName, String lastName, Doctor orderingDoctor, MedicalSpecialization medicalSpecialist )throws Exception{
        super();
        if( expiryDate == null || medicalSpecialist==null || medicalExamination ==null ){
            throw new Exception("Expiry date, medicalSpecialist, medical Examination cannot be null.");
        }
        this.dateOfIssuing= LocalDate.now();
        this.expiryDate=expiryDate;
        this.medicalExamination=medicalExamination;
        this.PESEL=PESEL;
        this.firstName=firstName;
        this.lastName=lastName;
        this.orderingDoctor=orderingDoctor;
        this.medicalSpecialist=medicalSpecialist;

    }



    public static Referral getMostUrgentReferral(List<Referral> list){
        Referral mostUrgentReferral = list.get(0);
        LocalDate mostUrgentDate= list.get(0).getExpiryDate();
        for (Referral referral: list) {
            if(referral.isActual()==true  &&  referral.getExpiryDate().isBefore(mostUrgentDate)){
                mostUrgentDate=referral.getExpiryDate();
                mostUrgentReferral=referral;
            }
        }
        return mostUrgentReferral;
    }




    public boolean isActual(){
        if(LocalDate.now().isBefore(expiryDate)){
            return true;
        }
        return false;
    }



    public MedicalSpecialization getMedicalSpecialist() {
        return medicalSpecialist;
    }




    public LocalDate getDateOfIssuing() {
        return dateOfIssuing;
    }




    public LocalDate getExpiryDate() {
        return expiryDate;
    }




    public String getPESEL() {
        return PESEL;
    }




    public static List<Referral> getActualReferralForPacient(Patient patient, MedicalSpecialization medicalSpecialist) throws Exception {
        List<Referral>  list =  new ArrayList<>();
        try {
            System.out.println();
            ObjectLifeSpan.showExtent(Patient.class);
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(Patient.class);
            for (ObjectLifeSpan o: getExtentForClass) {
                AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                con.forEach((key, value) ->{
                    if(key.equals("referral")){
                        LinkedHashMap<Object, ObjectAssociation> linkedHashMap = value;
                        linkedHashMap.forEach((key1, value1) -> {
                            Referral referral= ((Referral) key1);
                            if((referral.getPESEL().equals(patient.getPesel())) && (referral.getMedicalSpecialist()== medicalSpecialist) && (referral.getExpiryDate().isAfter(LocalDate.now()))){{
                                list.add(referral);
                            }}
                        });
                    }}); }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }




    @Override
    public String toString() {
        return "Referral{" +
                "dateOfIssuing=" + dateOfIssuing +
                ", expiryDate=" + expiryDate +
                ", medicalExamination='" + medicalExamination + '\'' +
                ", PESEL='" + PESEL + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", orderingDoctor=" + orderingDoctor +
                ", medicalSpecialist=" + medicalSpecialist +
                '}';
    }
}
