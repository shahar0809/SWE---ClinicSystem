package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Questions")
public class Question implements Serializable {
    @Id
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "question")
    protected String question;

    public Question(String question) {
        this.question = question;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }
}