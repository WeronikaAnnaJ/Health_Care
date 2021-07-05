package sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Referral extends AssociationConstraint{
    private LocalDate dateOfIssuing;
    private LocalDate expiryDate;
    private String medicalExamination;//albo lista w zaleznosci ile badań new ArrayList<>();//albo inna nazwa
    private String PESEL;
    private String firstName;
    private String lastName;
    private Doctor orderingDoctor;
    private MedicalSpecialist medicalSpecialist;

    Referral(LocalDate expiryDate, String medicalExamination, String PESEL, String firstName, String lastName, Doctor orderingDoctor, MedicalSpecialist medicalSpecialist )throws Exception{
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

    //get skierowania na daną usługę
    //wybierz pacjenta jakiego chcesz skierowanie
    //wybierz nazwe jaką chcesz wyszykać
    //pobierz wszytskei skietowania dla danego pacjenta
    //wybierz to skierowanie którego nazwa rowna się podana
    //sprawdź jegoo datę ważnosci
    //zwroc błąd jak nie ma takiego skierowania dla tej osoby lub nie ma takiego pacjenta
    // albo jak juz wygasło

    //sprawdz skeirowania wszytskke dla danego pacjenta


    public String getPESEL() {
        return PESEL;
    }

    public static List<Referral> getActualReferralForPacient(Patient patient, MedicalSpecialist medicalSpecialist) throws Exception {
        ObjectAssociation[] referrals= patient.getConnections("referral"); //getfirst actual?
        if(referrals.length==0){
            throw new Exception("There are no referrals for patient");
        }
        List<Referral> actualReferral= new ArrayList<>();
        for ( ObjectAssociation object:referrals) {
            Referral referral= (Referral) object;
            if(referral.getMedicalSpecialist()==medicalSpecialist){
                if(referral.isActual()== true){
                    actualReferral.add(referral);
                }
            }
        }
        if(actualReferral.isEmpty()){
            throw new Exception("There are no actual referrals for patient");
        }

        return actualReferral;
    }
    //get newest refferal from refferals
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
    public MedicalSpecialist getMedicalSpecialist() {
        return medicalSpecialist;
    }

    public LocalDate getDateOfIssuing() {
        return dateOfIssuing;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
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
