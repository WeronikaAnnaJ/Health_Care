package sample;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

enum MedicalSpecialist{
    Cardiologist,
    Dermatologist,
    Gynaecologist,
    Internist,
    Neurologist,
    FamilyDoctor
};


public class Doctor extends Medic { //klasa Lekarz dziedzicząca po klasie Pracownik Medyczny

    private String PWZ;
    private EnumSet<MedicalSpecialist> specializations;//Specjalny rodzaj kolekcji pełniący rolę dyskryminatora

  //  private static Map<Doctor, Map<LocalDateTime, Boolean>> datesForDoctors = new HashMap<>();
    private static Map<String, Doctor> allPWZ = new LinkedHashMap<>();

    private Map<LocalDateTime, Boolean> availableDates = new TreeMap<LocalDateTime, Boolean>();



    Doctor(String name, String middleName, String maidenName, String lastName, String pesel, LocalDate birthDate, String phoneNumber, String emailAddress, String PWZ, MedicalSpecialist specialization) throws Exception {
        super(name, middleName, maidenName, lastName, pesel, birthDate, phoneNumber, emailAddress);


        /** Ograniczenie {Unique}  **/
        if (allPWZ.containsKey(pesel)) { //sprawdzenie, czy podany PESEL już istnieje i jest zapisany
            throw new Exception("PWZ value cannot be null or is no longer available.");//wyrzucenie wyjątku, blokującego utworzenie kolejnego pracownika z tym samym peselem
        }
        allPWZ.put(PWZ, this);//dodanie peselu i obiektu do mapy
        this.PWZ = PWZ;

        specializations = EnumSet.of(specialization);
    }





    /** Dostępne daty  **/
    //moze dodaj tak zeby calą liste mofło się dodawać

    //dodać doostępną date
    public void addAvaliableDate(LocalDateTime dateTime) {
        this.availableDates.put(dateTime, true);
    }

    //zmień dostępność daty
    public void changeDateAvailability(LocalDateTime date, boolean newAvailability) {
        this.availableDates.replace(date, newAvailability);
    }

    //sprawdż czy data jest dostępna
    public boolean isDateAvailable(LocalDateTime dateTime) {
        return availableDates.get(dateTime);
    }

    //zwróc daty dla danego lekarza
    public Map<LocalDateTime, Boolean> getDates() {
        return availableDates;
    }


    //wyświetl daty dla danego lekarza
    public void showDates() {
        System.out.println("Dates for doctor : " + this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if(availableDates.isEmpty()){
            System.out.println("No dates. ");
        }else{

            availableDates.forEach((key, value) -> {
                System.out.println("Available : " + value + "\nTime : " + key.format(formatter) + " \nDoctor : " + this.getName() + " " + this.getLastName() + "\n" );
            });

        }

    }

    //zmień na date,na zajęte i odwrotnie

    //dodaj daty lekarza do ogólnych dat dla wszystkich lekarzy, albo to już w tamtej klasie przy przetwarzaniu danych?

    //może jako metoda a nei stałe pole



    public static Map<Doctor, Map<LocalDateTime, Boolean>> getDatesForAllDoctors(MedicalFacility medicalFacility) throws Exception {
        Map<Doctor, Map<LocalDateTime, Boolean>> datesForDoctors = new HashMap<>();
        List<Doctor> doctors= getDoctorsEmployedIn(medicalFacility);
        for(Doctor doctor : doctors) {
            Map<LocalDateTime,Boolean> dates=doctor.getDates();
            datesForDoctors.put(doctor,dates);
        }

        return datesForDoctors;
    }




    public static List<RowForComboBox> getSegregatedAvailableDatesForDoctors(MedicalFacility medicalFacility, MedicalSpecialist medicalSpecialist) throws Exception {
        List<Doctor> doctors = getDoctorsEmployedIn(medicalFacility);
        List<RowForComboBox> informations = new ArrayList<>();

        for (Doctor doctor : doctors) {
            if (doctor.getSpecializations().contains(medicalSpecialist)) {
                List<LocalDateTime> availableDates = doctor.getAvailableSegregateDates();
                for (LocalDateTime d:availableDates) {
                    System.out.println(d);
                }
                for (LocalDateTime date : availableDates) {
                    RowForComboBox row = new RowForComboBox(doctor, date);
                    informations.add(row);
                }
            }}

        informations= informations.stream()
                .sorted(Comparator.comparing(RowForComboBox::getLocalDateTime)).collect(Collectors.toList());
        return informations;
        //segregate list
        //foreach zmien 1 cześć na datę i godzinę i przesuwaj w lewo dopóki nie będzie poprzednia mniejsza
    }





    public static void showDatesForAllDoctors(MedicalFacility medicalFacility)throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Dates for all doctors : " );
        Map<Doctor, Map<LocalDateTime, Boolean>> datesForDoctors =getDatesForAllDoctors(medicalFacility);
        datesForDoctors.forEach((key, value) -> {
            key.showDates();
            System.out.println();
    });
    }


    public static void  showMap(Map<LocalDateTime, Boolean> dates){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        dates.forEach((key, value) -> {
            System.out.println("Available : " + value + "\nTime : " + key.format(formatter) );
        });


    }

