package seek.clientequeryservice.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import seek.clientequeryservice.models.Cliente;
import seek.clientequeryservice.repositories.ClienteRepository;
import seek.clientequeryservice.services.ClienteService;

import java.util.List;


@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
