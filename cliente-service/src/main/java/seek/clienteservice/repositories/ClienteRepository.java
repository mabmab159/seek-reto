package seek.clienteservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import seek.clienteservice.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
