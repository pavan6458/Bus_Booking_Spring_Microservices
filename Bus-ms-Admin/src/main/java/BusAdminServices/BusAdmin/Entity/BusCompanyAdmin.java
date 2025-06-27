package BusAdminServices.BusAdmin.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "phone",columnNames ="companyPhone" )
        }
)
public class BusCompanyAdmin implements Serializable {
    @Id
    private Integer id;
    private String companyName;
    private String companyEmail;

    private String companyPhone;
    private String passwordHash;
    private String role;
    @CreationTimestamp
    private Date Created;
    @UpdateTimestamp
    private Date updated;

    @OneToMany(mappedBy = "busAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BusOperator> busOperators = new ArrayList<>();
    @OneToMany(mappedBy = "busCompanyAdmin",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Bus> busList = new ArrayList<>();

}
