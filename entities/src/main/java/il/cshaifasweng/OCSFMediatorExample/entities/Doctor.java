package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Doctors")
public class Doctor extends ClinicMember {
}
