
@Entity
@Table(name = "Questions")
public class Question implements Serializable {
    @Column(name = "questionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "question")
    protected String question;
}