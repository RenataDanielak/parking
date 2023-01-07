import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.TimestampType;

import java.util.*;


public class MiejsceRepozytorium {

    public List<MiejsceEntity> getWolne(Date start, Date koniec){
        //Stworzenie obiektu Configuration
        Configuration conf = new Configuration();

        //Wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");

        //Wczytanie adnotacji
        conf.addAnnotatedClass(MiejsceEntity.class);
        conf.addAnnotatedClass(RezerwacjaEntity.class);

        //Stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        //Pobranie sesji
        Session session = factory.getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        //Wczytanie wszystkich pracownikow
        NativeQuery query = session.createNativeQuery("select * from miejsce where id not in (select miejsce_id from rezerwacja where do > :start and od < :koniec) limit 1");
        query.setParameter("start", start, TimestampType.INSTANCE);
        query.setParameter("koniec", koniec, TimestampType.INSTANCE);
        query.addEntity(MiejsceEntity.class);
        List<MiejsceEntity> resultList = query.getResultList();

        //select * from miejsce where id not in (select miejsce_id from rezerwacja where do > '2022-12-10 10:00:00' and od < '2022-12-10 11:00:00');

        //zakonczenie transakcji
        session.getTransaction().commit();

        //Zamkniecie obiektu SessionFactory
        factory.close();

        return resultList;
    }


    public void mergeMiejsce (MiejsceEntity miejsce){
        //Stworzenie obiektu Configuration
        Configuration conf = new Configuration();

        //Wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");

        //Wczytanie adnotacji
        conf.addAnnotatedClass(MiejsceEntity.class);
        conf.addAnnotatedClass(RezerwacjaEntity.class);

        //Stworzenie obiektu SessionFactory
        SessionFactory factory = conf.buildSessionFactory();

        //Pobranie sesji
        Session session = factory.getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        //Wczytanie wszystkich pracownikow
        session.merge(miejsce);

        //zakonczenie transakcji
        session.getTransaction().commit();

        //Zamkniecie obiektu SessionFactory
        factory.close();

    }
}
