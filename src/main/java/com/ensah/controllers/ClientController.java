package com.ensah.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ensah.bo.Client;
import com.ensah.dao.ClientRepository;
@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	 @GetMapping("/")
	    public List<Client> getClients() {
	        return clientRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public Client getClient(@PathVariable Long id) {
	        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
	    }

	    @PostMapping("/create")
	    public void createClient(@RequestBody Client client) throws URISyntaxException {
	       clientRepository.save(client);
	        System.out.print(client.getName());
	     
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
	        Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
	        currentClient.setName(client.getName());
	        currentClient.setEmail(client.getEmail());
	        currentClient = clientRepository.save(client);

	        return ResponseEntity.ok(currentClient);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
	        clientRepository.deleteById(id);
	        return ResponseEntity.ok().build();
	    }

}
