
package DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotBlank(message = "Please enter username")
    @Size(min = 4, max = 10)
    private String userName;

    @NotBlank(message = "Please enter name")
    @Size(min = 2, max = 20)
    private String name;

    @NotBlank(message = "Please enter password")
    @Size(min = 6)
    private String password;

    @NotBlank(message = "Please enter email")
    @Email
    private String email;

    @NotBlank(message = "Please enter phone")
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
}
