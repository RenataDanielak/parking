import java.util.*;

public class ParkingSerwis {

    private MiejsceRepozytorium miejsceRepozytorium;
    private RezerwacjaRepozytorium rezerwacjaRepozytorium;

    public ParkingSerwis(){
        miejsceRepozytorium = new MiejsceRepozytorium();
        rezerwacjaRepozytorium = new RezerwacjaRepozytorium();
    }

    public Integer zarezerwujMiejsce (Date start, Date koniec, String imie) {
        List<MiejsceEntity> miejsceEntityList = miejsceRepozytorium.getWolne(start, koniec);
            if(miejsceEntityList.size()>0){
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

    public List<RezerwacjaEntity> zwolnioneRezerwacje (String imie){
        List<RezerwacjaEntity> rezerwacjeDoUsuniecia = new ArrayList<>();
        List<RezerwacjaEntity> listaRezerwacji = rezerwacjaRepozytorium.getRezerwacjeZajetePrzezUzytkownika(imie);
        if(listaRezerwacji.size()>0){
            for(int i=0; i< listaRezerwacji.size(); i++){
                rezerwacjeDoUsuniecia.add(listaRezerwacji.get(i));
                rezerwacjaRepozytorium.usuniecieZBazyDanych(listaRezerwacji.get(i));
            }
        }
        return rezerwacjeDoUsuniecia;
    }
}
