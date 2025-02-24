package busesProject.dtos;

import busesProject.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegister {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private Rol rol;

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return apellido;
    }


    public String getEmail() {
        return email;
    }

    @NotBlank
    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }



    public Rol getRol() {
        return rol;
    }



}
