package com.palacio.clientes.controller;

import com.palacio.clientes.model.Cliente;
import com.palacio.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClient() {
        return ResponseEntity.ok(clienteService.getAllClientes());
    }

    @GetMapping("/{id}")
    public Optional<Cliente> findClientId (@PathVariable Long id) {
        return clienteService.findClienteById(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<Cliente> insertClient(@RequestBody Cliente cliente) {
        Cliente clienteTemporal = clienteService.saveCliente(cliente);
        try {
            return ResponseEntity.created(new URI("/api/cliente" + clienteTemporal.getId())).body(clienteTemporal);
        }catch (URISyntaxException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteTemporal = clienteService.saveCliente(cliente);
        try {
            return ResponseEntity.ok(clienteTemporal);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Cliente> deleteClient(@PathVariable Long id) {
        return clienteService.deleteCliente(id);
    }
}