    public static List<Doctor> getDoctors(MedicalSpecialist medicalSpecialist, MedicalFacility medicalFacility) throws Exception {
        List<Doctor> allDoctors= Doctor.getDoctorsEmployedIn(medicalFacility);
        List<Doctor> doctors= new ArrayList<>();
        for( Doctor doctor : allDoctors){

            if(doctor.getSpecializations().contains(medicalSpecialist)){
                doctors.add(doctor);
            }
        }
        return doctors;
    }


    //metoda zwracająca wszytkie wizyty dozwolone dla danego doktora
    public  Map<LocalDateTime, Boolean> getDatesWhere(Boolean available){
        Map<LocalDateTime, Boolean> allDates= this.availableDates;
        Map<LocalDateTime, Boolean> availableDates= new TreeMap<>();
        allDates.forEach((key, value)->{
            if(value==available){
                availableDates.put(key,available);
            }
        });
        return availableDates;
    }


    //metoda zwracająca wszytkie wizyty dozwolone dla danego doktora
    public  List<LocalDateTime> getAvailableSegregateDates(){
        Map<LocalDateTime, Boolean> allDates= this.availableDates;
        List<LocalDateTime> availableDates= new ArrayList<>();
        allDates.forEach((key, value)->{
            if(value==true){
                availableDates.add(key);
            }
        });
        Collections.sort(availableDates);
        return availableDates;
    }

    /** -----------------------  **/



    public EnumSet<MedicalSpecialist> getSpecializations(){
        return this.specializations;
    }






    public void addSpecialization(MedicalSpecialist medicalSpecialist) {
        specializations.add(medicalSpecialist);
    }


    public void showSpecializations() {
        System.out.print("Medical specialist " + super.toString() + " specializations : ");
        for (MedicalSpecialist medicalSpecialist : specializations) {
            System.out.println(medicalSpecialist);
        }
    }


    public static List<Doctor> getDoctorsEmployedIn(MedicalFacility medicalFacility) throws Exception {
        List<Doctor> doctors = new ArrayList<>();

        try {
            Map<String, LinkedHashMap<Object, ObjectAssociation>> con = medicalFacility.getConections();
            List<Doctor> doctorsList= new ArrayList<>();
          //  LinkedHashMap<Object, ObjectAssociation> objects = con.get("employ");
           List<Doctor>  list =  new ArrayList<>();

            try {
                System.out.println("METOD");
                List<ObjectLifeSpan> getExtentForClass= ObjectLifeSpan.getExtentForClass(MedicalFacility.class);
                System.out.println();
                for (ObjectLifeSpan o: getExtentForClass) {
                    AssociationConstraint associationConstraint= (AssociationConstraint) o ;
                    Map<String, LinkedHashMap<Object, ObjectAssociation>> con2 = associationConstraint.getConections();
                    System.out.println("association");
                    con2.forEach((key, value) ->{
                        System.out.println(key + ":" + value + "  " );
                        System.out.println("Key");
                        System.out.println(value);
                        LinkedHashMap<Object, ObjectAssociation> linkedHashMap =value;
                        System.out.println("List");
                        System.out.println(linkedHashMap);
                        System.out.println("IN LINKED HASH MAP");
                        linkedHashMap.forEach((key1, value1) ->{
                            System.out.println(key1 + ":" + value1 + "  " );
                            list.add((Doctor) key1);
                            System.out.println("added to list" + (Doctor) key1);

                    } );



                });}
            }catch (Exception e){
                System.out.println("BŁAD W POBIERANIU");
            }

            for (Object object : list) {
                if (object instanceof Doctor) {
                    doctors.add((Doctor) object);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }           // }

        return doctors;
    }



    public static MedicalSpecialist getMedicalSpecialistFor(String specialization)throws Exception{

        switch (specialization){


            case "Cardiologist":
                return MedicalSpecialist.Cardiologist;

            case "Dermatologist":
                return MedicalSpecialist.Dermatologist;

            case "Gynaecologist":
                return MedicalSpecialist.Gynaecologist;

            case "Internist":
                return MedicalSpecialist.Internist;

            case "Neurologist":
                return MedicalSpecialist.Neurologist;

            case "FamilyDoctor":
                return MedicalSpecialist.FamilyDoctor;

            default:
                throw  new Exception("No medicalspecialist valu for " + specialization);
        }

    }


    public static MedicalSpecialist[] getAllSpecializations(){
        return MedicalSpecialist.class.getEnumConstants();
    }

    public static Map< String, MedicalSpecialist> getSpecializationsDicrionary(){
        MedicalSpecialist [] medicalSpecialists= MedicalSpecialist.class.getEnumConstants();
        Map<String,MedicalSpecialist > specializationDictionary= new HashMap<>();
        specializationDictionary.put("Kardiolog",MedicalSpecialist.Cardiologist);
        specializationDictionary.put( "Dermatolog",MedicalSpecialist.Dermatologist);
        specializationDictionary.put("Ginekolog",MedicalSpecialist.Gynaecologist);
        specializationDictionary.put("Internista",MedicalSpecialist.Internist);
        specializationDictionary.put("Neurolog",MedicalSpecialist.Neurologist);
        specializationDictionary.put("Lekarz rodzinny",MedicalSpecialist.FamilyDoctor);
        return  specializationDictionary;
    }




    @Override
    public String toString() {
        return "Doctor{" + super.toString() +
                "PWZ='" + PWZ + '\'' +
                '}';
    }

}
