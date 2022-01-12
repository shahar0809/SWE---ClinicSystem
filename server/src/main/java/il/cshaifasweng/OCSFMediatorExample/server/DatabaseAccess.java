package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
import java.time.LocalTime;
import java.util.List;

public final class DatabaseAccess {
    private static DatabaseAccess instance;
    private Session session;
    private CriteriaBuilder builder;

    DatabaseAccess() {
        session = getSessionFactory().openSession();
        builder = session.getCriteriaBuilder();
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    /**
     * Define entities in database
     *
     * @return Session Factory
     * @throws HibernateException
     */
    private SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(User.class);
//        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(ClinicManager.class);
        configuration.addAnnotatedClass(ClinicEmployee.class);
        configuration.addAnnotatedClass(HospitalManager.class);
        configuration.addAnnotatedClass(Appointment.class);
        configuration.addAnnotatedClass(ClinicMember.class);
        configuration.addAnnotatedClass(FamilyDoctor.class);
        configuration.addAnnotatedClass(ProfessionDoctor.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public <T> List<T> getAll(Class<T> object) {
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

    /**
     * Fetches user from the database.
     *
     * @param username The username of the user
     * @return User
     */
    public User getUser(String username) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("username"), username));
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     * Inserts entity into the database.
     *
     * @param entity An entity
     * @param <T>    The type of the entity
     */
    public <T> void insertEntity(T entity) {
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
    }

    /**
     * Inserts a user into the database.
     *
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
     * Inserts a Patient into the database.
     *
     * @param username Username
     * @param password Not encrypted password
     */
    public Patient createPatient(String username, String password) {
        session.beginTransaction();
        Patient patient = new Patient(username, password);
        session.save(patient);
        session.getTransaction().commit();
        return patient;
    }

    /**
     * Fetches a clinic from database.
     *
     * @param clinicName clinic's name
     * @return The clinic entity
     */
    public Clinic getClinic(String clinicName) {
        CriteriaQuery<Clinic> criteriaQuery = builder.createQuery(Clinic.class);
        Root<Clinic> rootEntry = criteriaQuery.from(Clinic.class);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("name"), clinicName));
        Query<Clinic> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    public void setOpeningHours(Clinic clinic, LocalTime openingHours) {
        session.beginTransaction();
        clinic.setOpeningHours(openingHours);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    public void setClosingHours(Clinic clinic, LocalTime closingHours) {
        session.beginTransaction();
        clinic.setClosingHours(closingHours);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    /**
     * Gets free appointments of a certain type.
     * @param clinic The clinic to search within
     * @param type The appointment type
     * @return A list of all available appointments
     */
    public List<Appointment> getFreeAppointments(Clinic clinic, AppointmentType type) {
        CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
        Root<Appointment> rootEntry = criteriaQuery.from(Appointment.class);
        criteriaQuery.where(builder.and(
                builder.equal(rootEntry.get("appointmentType"), type.toString()),
                builder.equal(rootEntry.get("isAvailable"), true)),
                builder.equal(rootEntry.get("clinic"), clinic));

        Query<Appointment> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
