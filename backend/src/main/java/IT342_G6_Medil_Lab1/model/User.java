package IT342_G6_Medil_Lab1.model;

import jakarta.persistence.*;
import lombok.Data; // <--- ADD THIS LINE

@Entity
@Table(name = "users")
@Data // This is the symbol it couldn't find
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // Default constructor
    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}