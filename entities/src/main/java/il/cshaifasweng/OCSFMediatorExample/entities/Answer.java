package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "patientAnswers")
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "patient")
    protected User patient;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "question")
    protected Question question;

    @Column(name = "answer")
    protected String answer;

    public Answer(Patient patient, Question question, String answer) {
        this.patient = patient;
        this.question = question;
        this.answer = answer;
    }

    public Answer() {

    }
}