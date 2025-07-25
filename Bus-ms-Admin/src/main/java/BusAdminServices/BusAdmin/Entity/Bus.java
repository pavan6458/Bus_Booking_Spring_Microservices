package BusAdminServices.BusAdmin.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bus implements Serializable {
    @Id
    private Integer id;
    private String busName;
    private Integer totalSeats;
    private String busType;
    private String seatType;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operator_id", referencedColumnName = "id")
    @JsonManagedReference
    private BusOperator busOperator;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id")
    @JsonManagedReference
    private BusCompanyAdmin busCompanyAdmin;

    @CreationTimestamp
    private Date Created;
    @UpdateTimestamp
    private Date updated;

}
