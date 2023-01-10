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
        List<MiejsceEntity> listaMiejsc = miejsceRepozytorium.getAll();
        Date date = new Date();
        for(int i = 0; i<listaMiejsc.size(); i++){
            List<RezerwacjaEntity> rezerwacja = listaMiejsc.get(i).getRezerwacje();
            for(int j = 0; j<rezerwacja.size(); j++){
                String imieUzytkowanika =  rezerwacja.get(j).getUzytkownik();
                Date startRezerwacji = rezerwacja.get(j).getStart();
                if(imie.equals(imieUzytkowanika) && startRezerwacji.after(date)){
                    rezerwacjeDoUsuniecia.add(rezerwacja.get(j));
                    listaMiejsc.remove(rezerwacja.get(j));
                    rezerwacjaRepozytorium.usuniecieZBazyDanych(rezerwacja.get(j));
                }
            }
        }
        return rezerwacjeDoUsuniecia;
    }


}
