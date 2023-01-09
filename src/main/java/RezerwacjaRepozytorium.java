import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

}
