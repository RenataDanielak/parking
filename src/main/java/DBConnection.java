import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DBConnection {

    //Stworzenie obiektu SessionFactory
    private static SessionFactory factory;

    public static SessionFactory getFactory() {
        if (factory == null){
            //Stworzenie obiektu Configuration
            Configuration conf = new Configuration();

            //Wczytanie pliku konfiguracyjnego
            conf.configure("hibernate.cfg.xml");

            //Wczytanie adnotacji
            conf.addAnnotatedClass(MiejsceEntity.class);
            conf.addAnnotatedClass(RezerwacjaEntity.class);

            factory  = conf.buildSessionFactory();
        }
        return factory;
    }

}
