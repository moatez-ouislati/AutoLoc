package tn.esprit.autoloc.service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.ClientRepository;

@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements IClientService {
    private final ClientRepository ClientRepository;
    @Override
    public Client create(Client Client) {
        if (Client.getIdClient() != null) {
            throw new IllegalArgumentException(
                    "Un nouveau Client ne doit pas avoir d'identifiant");
        }
        return ClientRepository.save(Client);
    }
    @Override
    public Client findById(Long id) {
        return ClientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
    }
    @Override
    public List<Client> findAll() {

        return ClientRepository.findAll();
    }
    @Override
    public Client update(Long id, Client Client) {
        Client existant = findById(id);
        return ClientRepository.save(existant);
    }
    @Override
    public void deleteById(Long id) {
        if (!ClientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client", id);
        }
        ClientRepository.deleteById(id);
    }
}