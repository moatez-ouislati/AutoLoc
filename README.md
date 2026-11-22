# AutoLoc 

Gestion de **location de véhicules** (agences, véhicules, clients, réservations, contrats, maintenances), développée avec **Spring Boot** et **MySQL** dans le cadre du module *UP ASI* (ESPRIT, 26-27).

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-informational)

<img width="1920" height="1080" alt="stylish-elegant-woman-car-salon" src="https://github.com/user-attachments/assets/06cc7d3e-321b-43d5-accb-8692ce52a784" />


## Fonctionnalités

- CRUD sur les agences, employés, véhicules, équipements, clients, réservations, contrats et maintenances ; consultation des paiements.
- Affectation d'un équipement à un véhicule et d'une maintenance à un véhicule (DTO validés).
- Requêtes croisées : véhicules par contrat ou maintenance, contrats et maintenances par véhicule.
- Documentation interactive Swagger UI et réponses d'erreur homogènes (400, 404, 409).

## Stack technique

| Domaine | Technologie |
|---|---|
| Langage | Java 17 |
| Framework | Spring Boot 4.1.1 (Web MVC, Data JPA, Validation) |
| Base de données | MySQL, Hibernate |
| Mapping | MapStruct 1.6.3, Lombok |
| Documentation | springdoc-openapi 3.0.2 (Swagger UI) |
| Build | Maven (wrapper `mvnw`) |

## Démarrage rapide

**Prérequis** : JDK 17+, MySQL démarré sur le port `3306`, Git.

```bash
git clone https://github.com/moatez-ouislati/AutoLoc.git
cd AutoLoc/autoloc-api
./mvnw spring-boot:run          # Windows : mvnw.cmd spring-boot:run
```

La base `autoloc_db` est créée automatiquement. Trois véhicules de démonstration sont insérés au premier démarrage.

| Ressource | URL |
|---|---|
| Swagger UI | http://localhost:8080/api/swagger-ui.html |
| OpenAPI (JSON) | http://localhost:8080/api/v3/api-docs |

### Configuration

`src/main/resources/application.properties` :



> Configuration de **développement** uniquement. En production : identifiants externalisés (variables d'environnement), `ddl-auto=validate` et migrations Flyway.

## Architecture

```text
src/main/java/tn/esprit/autoloc
├── domain/        Entités JPA et énumérations
├── repository/    Interfaces Spring Data JPA
├── service/       Logique métier (I…Service / …ServiceImpl)
├── exception/     Exceptions métier
└── web/
    ├── controller/  Contrôleurs REST et gestion globale des erreurs
    ├── dto/         Objets de transfert validés (XxxDTO)
    └── mapper/      Mappers MapStruct
```

```text
Controller (DTO + @Valid) ─► Service ─► Repository ─► MySQL
                               └──────► Mapper (entité → DTO)
```

- Le contrôleur passe toujours par le service, jamais par le repository.
- Injection par constructeur, dépendances typées par interface.
- Le mapping vers les DTO s'effectue dans la transaction du service.

## Modèle de domaine


<img width="1524" height="769" alt="Modèle de domaine" src="https://github.com/user-attachments/assets/0e505217-c42a-4b66-8722-20d4103d2e9b" />


9 entités (`Agence`, `Employe`, `Vehicule`, `Equipement`, `Maintenance`, `Client`, `Reservation`, `Contrat`, `Paiement`). Toutes les associations sont en `LAZY` ; la cascade est limitée aux compositions (`Contrat` → `Paiement`, `Reservation` → `Contrat`).

## API REST

Toutes les routes sont préfixées par `/api`.

| Ressource | Endpoints |
|---|---|
| Véhicules | `GET/POST /vehicules`, `GET/PUT/DELETE /vehicules/{id}` |
| Maintenances | `GET/POST /maintenances`, `GET/PUT/DELETE /maintenances/{id}` |
| Contrats | `GET/POST /contrats`, `GET/PUT/DELETE /contrats/{id}` |
| Clients, réservations, employés, équipements | CRUD standard sur `/clients`, `/reservations`, `/employes`, `/equipements` |
| Agences | `GET /agences`, `GET /agences/{id}`, `POST /agences` |
| Paiements | `GET /paiements`, `GET /paiements/{id}` (lecture seule) |

**Affectations**

| Méthode | Route | Corps | Réponse |
|---|---|---|---|
| PUT | `/vehicules/affecter-equipement` | `{"idVehicule":1,"idEquipement":1}` | `VehiculeEquipementsDTO` |
| PUT | `/maintenances/affecter-vehicule` | `{"idMaintenance":1,"idVehicule":1}` | `MaintenanceDTO` |

**Requêtes par mots-clés**

| Méthode | Route | Retourne |
|---|---|---|
| GET | `/vehicules/by-contrat/{idContrat}` | Véhicules d'un contrat |
| GET | `/contrats/by-vehicule/{idVehicule}` | Contrats d'un véhicule |
| GET | `/maintenances/by-vehicule/{idVehicule}` | Maintenances d'un véhicule |
| GET | `/vehicules/by-maintenance/{idMaintenance}` | Véhicules d'une maintenance |

Ces routes s'appuient sur des *derived queries* Spring Data, par exemple `findDistinctByReservationsContratIdContrat` (`VehiculeRepository`).

## Validation et gestion des erreurs

Les DTO d'entrée utilisent Bean Validation (`@NotNull`, `@Positive`, …) ; les contraintes sont visibles dans Swagger. `GlobalExceptionHandler` renvoie un format unique :

| Statut | Cas |
|---|---|
| 400 | Données invalides, JSON illisible, règle de gestion violée |
| 404 | Ressource introuvable |
| 409 | Conflit métier (équipement déjà affecté) ou contrainte d'intégrité (doublon) |

```json
{
  "statut": 400,
  "message": "Données invalides",
  "erreurs": { "idVehicule": "L'identifiant du véhicule doit être strictement positif" },
  "horodatage": "2026-11-22T10:30:00"
}
```

## Tests

Les scénarios se vérifient dans Swagger UI. Ordre de préparation des données : client → réservation → contrat (avec `"reservation": {"idReservation": 1}`) → maintenance → affectation. Les captures sont rangées dans `docs/screenshots/`.

## Limites connues et évolutions

- [ ] DTO pour l'ensemble des CRUD (certains endpoints exposent encore les entités JPA)
- [ ] Mise à jour complète des champs dans les méthodes `update()`
- [ ] Tests unitaires (Mockito) et tests de contrôleurs (`@WebMvcTest`)
- [ ] Règles métier de location (disponibilité, chevauchement, calcul du montant)
- [ ] Sécurité (Spring Security) et migrations Flyway

## Auteur

**Moataz Ouesleti**
