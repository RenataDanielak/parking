import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.TimestampType;

import java.util.Date;
import java.util.List;

public class RezerwacjaRepozytorium {

    public Integer zapisanieDoBazy (RezerwacjaEntity rezerwacja){

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        session.persist(rezerwacja);

        //zakonczenie transakcji
        session.getTransaction().commit();

        return rezerwacja.getId();
    }

//    public void usuniecieZBazyDanych (RezerwacjaEntity rezerwacja){
//        //Pobranie sesji
//        Session session = DBConnection.getFactory().getCurrentSession();
//
//        //rozpoczenie transakcji
//        session.beginTransaction();
//
//        session.delete(rezerwacja);
//
//        //zakonczenie transakcji
//        session.getTransaction().commit();
//    }

    public int usunieteRezerwacje(String imieRezerwujacego){
        Date date = new Date();

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        String imie = imieRezerwujacego.toLowerCase();
        //rozpoczenie transakcji
        session.beginTransaction();

        NativeQuery query = session.createNativeQuery("delete from rezerwacja where LOWER(uzytkownik) = :imie and od > :data");
        query.setParameter("data", date, TimestampType.INSTANCE);
        query.setParameter("imie", imie);
        query.addEntity(RezerwacjaEntity.class);
        int deletedRows = query.executeUpdate();

        //zakonczenie transakcji
        session.getTransaction().commit();

        return deletedRows;
    }

    public List<RezerwacjaEntity> wypiszStatus (Date sprawdzZajetoscOd, Date sprawdzZajetoscDo){

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from rezerwacja where od > :sprawdzZajetoscOd and do < :sprawdzZajetoscDo");
        query.setParameter("sprawdzZajetoscOd", sprawdzZajetoscOd, TimestampType.INSTANCE);
        query.setParameter("sprawdzZajetoscDo", sprawdzZajetoscDo, TimestampType.INSTANCE);
        query.addEntity(RezerwacjaEntity.class);

        List<RezerwacjaEntity> resultList = query.getResultList();
        //zakonczenie transakcji
        session.getTransaction().commit();

        return resultList;
    }


}
