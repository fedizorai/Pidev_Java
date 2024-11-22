package org.example;

import org.example.models.CategorieEvent;
import org.example.models.Evenement;
import org.example.services.EvenementServices;
import org.example.services.CategorieEventService;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

CategorieEventService ca=new CategorieEventService();
        EvenementServices es =new EvenementServices();
        Evenement as;
        as = new Evenement(55,6,99,"feress ","bizerte",new Date(2024,9,12));
      // es.add(as);
       // es.update(as);
        es.delete(as);


        CategorieEvent cs;
        cs=new CategorieEvent(11,"surprise","wael");
       // ca.add(cs);
        //ca.update(cs);
        //ca.delete(cs);

        System.out.println(es.recupere());
        System.out.println(ca.recupere());
    }
}