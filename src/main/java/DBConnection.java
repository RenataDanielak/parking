import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {


    private static SessionFactory factory;

    static {
        //Stworzenie obiektu Configuration
        Configuration conf = new Configuration();

        //Wczytanie pliku konfiguracyjnego
        conf.configure("hibernate.cfg.xml");

        //Wczytanie adnotacji
        conf.addAnnotatedClass(MiejsceEntity.class);
        conf.addAnnotatedClass(RezerwacjaEntity.class);

        //Stworzenie obiektu SessionFactory
        factory = conf.buildSessionFactory();
    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }

    public static void close() {
        factory.close();
    }

}
