import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args){


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        ParkingSerwis parkingSerwis = (ParkingSerwis) context.getBean("parkingSerwis");

        Scanner scanner=new Scanner(System.in);
        int wybor = 0;

        while(wybor != 4) {
            System.out.println("WYBIERZ CO CHCESZ ZROBIC");
            System.out.println("1-ZAREZERWUJ MIEJSCE");
            System.out.println("2-ZWOLNIJ MIEJSCE");
            System.out.println("3-SPRAWDZ ZAJETOSC PARKINGU");
            System.out.println("4-ZAKONCZ PROGRAM");
            wybor = scanner.nextInt();
            if (wybor == 1) {
                System.out.println("Podaj dzien rezerwacji");
                String startDzien = scanner.next();
                System.out.println("Podaj miesiac rezerwacji");
                String startMiesiac = scanner.next();
                System.out.println("Podaj godzine poczatku rezerwacji");
                String startGodzina = scanner.next();
                System.out.println("Podaj minute poczatku rezerwacji");
                String startMinuta = scanner.next();
                System.out.println("Podaj godzine konca rezerwacji");
                String koniecGodzina = scanner.next();
                System.out.println("Podaj minute konca rezerwacji");
                String koniecMinuta = scanner.next();
                System.out.println("Podaj swoje imie");
                String imie = scanner.next();
                Date poczatekRezerwacji = null;
                Date koniecRezerwacji = null;
                try {
                    poczatekRezerwacji = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-"+ startMiesiac + "-" + startDzien + " " + startGodzina + ":" + startMinuta + ":00");
                    koniecRezerwacji = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-"+ startMiesiac + "-" + startDzien + " " + koniecGodzina + ":" + koniecMinuta + ":00");
                    Integer numerMiejsca = parkingSerwis.zarezerwujMiejsce(poczatekRezerwacji, koniecRezerwacji, imie);
                    if (numerMiejsca != null) {
                        System.out.println("Zarezerwowano miejsce " + numerMiejsca);

                    } else {
                        System.out.println("Nie ma juz wolnych miejsc");
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Wprowadzono niepoprawne dane, wprowadz ponownie");
                    wybor = 1;
                }
            }
            else if (wybor==2) {
                System.out.println("Podaj swoje imie");
                String imie = scanner.next();
                boolean zwolnioneRezerwacje = parkingSerwis.zwolnioneRezerwacje(imie);
                if(zwolnioneRezerwacje){
                    System.out.println("Zwolniono wszystkie miejsca zarezerwowane przez uzytkownika");
                } else {
                    System.out.println("Brak rezerwacji do zwolnienia");
                }
            }
            else if(wybor == 3){
                System.out.println("Podaj dzien, ktory chcesz sprawdzic");
                String dzien = scanner.next();
                System.out.println("Podaj miesiac, ktory chcesz sprawdzic");
                String miesiac = scanner.next();
                Date zajetoscParkinguStart = null;
                Date zajetoscParkinguKoniec = null;
                try {
                    zajetoscParkinguStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-"+ miesiac + "-" + dzien + " " + "00:00:00");
                    zajetoscParkinguKoniec = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-" + miesiac + "-" + dzien + " " + "23:59:00");
                    List<RezerwacjaEntity> wypiszStatus = parkingSerwis.listaZajetychRezerwacji(zajetoscParkinguStart, zajetoscParkinguKoniec);
                    if (wypiszStatus == null) {
                        System.out.println("Tego dnia nie wykonano jeszcze żanych rezerwacji");


                    } else {
                        System.out.println("Rezerwacje wykonane tego dnia " + wypiszStatus);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Wprowadzono niepoprawne dane, wprowadz ponownie");
                    wybor = 1;
                }
            }
            if (wybor == 4) {
                System.out.println("Program zakonczono");
            }
        }
    }
}
