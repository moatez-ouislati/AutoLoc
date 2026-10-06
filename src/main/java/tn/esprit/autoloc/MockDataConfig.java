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
                    new Vehicule(null, "123 TUN 4567", "Renault", "Clio",
                            CategorieVehicule.CITADINE, new BigDecimal("80.00"),
                            StatutVehicule.DISPONIBLE),
                    new Vehicule(null, "234 TUN 8910", "Peugeot", "508",
                            CategorieVehicule.BERLINE, new BigDecimal("150.00"),
                            StatutVehicule.DISPONIBLE),
                    new Vehicule(null, "112 TUN 156", "BMW", "E30",
                            CategorieVehicule.SUV, new BigDecimal("200.00"),
                            StatutVehicule.MAINTENANCE)
            ));

        };
    }

}
