package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


    public class ExtendFile {

        private static final String extentFile = "Extend.ser"; //atrybut klasowy


        /**
         * Metoda umożliwiająca zapis do pliku ekstensji klasy otrzymanej za pomocą metody z klasy ObjectLifeSpam
         *
         */
        public static void writeExtends(){

            try{
                var out = new ObjectOutputStream(new FileOutputStream(extentFile));
                ObjectLifeSpan.writeExtents(out);
                out.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }





        /**
         * Metoda umożliwiająca na odczyt z pliku ekstensji klasy otrzymanej za pomocą metody z klasy ObjectLifeSpam
         *
         */

        public static void readExtends(){

            try{
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(extentFile));
                ObjectLifeSpan.readExtents(in);
            }catch(Exception e){
                e.printStackTrace();
            }
        }




    }

