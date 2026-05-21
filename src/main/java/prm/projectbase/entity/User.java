package prm.projectbase.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseEntity{

    @Column(unique = true, nullable = false)
    String userName;
        
    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "full_name")
    String fullName;

    @Builder.Default
    @Column(nullable = false)
    boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    Role role;
}
