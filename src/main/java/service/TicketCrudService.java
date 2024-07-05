package service;

import dao.TicketDao;
import entity.Ticket;
import model.ClientDTO;
import model.TicketDTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class TicketCrudService {

    private TicketDao ticketDao = new TicketDao();

    public TicketCrudService(){
    }

    public TicketDTO findTicket(long id) {
        Ticket entity = ticketDao.findById(id);
        if( entity == null) {
            return null;
        }
        TicketDTO dto = new TicketDTO();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreated_at());
        dto.setFromPlanetId(entity.getFrom_planet_id());
        dto.setToPlanetId(entity.getTo_planet_id());
        return dto;
    }

    public TicketDTO createTicket(long clientId, String fromPlanetId, String toPlanetId) {
        Ticket entity = ticketDao.create(new Timestamp(System.currentTimeMillis()), clientId, fromPlanetId, toPlanetId);
        if (entity == null) {
            return null;
        }
        TicketDTO dto = new TicketDTO();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreated_at());
        dto.setFromPlanetId(entity.getFrom_planet_id());
        dto.setToPlanetId(entity.getTo_planet_id());
        return dto;
    }

    public void updateTicket(TicketDTO ticket) {
        Ticket entity = new Ticket(ticket.getId(), ticket.getCreatedAt(), ticket.getClientId(),  ticket.getFromPlanetId(), ticket.getToPlanetId());
        ticketDao.update(entity);
    }

    public void deleteTicket(long id) {
        Ticket entity = new Ticket(id);
        ticketDao.delete(entity);
    }

    public List<TicketDTO> findAllTicket() {
        List<Ticket> entityList = ticketDao.findAll();
        return toDtoList(entityList);
    }

    public List<TicketDTO> findClientTickets(ClientDTO client) {
        List<Ticket> entityList = ticketDao.findClientTickets(client.getId());
        return toDtoList(entityList);
    }

    private List<TicketDTO> toDtoList(List<Ticket> entitiesList) {
        return entitiesList.stream().map(e -> {
            TicketDTO dto = new TicketDTO();
            dto.setId(e.getId());
            dto.setCreatedAt(e.getCreated_at());
            dto.setFromPlanetId(e.getFrom_planet_id());
            dto.setToPlanetId(e.getTo_planet_id());
            return dto;
        }).collect(Collectors.toList());
    }
}
