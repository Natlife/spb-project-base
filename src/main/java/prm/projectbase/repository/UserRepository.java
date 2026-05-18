package prm.projectbase.repository;

import org.springframework.data.jpa.repository.Query;
import prm.projectbase.dto.response.UserResponse;
import prm.projectbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("""
        SELECT new pm.projectbase.dto.response.UserResponse(
            u.id, u.userName, u.email, u.fullName, u.active
        )
        FROM User u
        WHERE LOWER(u.fullName) LIKE :name
            And LOWER(u.email) LIKE :email
    """)
    Optional<UserResponse> findByNameAndEmail(String name, String email);
}
