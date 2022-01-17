
@Entity
@Table(name = "Patient Answers")
public class Answer implements Serializable {
    @id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    protected User patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question")
    protected Question question;

    @Column(name = "answer")
    protected String answer;

}