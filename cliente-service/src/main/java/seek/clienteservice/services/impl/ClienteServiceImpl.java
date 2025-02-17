package seek.clienteservice.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import seek.clienteservice.models.Cliente;
import seek.clienteservice.repositories.ClienteRepository;
import seek.clienteservice.services.ClienteService;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}
