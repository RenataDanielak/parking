import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.TimestampType;

import javax.persistence.Query;
import java.util.*;


public class MiejsceRepozytorium {


    public List<MiejsceEntity> getWolne(Date start, Date koniec){

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        NativeQuery query = session.createNativeQuery("select * from miejsce where id not in (select miejsce_id from rezerwacja where do > :start and od < :koniec) limit 1");
        query.setParameter("start", start, TimestampType.INSTANCE);
        query.setParameter("koniec", koniec, TimestampType.INSTANCE);
        query.addEntity(MiejsceEntity.class);
        List<MiejsceEntity> resultList = query.getResultList();

        //zakonczenie transakcji
        session.getTransaction().commit();

        return resultList;
    }


    public void mergeMiejsce (MiejsceEntity miejsce){

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        session.merge(miejsce);

        //zakonczenie transakcji
        session.getTransaction().commit();
    }

    public List<MiejsceEntity> getAll(){

        //Pobranie sesji
        Session session = DBConnection.getFactory().getCurrentSession();

        //rozpoczenie transakcji
        session.beginTransaction();

        List<MiejsceEntity> resultList = session.createQuery("from MiejsceEntity").getResultList();

        //zakonczenie transakcji
        session.getTransaction().commit();

        return resultList;
    }


}
