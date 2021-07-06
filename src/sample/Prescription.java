package sample;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Map;

public class Prescription extends AssociationConstraint{
    private int code;
    private String PESEL;
    private String firstName;
    private String lastName;
    private Map<Map<Integer,String>,Integer> medications=new Hashtable<>();
    private Map<Integer, String> medication=new Hashtable<>();
    private LocalDate expiryDate;




    Prescription(int code, String PESEL, String firstName, String lastName, Map<Map<Integer,String>,Integer> medications, LocalDate expiryDate)throws Exception{
        super();
        this.code=code;
        if(expiryDate==null){
            throw new Exception("Expiry date cannot be null.");
        }
        this.expiryDate=expiryDate;
        this.medications=medications;
    }



    public int getCode(){
        return code;
    }



}
