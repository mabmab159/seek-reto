package seek.clienteservice.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import seek.clienteservice.dtos.ClienteDTO;
import seek.clienteservice.models.Cliente;
import seek.clienteservice.services.ClienteService;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(
                entityToDTO(clienteService.saveCliente(dtoToEntity(clienteDTO))), HttpStatus.CREATED);
    }

    public ClienteDTO entityToDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .edad(cliente.getEdad())
                .fechaNacimiento(cliente.getFechaNacimiento())
                .build();
    }

    public Cliente dtoToEntity(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .edad(clienteDTO.getEdad())
                .fechaNacimiento(clienteDTO.getFechaNacimiento())
                .build();
    }
}
