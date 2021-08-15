package com.ensah.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ensah.bo.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
