/**
	@author Kamil Rzemek
*/

import java.util.*;
import java.io.*;
import java.nio.file.Paths;

public class generowanieDanych {
    public static void main(String args[]) throws IOException{
        //Pobranie imion żeńskich
        String []imionaZenskie = new String[20];
        int index = 0;
        String imie;
        String a = "a";
        Scanner odczyt = new Scanner(Paths.get("imiona.txt"), "UTF-8");
        while(odczyt.hasNext()){
            imie = odczyt.next();
            if(imie.substring(imie.length()-1).equals(a)){
                if(imie.equals("Jarema")){
                    //Wyjatek mezczyzna
                }
                else{
                    imionaZenskie[index++] = imie;
                }
            }
        }
        //Imiona zenskie mam w tablicy
        //Zamykam plik
        odczyt.close();
        //Pobieram nazwiska z pliku
        int indexNazwisk = 0;
        String []nazwiska = new String[20];
        odczyt = new Scanner(Paths.get("nazwiska.txt"), "UTF-8");
        String wiersz;
        String nazwisko;
        StringTokenizer rozdzialWiersza;
        while(odczyt.hasNext()){
            wiersz = odczyt.nextLine();
            rozdzialWiersza = new StringTokenizer(wiersz);
            nazwisko = rozdzialWiersza.nextElement().toString();
            nazwisko = rozdzialWiersza.nextElement().toString();
            nazwiska[indexNazwisk++] = nazwisko;
        }
        //Nazwiska i imiona mam w tablicach
        //Zamykam plik
        odczyt.close();
        //
        System.out.println("Podaj liczbe od 1 do 100: ");
        int N = 0;
        do {
            odczyt = new Scanner(System.in);
            N = odczyt.nextInt();
            if(N<1 || N>100){
                System.out.println("Blad. Podaj liczbe od 1 do 100: ");
            }
        } while(N<1 || N>100);
        //Losujemy kobiety do pliku
        Random losowanie = new Random();
        PrintWriter zapis = new PrintWriter("szpieg.txt");
        for(int i=0;i<N;i++){
            zapis.print(imionaZenskie[losowanie.nextInt(index)]+" ");
            zapis.print(nazwiska[losowanie.nextInt(indexNazwisk)]+" ");
            zapis.println(losowaniePeselu());
        }
        zapis.close();
    }

    public static String losowaniePeselu(){
        String pesel="";
        Random losowanie = new Random();
        //PESEL TO ROK MIESIAC DZIEN I 5 CYFR
        Integer rok = losowanie.nextInt(77)+23;
        String [] miesiace = {
                "01","02","03","04","05","06",
                "07","08","09","10","11","12"};
        Integer dzien = (losowanie.nextInt(31)+1);
        pesel+= rok.toString();
        pesel+= miesiace[losowanie.nextInt(12)];
        pesel+= dzien.toString();
        Integer cyfra;
        for(int i=0;i<5;i++){
            cyfra = losowanie.nextInt(10);
            pesel+= cyfra.toString();
        }
        return pesel;
    }
}
