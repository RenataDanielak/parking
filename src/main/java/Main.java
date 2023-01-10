import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    //jak uzytkownik wybierze opcje numer 2 program zapyta go o imie to nalezy wyszukac wszystkie rezerwacje z przyszlosci i je usunac przy wyszukiwaniu rezerwacji program
    // powinien ignorowac wielkosc liter
    //https://www.w3schools.com/sql/func_mysql_lower.asp
    //Date date = new Date();
    //main- serwis- repozytorium


    public static void main(String[] args){

        ParkingSerwis parkingSerwis = new ParkingSerwis();

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

                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("Wprowadzono niepoprawne dane, wprowadz ponownie");
                    wybor = 1;
                }
                Integer numerMiejsca = parkingSerwis.zarezerwujMiejsce(poczatekRezerwacji, koniecRezerwacji, imie);
                if (numerMiejsca != null) {
                    System.out.println("Zarezerwowano miejsce " + numerMiejsca);

                } else {
                    System.out.println("Nie ma juz wolnych miejsc");
                }
            }
            else if (wybor==2) {
                System.out.println("Podaj swoje imie");
                String imie = scanner.next();
                List<RezerwacjaEntity> zwolnijMiejsce = parkingSerwis.zwolnioneRezerwacje(imie);
                if(zwolnijMiejsce.size()>0){
                    System.out.println("Zwolniono wszystkie miejsca zarezerwowane przez uzytkownika");
                } else {
                    System.out.println("Brak rezerwacji do zwolnienia");
                }
            }
            else if(wybor == 3){
                System.out.println("Funkcja niedostepna");
            }
            if (wybor == 4) {
                System.out.println("Program zakonczono");
            }
        }




        //Zrpbic interfejs uzytkownika jak w Parking09, dla opcji innych niz zarezerwuj niech wyswietla "Fukcja niedostepna",
        //dla zarezerwuj niech pyta o dzien, miesiąc, goddzina poczatku rezerwacki, minuta poczatku rezerwacji. godzina konca, minuta konca i imie
        // rok domyslnie bierzacy. Na koncu niech zwraca komunikat zarezerwowama miejscie X, lub brak miejsc.
        // *Jesli uzytkownik wpisze niepoprawne wartosci dane, niech wyswietli komunikat i ponownie poprosi o dane

//        MiejsceRepozytorium miejsceRepozytorium = new MiejsceRepozytorium();
//        List<MiejsceEntity> result = miejsceRepozytorium.getAll();
//
//        System.out.println(result);
//
//        MiejsceEntity miejsce = result.get(0);
//
//        RezerwacjaEntity rezerwacja = new RezerwacjaEntity();
//        rezerwacja.setUzytkownik("Lukasz");
//
//
//        Date startDate = null;
//        try {
//            startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-01-02 14:15:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        rezerwacja.setStart(startDate);
//        rezerwacja.setKoniec(new Date());
//        rezerwacja.setMiejsce(miejsce);
//
//        miejsce.getRezerwacje().add(rezerwacja);
//        miejsceRepozytorium.mergeMiejsce(miejsce);
//
//        List<MiejsceEntity> result2 = miejsceRepozytorium.getAll();
//        System.out.println(result2);







    }
}
