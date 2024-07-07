package service;

import dao.PlanetDao;
import entity.Planet;
import model.PlanetDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PlanetCrudService {
    private PlanetDao planetDao = new PlanetDao();

    public PlanetCrudService() {

    }

    public PlanetDTO findPlanet(String id) {
        Planet entity = planetDao.findById(id);
        return toDto(entity);
    }

    public PlanetDTO createPlanet(String planetId, String planetName) {
        if (planetName.length() < 1 || planetName.length() > 500) {
            throw new IllegalArgumentException("Name must be between 1 and 500 characters long.");
        }
        if (!planetId.matches("[A-Z0-9]+")) {
            throw new IllegalArgumentException("ID must be uppercase.");
        }
        Planet entity = planetDao.create(planetId, planetName);
        if (entity == null) {
            return null;
        }
        return toDto(entity);
    }

    public void updatePlanet(PlanetDTO planet) {
        planetDao.update(toEntity(planet));
    }

    public void deletePlanet(String planetId) {
        if (planetId != null) {
            planetDao.delete(new Planet(planetId, null));
        }
    }

    public List<PlanetDTO> findAllPlanet() {
        List<Planet> entityList = planetDao.findAll();
        return entityList.stream().map(PlanetCrudService::toDto).collect(Collectors.toList());
    }

    public static PlanetDTO toDto(Planet entity) {
        if (entity == null) {
            return null;
        }
        PlanetDTO dto = new PlanetDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Planet toEntity(PlanetDTO dto) {
        return new Planet(dto.getId(), dto.getName());
    }
}
