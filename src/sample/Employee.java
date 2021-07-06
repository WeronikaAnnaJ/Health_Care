package sample;

import java.time.LocalDate;
import java.time.Period;
import java.util.Hashtable;
import java.util.Map;

enum CEFR{
    A1,
    A2,
    B1,
    B2,
    C1,
    C2

};

public class Employee extends Person{ //klasa abstrakcyjna dziedzicząca po klasie abstrakcyjnej Osoba

    private LocalDate employmentDate;
    private Map<String, CEFR> languages= new Hashtable<>();



    Employee(String name, String middleName, String maidenName, String lastName, String pesel, LocalDate birthDate, String phoneNumber, String emailAddress) throws Exception {
        super(name, middleName, maidenName, lastName, pesel, birthDate, phoneNumber, emailAddress);
    }



    public void setEmploymentDate(LocalDate employmentDate){
        this.employmentDate=employmentDate;
    }



    public void addLanguage(String language, CEFR level){
        if(languages.containsKey(language)){
            languages.replace(language,level);
        }else{
            languages.put(language, level);
        }
    }



    public Period getPeriodOfEmployment() throws Exception {
        if(employmentDate==null){
            throw new Exception("Employment Date value is null. Set an employment date to get period of employment. ");
        }
        return Period.between(employmentDate,LocalDate.now());
    }



    @Override
    public String toString() {
        return "Employee{" + super.toString()+
                "employmentDate=" + employmentDate +
                '}';
    }
}
