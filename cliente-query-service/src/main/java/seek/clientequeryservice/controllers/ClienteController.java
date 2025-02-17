package seek.clientequeryservice.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seek.clientequeryservice.dtos.EsperanzaDTO;
import seek.clientequeryservice.dtos.MetricasDTO;
import seek.clientequeryservice.models.Cliente;
import seek.clientequeryservice.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/{esperanza}")
    public ResponseEntity<List<EsperanzaDTO>> getAllClientesWithEsperanza(@PathVariable String esperanza) {
        return ResponseEntity.ok(clienteService.getAllClientes()
                .stream()
                .map(cliente -> dtoToEsperanzaDTO(cliente, esperanza))
                .toList());
    }

    @GetMapping("/metricas")
    public ResponseEntity<MetricasDTO> getAllMetricas() {
        var listado = clienteService.getAllClientes();
        double promedio = listado.stream().mapToInt(p -> p.getEdad()).summaryStatistics().getAverage();
        double desviacionEstandar = listado
                .stream()
                .map(cliente -> Math.pow(promedio - cliente.getEdad(), 2)).reduce(Double::sum).get();
        var salida = Math.sqrt(desviacionEstandar / listado.size());
        return ResponseEntity.ok(new MetricasDTO(promedio, salida));
    }

    public EsperanzaDTO dtoToEsperanzaDTO(Cliente cliente, String esperanza) {
        return EsperanzaDTO.builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .edad(cliente.getEdad())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .anioEsperanza(cliente.getFechaNacimiento().getYear() + Integer.parseInt(esperanza))
                .build();
    }
}
