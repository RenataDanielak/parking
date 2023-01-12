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

    public void usuniecieZBazyDanych (RezerwacjaEntity rezerwacja){
        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        session.delete(rezerwacja);

        //zakonczenie transakcji
        session.getTransaction().commit();
    }

    public int usunieteRezerwacje(String imie){
        Date date = new Date();

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        NativeQuery query = session.createNativeQuery("delete from rezerwacja where uzytkownik = :imie and od > :data");
        query.setParameter("data", date, TimestampType.INSTANCE);
        query.setParameter("imie", imie);
        query.addEntity(RezerwacjaEntity.class);
        int deletedRows = query.executeUpdate();

        //zakonczenie transakcji
        session.getTransaction().commit();

        return deletedRows;
    }


}
