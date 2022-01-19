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
import java.util.UUID;

public final class DatabaseAccess {
    private static DatabaseAccess instance;
    private Session session;
    private CriteriaBuilder builder;

    private DatabaseAccess() {
        session = getSessionFactory().openSession();
        builder = session.getCriteriaBuilder();
    }

    public static DatabaseAccess getInstance() {
        if (instance == null) {
            instance = new DatabaseAccess();
        }
        return instance;
    }

    public <T> void flushSession(T obj) {
        session.merge(obj);
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
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(ClinicManager.class);
        configuration.addAnnotatedClass(ClinicEmployee.class);
        configuration.addAnnotatedClass(HospitalManager.class);
        configuration.addAnnotatedClass(Appointment.class);
        configuration.addAnnotatedClass(ClinicMember.class);
        configuration.addAnnotatedClass(FamilyDoctor.class);
        configuration.addAnnotatedClass(ProfessionDoctor.class);
        configuration.addAnnotatedClass(ProfessionDoctorAppointment.class);
        configuration.addAnnotatedClass(FamilyDoctorAppointment.class);
        configuration.addAnnotatedClass(ChildrenDoctorAppointment.class);
        configuration.addAnnotatedClass(NurseAppointment.class);
        configuration.addAnnotatedClass(CovidTestAppointment.class);
        configuration.addAnnotatedClass(CovidVaccineAppointment.class);
        configuration.addAnnotatedClass(FluVaccineAppointment.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Answer.class);

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

    public User getUserByToken(UUID token) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> rootEntry = criteriaQuery.from(User.class);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("token"), token));
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
        try {
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Inserts a Patient into the database.
     *
     * @param username Username
     * @param password Not encrypted password
     */
    public Patient createPatient(String username, String password, int age, Clinic clinic) {
        Patient patient = null;
        session.beginTransaction();
        try {
            patient = new Patient(username, password, age, clinic);
            patient.refreshToken();
            session.save(patient);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return patient;
    }

    public boolean hasAnsweredCovidQuestionnaire(User user) {
        CriteriaQuery<Answer> criteriaQuery = builder.createQuery(Answer.class);
        Root<Answer> rootEntry = criteriaQuery.from(Answer.class);
        criteriaQuery.select(rootEntry).where(builder.equal(rootEntry.get("patient"), user));
        Query<Answer> query = session.createQuery(criteriaQuery);
        return query.getResultList().size() == 3;
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
        try {
            clinic.setOpeningHours(openingHours);
            session.save(clinic);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public void setClosingHours(Clinic clinic, LocalTime closingHours) {
        session.beginTransaction();
        try {
            clinic.setClosingHours(closingHours);
            session.save(clinic);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    /**
     * Updates existing appointment in DB
     * @param appointment Updated appointment
     */
    public void updateAppointment(Appointment appointment) {
        session.beginTransaction();
        try {
            session.merge(appointment);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public List<Appointment> getUserAppointment(Patient patient) {
        CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
        Root<Appointment> rootEntry = criteriaQuery.from(Appointment.class);

        criteriaQuery.where(builder.and(
                        builder.equal(rootEntry.get("isAvailable"), false),
                        builder.equal(rootEntry.get("patient"), patient)));

        Query<Appointment> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * Gets free appointments of a certain type.
     * @param clinic The clinic to search within
     * @return A list of all available appointments
     */
    public <T extends Appointment> List<T> getFreeAppointments(Class<T> object, Clinic clinic, AppointmentType type) {
        CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
        Root<T> rootEntry = criteriaQuery.from(object);

        criteriaQuery.where(builder.and(
                builder.equal(rootEntry.get("isAvailable"), true),
                builder.equal(rootEntry.get("clinic"), clinic)),
                builder.equal(rootEntry.get("type"), type));

        Query<T> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public void setCovidTestStartHour(Clinic clinic, LocalTime startHour) {
        session.beginTransaction();
        clinic.setCovidTestStartHour(startHour);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    public void setCovidTestEndHour(Clinic clinic, LocalTime endHour) {
        session.beginTransaction();
        clinic.setCovidTestEndHour(endHour);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    public LocalTime getCovidTestStartHour(Clinic clinic) { return clinic.getCovidTestStartHour();}

    public LocalTime getCovidTestEndHour(Clinic clinic) {
        return clinic.getCovidTestEndHour();
    }

    public void setCovidVaccineStartHour(Clinic clinic, LocalTime startHour) {
        session.beginTransaction();
        clinic.setCovidVaccineStartHour(startHour);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    public void setCovidVaccineEndHour(Clinic clinic, LocalTime endHour) {
        session.beginTransaction();
        clinic.setCovidVaccineEndHour(endHour);
        session.save(clinic);
        session.flush();
        session.getTransaction().commit();
    }

    public LocalTime getCovidVaccineStartHour(Clinic clinic) {
        return clinic.getCovidVaccineStartHour();
    }

    public LocalTime getCovidVaccineEndHour(Clinic clinic) {
        return clinic.getCovidVaccineEndHour();
    }

    public LocalTime getClinicOpeningHour(Clinic clinic) { return clinic.getOpeningHours();}

    public LocalTime getClinicClosingHour(Clinic clinic) { return clinic.getClosingHours();}

    public void refreshUserToken(User user) {
        session.beginTransaction();
        user.refreshToken();
        session.save(user);
        session.flush();
        session.getTransaction().commit();
    }

    public List<Appointment> getUnavailableAppointments() {
        CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
        Root<Appointment> rootEntry = criteriaQuery.from(Appointment.class);
        criteriaQuery.where(builder.equal(rootEntry.get("isAvailable"), false));
        Query<Appointment> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<Appointment> getMemberAppointments(ClinicMember member) {
        CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
        Root<Appointment> rootEntry = criteriaQuery.from(Appointment.class);

        criteriaQuery.where(builder.equal(rootEntry.get("member"), member));

        Query<Appointment> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
