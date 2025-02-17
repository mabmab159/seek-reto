package seek.clientequeryservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import seek.clientequeryservice.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
