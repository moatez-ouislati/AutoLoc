package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence create(Agence Agence);
    Agence findById(Long id);
    List<Agence> findAll();
    Agence update(Long id, Agence Agence);
    void deleteById(Long id);
}
