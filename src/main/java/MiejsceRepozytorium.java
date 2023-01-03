import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;


public class MiejsceRepozytorium {

    public List<MiejsceEntity> getAll (){
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
        List<MiejsceEntity> resultList = session.createQuery("from MiejsceEntity").getResultList();

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
