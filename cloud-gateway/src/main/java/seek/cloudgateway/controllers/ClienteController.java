package seek.cloudgateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import seek.cloudgateway.model.dto.ClienteDTO;
import seek.cloudgateway.model.dto.EsperanzaDTO;
import seek.cloudgateway.model.dto.MetricasDTO;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ClienteController {
    @GetMapping("/{esperanza}")
    public ResponseEntity<List<EsperanzaDTO>> getAllClientesWithEsperanza(@PathVariable String esperanza) {
        RestTemplate restTemplate = new RestTemplate();
        String urlBase = "http://cliente-query-service:9020/cliente/";
        ResponseEntity<List<EsperanzaDTO>> response
                = restTemplate.exchange(urlBase + esperanza, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return ResponseEntity.ok().body(response.getBody());
    }

    @GetMapping("/metricas")
    public ResponseEntity<MetricasDTO> getAllMetricas() {
        RestTemplate restTemplate = new RestTemplate();
        String urlBase = "http://cliente-query-service:9020/cliente/metricas";
        ResponseEntity<MetricasDTO> response
                = restTemplate.getForEntity(urlBase, MetricasDTO.class);
        return ResponseEntity.ok().body(response.getBody());
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        RestTemplate restTemplate = new RestTemplate();
        String urlBase = "http://cliente-service:9010/cliente";
        ResponseEntity<ClienteDTO> response
                = restTemplate.exchange(urlBase, HttpMethod.POST, new HttpEntity<>(clienteDTO), ClienteDTO.class);
        return ResponseEntity.ok().body(response.getBody());
    }
}