package wordmem.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import javax.validation.constraints.NotNull;

// @Data
// public class Users {
//     private String username;
//     private String email;
//     private String password;
//     private boolean enabled;

//     public Users(String username, String email, String password) {
//         this.email = email;
//         this.username = username;
//         this.password = password;
//     }

//     public Users toUser(PasswordEncoder passwordEncoder) {
//         return new Users(username, email, passwordEncoder.encode(password));
//     }
// }
@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
public class Users {

    // private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "username")
    @NotNull
    private final String username;
    @Column(name = "password")
    @NotNull
    private final String password;

    @Column(name = "email")
    @NotNull
    private final String email;

    @Column(name = "enabled")
    private boolean enabled;

    public Users toUser(PasswordEncoder passwordEncoder) {
        return new Users(username, passwordEncoder.encode(password), email);
    }
}
