package tripDemo.repo;

import org.hibernate.Session;
import tripDemo.db.BaseConnection;
import tripDemo.dictionaries.ServiceEnum;

public abstract class BaseRepository {
    private ServiceEnum serviceEnum;

    protected BaseRepository(ServiceEnum serviceEnum) {
        this.serviceEnum = serviceEnum;
    }

    public Session getSession() {
        return BaseConnection.getInstance().getSession(serviceEnum);
    }

    public void closeSession() {
        BaseConnection.getInstance().closeConnection(serviceEnum);
    }

    public <T> T getById(Class<T> tClass, long id) {
        Session session = getSession();
        T object = session.get(tClass, id);
        closeSession();
        return object;
    }

    public <T> T create(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.save(object);
        session.getTransaction().commit();
        closeSession();
        return object;
    }

    public <T> void delete(T object) {
        Session session = getSession();
        session.beginTransaction();
        session.remove(object);
        session.getTransaction().commit();
        closeSession();
    }
}