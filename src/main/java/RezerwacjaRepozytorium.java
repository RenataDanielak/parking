import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.TimestampType;

import java.util.Date;
import java.util.List;

public class RezerwacjaRepozytorium {

    private SessionFactory factory;

    public RezerwacjaRepozytorium (){
        Configuration conf = new Configuration();

        //Wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");

        //Wczytanie adnotacji
        conf.addAnnotatedClass(MiejsceEntity.class);
        conf.addAnnotatedClass(RezerwacjaEntity.class);

        //Stworzenie obiektu SessionFactory
        factory = conf.buildSessionFactory();
    }

    public Integer zapisanieDoBazy (RezerwacjaEntity rezerwacja){

        //Pobranie sesji
        Session session = factory.getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        session.persist(rezerwacja);

        //zakonczenie transakcji
        session.getTransaction().commit();

        return rezerwacja.getId();
    }

    public Integer usuniecieZBazyDanych (RezerwacjaEntity rezerwacja){
        //Pobranie sesji
        Session session = factory.getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        session.delete(rezerwacja);

        //zakonczenie transakcji
        session.getTransaction().commit();

        return rezerwacja.getId();
    }

    public List<RezerwacjaEntity> getRezerwacjeZajetePrzezUzytkownika(String imie){
        Date date = new Date();

        //Pobranie sesji
        Session session = factory.getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from rezerwacja where uzytkownik = :imie and od > :data");
        query.setParameter("data", date, TimestampType.INSTANCE);
        query.setParameter("imie", imie);
        query.addEntity(RezerwacjaEntity.class);
        List<RezerwacjaEntity> resultList = query.getResultList();

        //zakonczenie transakcji
        session.getTransaction().commit();

        return resultList;
    }


}
