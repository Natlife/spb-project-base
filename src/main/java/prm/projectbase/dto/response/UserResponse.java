package prm.projectbase.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    int id;
    String username;
    String email;
    String fullName;
    boolean active;
    RoleResponse role;

    public UserResponse(int id, String username, String email, String fullName, boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.active = active;
    }
}
