package il.cshaifasweng.OCSFMediatorExample;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class DatabaseAccess {
    private static Session session;

    public DatabaseAccess() {
        session = getSessionFactory().openSession();
    }

    /**
     * Define entities in database
     * @return Session Factory
     * @throws HibernateException
     */
    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(ClinicManager.class);
        configuration.addAnnotatedClass(ClinicMember.class);
        configuration.addAnnotatedClass(HospitalManager.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static <T> List<T> getAll(Class<T> object) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

    /**
     * Fetches user from the database.
     * @param username The username of the user
     * @param hashPassword The hashed password of the user
     * @return User
     */
    public User getUser(String username, String hashPassword) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        criteriaQuery.select(rootEntry).where(builder.and(builder.equal(rootEntry.get("username"), username), builder.equal(rootEntry.get("hashPassword"), hashPassword)));
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * Inserts entity into the database.
     * @param entity An entity
     * @param <T> The type of the entity
     */
    public static <T> void insertEntity(T entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    /**
     * Inserts a user into the database.
     * @param username Username
     * @param password Not encrypted password
     */
    public void createUser(String username, String password) {
        session.beginTransaction();
        User user = new User(username, password);
        session.save(user);
        session.getTransaction().commit();
    }

    /**
     * Feetches a clinic from database.
     * @param clinicName clinic's name
     * @return The clinic entity
     */
    public static Clinic getClinic(String clinicName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Clinic> criteriaQuery = builder.createQuery(Clinic.class);
        Root<Clinic> rootEntry = criteriaQuery.from(Clinic.class);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("name"), clinicName));
        Query<Clinic> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public static void setOpeningHours(Clinic clinic, LocalTime openingHours) {
        clinic.setOpeningHours(openingHours);
    }

    public static void setClosingHours(Clinic clinic, LocalTime closingHours) {
        clinic.setOpeningHours(closingHours);
    }
}
