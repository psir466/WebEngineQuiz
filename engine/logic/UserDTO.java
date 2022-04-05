package engine.logic;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

public class UserDTO {


    // @Email laisse passer des Email test@test ???? sans .com
    @Email
    @NotBlank
    // obliger d'utiliser @Pattern !!!!!!!!!!!!!
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 5)
    private String password;

    public UserDTO() {
    }

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
