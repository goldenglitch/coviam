import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement
public class Employee {
    String firstName;
    String lastName;
    Date dateOfBirth;
    Double experience;

    public Employee() {
    }

    public Employee(String firstName, String lastName, Date dateOfBorth, Double experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }


    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBorth() {
        return dateOfBirth;
    }

    @XmlElement
    public void setDateOfBorth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Double getExperience() {
        return experience;
    }

    @XmlElement
    public void setExperience(Double experience) {
        this.experience = experience;
    }
}


