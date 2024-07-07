package service;

import dao.TicketDao;
import entity.Client;
import entity.Planet;
import entity.Ticket;
import model.TicketDTO;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class TicketCrudService {

    private TicketDao ticketDao = new TicketDao();

    public TicketCrudService() {
    }

    public TicketDTO findTicket(long id) {
        Ticket entity = ticketDao.findById(id);
        return toDto(entity);
    }

    public TicketDTO createTicket(long clientId, String fromPlanetId, String toPlanetId) {
        Ticket entity = ticketDao.create(new Timestamp(System.currentTimeMillis()), new Client(clientId), new Planet(fromPlanetId), new Planet(toPlanetId));
        return toDto(entity);
    }

    public void updateTicket(TicketDTO ticket) {
        Ticket entity = toEntity(ticket);
        ticketDao.update(entity);
    }

    public static Ticket toEntity(TicketDTO dto) {
        Client client = ClientCrudService.toEntity(dto.getClient());
        Planet fromPlanet = PlanetCrudService.toEntity(dto.getFromPlanet());
        Planet toPlanet = PlanetCrudService.toEntity(dto.getToPlanet());
        return new Ticket(dto.getId(), dto.getCreatedAt(), client, fromPlanet, toPlanet);
    }

    public void deleteTicket(long id) {
        Ticket entity = new Ticket(id);
        ticketDao.delete(entity);
    }

    public List<TicketDTO> findAllTicket() {
        List<Ticket> entityList = ticketDao.findAll();
        return toDtoList(entityList);
    }


    private List<TicketDTO> toDtoList(List<Ticket> entitiesList) {
        return entitiesList.stream().map(TicketCrudService::toDto).collect(Collectors.toList());
    }

    public static TicketDTO toDto(Ticket entity) {
        if (entity == null) {
            return null;
        }
        TicketDTO dto = new TicketDTO();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreated_at());
        dto.setClient(ClientCrudService.toDto(entity.getClient()));
        dto.setFromPlanet(PlanetCrudService.toDto(entity.getFromPlanet()));
        dto.setToPlanet(PlanetCrudService.toDto(entity.getToPlanet()));
        return dto;
    }
}
