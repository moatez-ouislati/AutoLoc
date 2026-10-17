package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    Client create(Client Client);
    Client findById(Long id);
    List<Client> findAll();
    Client update(Long id, Client Client);
    void deleteById(Long id);
}
