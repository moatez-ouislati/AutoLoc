package tn.esprit.autoloc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.VehiculeRepository;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class MockDataConfig {

    @Bean
    CommandLineRunner initVehicules(VehiculeRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }
            repository.saveAll(List.of(
                    Vehicule.builder()
                            .immatriculation("123 TUN 4567")
                            .marque("Renault")
                            .modele("Clio")
                            .categorie(CategorieVehicule.CITADINE)
                            .tarifJournalier(new BigDecimal("80.00"))
                            .statut(StatutVehicule.DISPONIBLE)
                            .build(),

                    Vehicule.builder()
                            .immatriculation("234 TUN 8910")
                            .marque("Peugeot")
                            .modele("508")
                            .categorie(CategorieVehicule.BERLINE)
                            .tarifJournalier(new BigDecimal("150.00"))
                            .statut(StatutVehicule.DISPONIBLE)
                            .build(),

                    Vehicule.builder()
                            .immatriculation("112 TUN 156")
                            .marque("BMW")
                            .modele("E30")
                            .categorie(CategorieVehicule.SUV)
                            .tarifJournalier(new BigDecimal("200.00"))
                            .statut(StatutVehicule.MAINTENANCE)
                            .build()
            ));
        };
    }
}