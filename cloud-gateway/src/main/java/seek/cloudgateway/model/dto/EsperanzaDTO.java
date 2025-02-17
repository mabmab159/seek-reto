package seek.cloudgateway.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EsperanzaDTO  {
    private String nombre;
    private String apellido;
    private int edad;
    private LocalDate fechaNacimiento;
    private int anioEsperanza;
}