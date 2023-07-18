package ar.edu.utn.frc.tup.lciii;

import java.io.*;
import lombok.Data;

@Data
public class Archivo {
    public String  read(String archivo ){

        FileReader fileReader; //leer archivo
        String text= "";
        try{

            fileReader = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fileReader);
            String t= "";
            String bfRead;
            while ((bfRead = br.readLine())!= null){

                t = t + bfRead + "\n";

            }
            text= t;
            br.close();
           }catch (IOException e){
            System.out.println("Error:" + e.getMessage());
             }
           return text;
    }
}
