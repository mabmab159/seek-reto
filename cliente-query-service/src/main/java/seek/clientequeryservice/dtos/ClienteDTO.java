package seek.clientequeryservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @NotEmpty(message = "Nombre no puede ser vacio")
    private String nombre;
    @NotEmpty(message = "Apellido no puede ser vacio")
    private String apellido;
    @Min(value = 0, message = "La edad no puede ser negativa")
    private int edad;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Fecha de nacimiento no puede ser vacio")
    private LocalDate fechaNacimiento;
}
