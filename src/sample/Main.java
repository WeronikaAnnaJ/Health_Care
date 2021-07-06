package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.initStyle(StageStyle.DECORATED);
        Scene scene=new Scene(root, 650, 605);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {

   try {
            Employee employee = new Employee("Karol", "Wojciech", null, "Karolak", "1", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl");
            employee.setEmploymentDate(LocalDate.of(2020,6,1));
            System.out.println(employee);
            System.out.println(employee.getPeriodOfEmployment());

            EmploymentContract employmentContract= new EmploymentContract(Tenure.FullTime);
            employmentContract.setSalary(3000);

            employee.addConnection("employed under","apply to", employmentContract);
            employee.showConnections("employed under");

        } catch (Exception e) {
            e.printStackTrace();
        }

        //rejestratorki
        try {
            List<String> languages0=new ArrayList<>();
            languages0.add("Polish");
            languages0.add("English");

            WardClerk wardClerk0=new WardClerk("Karol", "Wojciech", null, "Karolak", "2", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl",languages0);
            wardClerk0.setEmploymentDate(LocalDate.of(2020,6,1));
            System.out.println(wardClerk0);
            System.out.println(wardClerk0.getPeriodOfEmployment());

            EmploymentContract employmentContract0= new EmploymentContract(Tenure.FullTime);
            employmentContract0.setSalary(3000);

            wardClerk0.addConnection("employed under","apply to", employmentContract0);
            wardClerk0.showConnections("employed under");

        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            List<String> languages1=new ArrayList<>();
            languages1.add("Polish");
            languages1.add("English");

            WardClerk wardClerk1=new WardClerk("Karol", "Wojciech", null, "Karolak", "3", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl", languages1);
            wardClerk1.setEmploymentDate(LocalDate.of(2020,6,1));
            System.out.println(wardClerk1);
            System.out.println(wardClerk1.getPeriodOfEmployment());

            EmploymentContract employmentContract1= new EmploymentContract(Tenure.FullTime);
            employmentContract1.setSalary(3000);

            wardClerk1.addConnection("employed under","apply to", employmentContract1);
            wardClerk1.showConnections("employed under");
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            List<String> languages2=new ArrayList<>();
            languages2.add("Polish");
            languages2.add("English");

            WardClerk wardClerk2=new WardClerk("Karol", "Wojciech", null, "Karolak", "4", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl",languages2);
            wardClerk2.setEmploymentDate(LocalDate.of(2020,6,1));
            System.out.println(wardClerk2);
            System.out.println(wardClerk2.getPeriodOfEmployment());
            wardClerk2.addLanguage("Italian");
            wardClerk2.showLanguages();

            EmploymentContract employmentContract2= new EmploymentContract(Tenure.FullTime);
            employmentContract2.setSalary(3000);

            wardClerk2.addConnection("employed under","apply to", employmentContract2);
            wardClerk2.showConnections("employed under");
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            List<String> languages3=new ArrayList<>();
            languages3.add("Polish");
            languages3.add("English");

            WardClerk wardClerk3=new WardClerk("Karol", "Wojciech", null, "Karolak", "5", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl",languages3);
            wardClerk3.setEmploymentDate(LocalDate.of(2020,6,1));
            System.out.println(wardClerk3);
            System.out.println(wardClerk3.getPeriodOfEmployment());
            wardClerk3.addLanguage("Japanese");
            wardClerk3.showLanguages();


            EmploymentContract employmentContract3= new EmploymentContract(Tenure.FullTime);
            employmentContract3.setSalary(3000);

            wardClerk3.addConnection("employed under","apply to", employmentContract3);
            wardClerk3.showConnections("employed under");

        } catch (Exception e) {
            e.printStackTrace();
        }


        MedicalFacility medicalFacility= MedicalFacility.getMedicalFacilityExample();
        System.out.println(medicalFacility);

        try{
            Doctor doctor= new Doctor("Karol", "Wojciech", null, "Karolak", "7", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl","1", MedicalSpecialist.Dermatologist);
            doctor.addSpecialization(MedicalSpecialist.FamilyDoctor);
            System.out.println(doctor);
            doctor.showSpecializations();
            doctor.addConnection("employed","employ",medicalFacility);

            doctor.addAvaliableDate(LocalDateTime.of(2021,6,6,8,15) );
            doctor.addAvaliableDate(LocalDateTime.of(2021,6,9,10,15) );
            doctor.addAvaliableDate(LocalDateTime.of(2021,6,9,11,15) );
            doctor.addAvaliableDate(LocalDateTime.of(2021,6,10,15,0) );
            doctor.addAvaliableDate(LocalDateTime.of(2021,6,10,8,0) );

            doctor.showDates();

        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            Doctor doctor1= new Doctor("Karol", "Wojciech", null, "Karolak", "8", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl","2", MedicalSpecialist.Internist);
            doctor1.addSpecialization(MedicalSpecialist.Gynaecologist);
            System.out.println(doctor1);
            doctor1.showSpecializations();
            doctor1.addConnection("employed","employ",medicalFacility);

            doctor1.addAvaliableDate(LocalDateTime.of(2021,6,6,8,15) );
            doctor1.addAvaliableDate(LocalDateTime.of(2021,6,6,10,15) );
            doctor1.addAvaliableDate(LocalDateTime.of(2021,6,6,11,15) );
            doctor1.addAvaliableDate(LocalDateTime.of(2021,6,11,15,0) );
            doctor1.addAvaliableDate(LocalDateTime.of(2021,6,11,8,0) );

            doctor1.changeDateAvailability(LocalDateTime.of(2021,6,6,8,15), false);
            doctor1.changeDateAvailability(LocalDateTime.of(2021,6,11,15,0), false);
            doctor1.changeDateAvailability(LocalDateTime.of(2021,6,11,8,0), false);

            System.out.println("\nAvailable dates for " + doctor1);
            Map<LocalDateTime, Boolean> availableDates= doctor1.getDatesWhere(true);
            availableDates.forEach((key, value)->{
                System.out.println("Time : "+ key.format( DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " - "+ value);

            });
            doctor1.showDates();
        }catch(Exception e){
            e.printStackTrace();
        }



        try{
            Doctor doctor2= new Doctor("Karol", "Wojciech", null, "Karolak", "9", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl","3", MedicalSpecialist.Neurologist);
            doctor2.addSpecialization(MedicalSpecialist.FamilyDoctor);
            System.out.println(doctor2);
            doctor2.showSpecializations();

            doctor2.addAvaliableDate(LocalDateTime.of(2021,6,8,8,15) );
            doctor2.addAvaliableDate(LocalDateTime.of(2021,6,9,8,15) );
            doctor2.addAvaliableDate(LocalDateTime.of(2021,6,10,8,15) );
            doctor2.addAvaliableDate(LocalDateTime.of(2021,6,11,8,0) );
            doctor2.addAvaliableDate(LocalDateTime.of(2021,6,12,8,0) );
            doctor2.showDates();

            doctor2.addConnection("employed","employ",medicalFacility);
        }catch(Exception e){
            e.printStackTrace();
        }



        try{
            Doctor doctor3= new Doctor("Karol", "Wojciech", null, "Karolak", "10", LocalDate.of(1990, 12, 12), "111-111-111", "karol.wojciech.karolak@wp.pl","3", MedicalSpecialist.FamilyDoctor);
            doctor3.addSpecialization(MedicalSpecialist.Cardiologist);
            System.out.println(doctor3);
            doctor3.showSpecializations();
            doctor3.addConnection("employed","employ",medicalFacility);

/*
            doctor3.addAvaliableDate(LocalDateTime.of(2021,6,8,10,15) );
            doctor3.addAvaliableDate(LocalDateTime.of(2021,6,9,10,15) );
            doctor3.addAvaliableDate(LocalDateTime.of(2021,6,10,10,15) );
            doctor3.addAvaliableDate(LocalDateTime.of(2021,6,11,10,0) );
            doctor3.addAvaliableDate(LocalDateTime.of(2021,6,12,10,0) );

            doctor3.showDates();
*/
        /////////////////////////

        }catch(Exception e){
            e.printStackTrace();
        }


        try {
            medicalFacility.showConnections("employ");
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            List<Doctor> doctors= Doctor.getDoctorsEmployedIn(medicalFacility);
            for(Doctor doctor : doctors){
                System.out.println(doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<ObjectLifeSpan> ob= ObjectLifeSpan.getExtentForClass(WardClerk.class);
        for(ObjectLifeSpan o: ob){
            System.out.println(o);
        }

        List<String> doctorsForApoointment=new ArrayList<>();
        doctorsForApoointment.add("Cardiologist");
        doctorsForApoointment.add("Dermatologist");
        doctorsForApoointment.add("Gynaecologist");
        doctorsForApoointment.add("Internist");
        doctorsForApoointment.add("Neurologist");
        doctorsForApoointment.add("Orthopaedist");
        doctorsForApoointment.add("FamilyDoctor");


        try {
            Doctor.showDatesForAllDoctors(medicalFacility);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            List<Doctor> doctors= Doctor.getDoctors(MedicalSpecialist.FamilyDoctor, medicalFacility);
            int i=1;
            System.out.println("Family doctors in medical facility");
            for (Doctor d:doctors) {
                System.out.println(i +".  "+ d);
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            System.out.println(Doctor.getMedicalSpecialistFor("FamilyDoctor").toString());
            System.out.println(Doctor.getMedicalSpecialistFor("Internist").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //working Example = Patient
        try {
            Patient patientExample= Patient.getPatientExample();
            Referral referralCardiologist= new Referral(LocalDate.of(2021,9,6),"Appointment", "999","Weronika","Kowalska",null,MedicalSpecialist.Cardiologist);
            Referral referralInternist= new Referral(LocalDate.of(2021,9,6),"Appointment", "999","Weronika","Kowalska",null,MedicalSpecialist.Internist);
            Referral referralGinecologist= new Referral(LocalDate.of(2021,10,6),"Appointment", "999","Weronika","Kowalska",null,MedicalSpecialist.Gynaecologist);
            Referral referralGinecologist2= new Referral(LocalDate.of(2021,11,11),"Appointment", "999","Weronika","Kowalska",null,MedicalSpecialist.Gynaecologist);
            Referral referralDermatologist= new Referral(LocalDate.of(2021,1,6),"Appointment", "999","Weronika","Kowalska",null,MedicalSpecialist.Dermatologist);

            referralCardiologist.addConnectionWithQualifier("patient","referral", patientExample, patientExample.getPesel());
            referralInternist.addConnectionWithQualifier("patient","referral", patientExample, patientExample.getPesel());
            referralGinecologist.addConnectionWithQualifier("patient","referral", patientExample, patientExample.getPesel());
            referralGinecologist2.addConnectionWithQualifier("patient","referral", patientExample, patientExample.getPesel());
            referralDermatologist.addConnectionWithQualifier("patient","referral", patientExample, patientExample.getPesel());

            patientExample.showConnections("referral");
            Referral.getActualReferralForPacient(patientExample,MedicalSpecialist.Cardiologist);
            List<Referral> referrals= Referral.getActualReferralForPacient(patientExample, MedicalSpecialist.Gynaecologist);
            System.out.println(Referral.getMostUrgentReferral(referrals));

            LocalDateTime localDateTime= LocalDateTime.of(2021,10,10,12,10);
            System.out.println(localDateTime.toLocalDate());
            System.out.println(localDateTime.toLocalTime());
            Appointment appointment= new Appointment(LocalDateTime.of(2021,10,10,12,10), patientExample);
          //  appointment.addConnection("patient","appointment", patientExample);
            List<Appointment> appointments= Appointment.getAppointments(patientExample);
            for (Appointment appointment1 : appointments){
                System.out.println(appointment1);
            }

            List<RowForComboBox> rows= Doctor.getSegregatedAvailableDatesForDoctors(medicalFacility, MedicalSpecialist.FamilyDoctor);
            System.out.println(rows.size());
            for(RowForComboBox row : rows) {
                System.out.println(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        ExtendFile.writeExtends();

        try {
            System.out.println();
            ObjectLifeSpan.showExtent(Patient.class);
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(Patient.class);
            System.out.println();
            for (ObjectLifeSpan o: getExtentForClass) {
                AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                System.out.println("association");
                con.forEach((key, value) -> System.out.println(key + ":" + value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            System.out.println();
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(Doctor.class);
            System.out.println();
            for (ObjectLifeSpan o: getExtentForClass) {
                AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                System.out.println("association");
                con.forEach((key, value) -> System.out.println(key + ":" + value + "  " ) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ObjectLifeSpan.showExtent(MedicalFacility.class);
            System.out.println();
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(MedicalFacility.class);
            System.out.println();
            for (ObjectLifeSpan o: getExtentForClass) {
                AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                System.out.println("association");
                con.forEach((key, value) -> System.out.println(key + ":" + value + "  " ) );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);



        try {
            ObjectLifeSpan.showExtent(MedicalFacility.class);
            System.out.println();
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(MedicalFacility.class);
            System.out.println();
            for (ObjectLifeSpan o: getExtentForClass) {
                 AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                 Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                 System.out.println("association");
                 con.forEach((key, value) -> System.out.println(key + ":" + value + "  " ) );
                }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println();
            ObjectLifeSpan.showExtent(Referral.class);
            List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(Referral.class);
            System.out.println();
            for (ObjectLifeSpan o: getExtentForClass) {
                AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                Map<String, LinkedHashMap<Object, ObjectAssociation>> con = associationConstraint.getConections();
                System.out.println("association");
                con.forEach((key, value) -> System.out.println(key + ":" + value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            ObjectLifeSpan.showExtent(Doctor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }



        try {
            ObjectLifeSpan.showExtent(EmploymentContract.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            ObjectLifeSpan.showExtent(RowForComboBox.class);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }
}
