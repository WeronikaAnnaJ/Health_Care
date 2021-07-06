package sample;

import java.time.LocalDate;

public class Invoice extends AssociationConstraint{
    private LocalDate dateOfInvoicing;
    private float amount;



    Invoice(LocalDate dateOfInvoicing, float amount){
        super();
        if(dateOfInvoicing==null){
            this.dateOfInvoicing=LocalDate.now();
        }else{
            this.dateOfInvoicing=dateOfInvoicing;
        }
        this.amount=amount;
    }



}
