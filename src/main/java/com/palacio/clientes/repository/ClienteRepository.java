package com.palacio.clientes.repository;

import com.palacio.clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
