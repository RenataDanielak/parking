import java.util.*;

public class ParkingSerwis {

    public Integer zarezerwujMiejsce (Date start, Date koniec, String imie) {
        MiejsceRepozytorium miejsceRepozytorium = new MiejsceRepozytorium();
        List<MiejsceEntity> miejsceEntityList = miejsceRepozytorium.getAll();
        for (int i = 0; i < miejsceEntityList.size(); i++) {
            MiejsceEntity miejsce = miejsceEntityList.get(i);
            if(sprawdzRezerwacje(start, koniec, miejsce.getRezerwacje())){
                RezerwacjaEntity rezerwacja = new RezerwacjaEntity();
                rezerwacja.setMiejsce(miejsce);
                rezerwacja.setUzytkownik(imie);
                rezerwacja.setStart(start);
                rezerwacja.setKoniec(koniec);
                miejsce.getRezerwacje().add(rezerwacja);
                miejsceRepozytorium.mergeMiejsce(miejsce);
                return miejsce.getNumerMiejsca();
            }
        }
        return null;
    }
    private boolean sprawdzRezerwacje (Date start, Date koniec, List<RezerwacjaEntity> rezerwacje) {
        for(int i = 0; i<rezerwacje.size(); i++){
            if (rezerwacje.get(i) != null && (rezerwacje.get(i).getKoniec().after(start)&& rezerwacje.get(i).getStart().before(koniec))) {
                    return false;
            }
        }
        return true;
    }
}
