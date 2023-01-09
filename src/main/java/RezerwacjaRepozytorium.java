import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RezerwacjaRepozytorium {

    public Integer zapisanieDoBazy (RezerwacjaEntity rezerwacja){
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

        session.persist(rezerwacja);

        //zakonczenie transakcji
        session.getTransaction().commit();

        //Zamkniecie obiektu SessionFactory
        factory.close();

        return rezerwacja.getId();

    }

}
