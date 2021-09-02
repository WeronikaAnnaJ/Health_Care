package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum MedicalSpecialization {
    Cardiologist,
    Dermatologist,
    Gynaecologist,
    Internist,
    Neurologist,
    FamilyDoctor
};

public class Specialization extends ObjectAssociation {

    MedicalSpecialization specialization;

    Specialization(MedicalSpecialization specialization) {
        super();
        this.specialization = specialization;
        //uprawnienia
        //obowiazki
    }

    //znajdz daneych lekarzy o danej specjalizacji z zapisanych
    @Override
    public String toString() {
        return "Specialization{" +
                "specialization=" + specialization +
                '}';
    }


    public static List<Doctor> getDoctors(Specialization specialization) throws Exception {
        ObjectAssociation[] objects = specialization.getConnections("held by");
        List<Doctor> doctors = new ArrayList<>();
        for (ObjectAssociation object : objects) {
            doctors.add((Doctor) object);
        }
        return doctors;
    }

    public static List<Specialization> getSpecializations(Doctor doctor) throws Exception {
        ObjectAssociation[] objects = doctor.getConnections("has competence");
        doctor.showConnections("has competence");
        System.out.println("TABLE LENGTH -> " + objects.length);
        List<Specialization> specializations = new ArrayList<>();
        for (ObjectAssociation object : objects) {
            specializations.add((Specialization) object);
        }
        return specializations;
    }

    //getSpecialization from Medical Spec
    public static Specialization getSpecialization(MedicalSpecialization specialization) throws Exception {
        List<ObjectLifeSpan> list = ObjectLifeSpan.getExtentForClass(Specialization.class);
        for (ObjectLifeSpan object : list) {
            Specialization obj = (Specialization) object;
            if (obj.getSpecialization().equals(specialization)) {
                return obj;
            }
        }
        return null;
    }

    public MedicalSpecialization  getSpecialization(){
        return specialization;
    }




    public static Map<String, MedicalSpecialization> getSpecializationsDicrionary() {
        MedicalSpecialization[] medicalSpecialists = MedicalSpecialization.class.getEnumConstants();
        Map<String, MedicalSpecialization> specializationDictionary = new HashMap<>();
        specializationDictionary.put("Kardiolog", MedicalSpecialization.Cardiologist);
        specializationDictionary.put("Dermatolog", MedicalSpecialization.Dermatologist);
        specializationDictionary.put("Ginekolog", MedicalSpecialization.Gynaecologist);
        specializationDictionary.put("Internista", MedicalSpecialization.Internist);
        specializationDictionary.put("Neurolog", MedicalSpecialization.Neurologist);
        specializationDictionary.put("Lekarz rodzinny", MedicalSpecialization.FamilyDoctor);
        return specializationDictionary;
    }

    public static String getStringMedicalSpecialization(MedicalSpecialization specialization) throws Exception{

        switch (specialization){

            case Cardiologist :
                return "Cardiologist";

            case Dermatologist :
                return   "Dermatologist";

            case Gynaecologist:
                return "Gynaecologist";

            case Internist:
                return "Internist";

            case Neurologist:
                return "Neurologist";

            case FamilyDoctor:
                return "FamilyDoctor";

            default:
                throw  new Exception("No medicalspecialist value ");
        }

    }

}
