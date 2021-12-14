package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "nurses")
public class Nurse extends ClinicMember {
}
