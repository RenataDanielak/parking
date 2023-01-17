import java.util.*;

public class ParkingSerwis {

    private MiejsceRepozytorium miejsceRepozytorium;
    private RezerwacjaRepozytorium rezerwacjaRepozytorium;

    public ParkingSerwis(MiejsceRepozytorium miejsceRepozytorium, RezerwacjaRepozytorium rezerwacjaRepozytorium) {
        this.miejsceRepozytorium = miejsceRepozytorium;
        this.rezerwacjaRepozytorium = rezerwacjaRepozytorium;
    }

    public Integer zarezerwujMiejsce(Date start, Date koniec, String imie) {
        List<MiejsceEntity> miejsceEntityList = miejsceRepozytorium.getWolne(start, koniec);
        if (miejsceEntityList.size() > 0) {
            MiejsceEntity miejsce = miejsceEntityList.get(0);
            RezerwacjaEntity rezerwacja = new RezerwacjaEntity();
            rezerwacja.setMiejsce(miejsce);
            rezerwacja.setUzytkownik(imie);
            rezerwacja.setStart(start);
            rezerwacja.setKoniec(koniec);
            rezerwacjaRepozytorium.zapisanieDoBazy(rezerwacja);
            return miejsce.getNumerMiejsca();

        }
        return null;
    }

    public boolean zwolnioneRezerwacje(String imie) {
        int usunieteRezerwacje = rezerwacjaRepozytorium.usunieteRezerwacje(imie);
        if (usunieteRezerwacje > 0) {
            return true;
        }
        return false;
    }

    public List<RezerwacjaEntity> listaZajetychRezerwacji(Date zajetoscOd, Date zajetoscDo){
        List<RezerwacjaEntity> resultList = rezerwacjaRepozytorium.listaRezerwacji(zajetoscOd, zajetoscDo);
        return resultList;
    }
}

