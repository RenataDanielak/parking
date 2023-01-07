import java.util.*;

public class ParkingSerwis {

    public Integer zarezerwujMiejsce (Date start, Date koniec, String imie) {
        MiejsceRepozytorium miejsceRepozytorium = new MiejsceRepozytorium();
        List<MiejsceEntity> miejsceEntityList = miejsceRepozytorium.getWolne(start, koniec);
            if(miejsceEntityList.size()>0){
                MiejsceEntity miejsce = miejsceEntityList.get(0);
                RezerwacjaEntity rezerwacja = new RezerwacjaEntity();
                rezerwacja.setMiejsce(miejsce);
                rezerwacja.setUzytkownik(imie);
                rezerwacja.setStart(start);
                rezerwacja.setKoniec(koniec);
                miejsce.getRezerwacje().add(rezerwacja);
                miejsceRepozytorium.mergeMiejsce(miejsce);
                return miejsce.getNumerMiejsca();

            }
               return null;
        }



}
