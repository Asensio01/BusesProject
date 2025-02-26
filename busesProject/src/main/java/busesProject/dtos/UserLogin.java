package busesProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


}
