package busesProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    private String nombre;
    private String password;

    public String getNombre() {
        return nombre;
    }
    public String getPassword() {
        return password;
    }


}
