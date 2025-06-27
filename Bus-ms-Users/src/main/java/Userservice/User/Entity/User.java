package Userservice.User.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.io.Serializable;
import java.util.Date;


@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "user_master")
public class User implements Serializable {
    @Id
    private Integer id;
    @Column(name = "user_name")
    private String name;
    private boolean otp;
    @Column(name = "phone_number",unique = true)
    @Size(min = 10)
    private String mobileNumber;
    public boolean profileCreated;
    @Column(name = "password_hash")
    private String profilePicture;
    private String PasswardHash;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdDate;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedDate;
    private String role;

}
