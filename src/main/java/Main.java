
import model.ClientDTO;
import model.PlanetDTO;
import model.TicketDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ClientCrudService;
import service.PlanetCrudService;
import service.TicketCrudService;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static  final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Start");
        ClientCrudService clientCrudService = new ClientCrudService();
        LOGGER.info("Create client");
        ClientDTO client = clientCrudService.createClient("Oleh");
        LOGGER.info("Created {}", client);
        ClientDTO find = clientCrudService.findClient(client.getId());
        LOGGER.info("Found {}", find);
        assert client.equals(find);
        List<ClientDTO> listClient = clientCrudService.findAllClient();
        LOGGER.info("Found All size {}", listClient.size());
        assert listClient.contains(client);
        ClientDTO toUpdate = new ClientDTO();
        toUpdate.setId(client.getId());
        toUpdate.setName("Sasha");
        LOGGER.info("Try update from {} to {}", client, toUpdate);
        clientCrudService.updateClient(toUpdate);
        LOGGER.info("Updated {}", clientCrudService.findClient(toUpdate.getId()));

        LOGGER.info("Create planet");
        PlanetCrudService planetCrudService = new PlanetCrudService();
        PlanetDTO planet = planetCrudService.createPlanet("BAN1", "BANANA");
        LOGGER.info("Created {}", planet);
        PlanetDTO findPlanet = planetCrudService.findPlanet(planet.getId());
        LOGGER.info("Found {}", findPlanet);
        assert planet.equals(findPlanet);
        List<PlanetDTO> listPlanet = planetCrudService.findAllPlanet();
        LOGGER.info("Found All size {}", listPlanet.size());
        assert listPlanet.contains(planet);
        PlanetDTO toUpdatePlanet = new PlanetDTO();
        toUpdatePlanet.setId("HTP34");
        toUpdatePlanet.setName("HTTP");
        LOGGER.info("Try update from {} to {}", planet, toUpdatePlanet);
        planetCrudService.updatePlanet(toUpdatePlanet);
        LOGGER.info("Updated {}", planetCrudService.findPlanet(toUpdatePlanet.getId()));
        LOGGER.info("Try delete {}", planet);
        planetCrudService.deletePlanet(planet.getId());
        LOGGER.info("Round planet {}", planetCrudService.findPlanet(toUpdatePlanet.getId()));

        LOGGER.info("Create ticket");
        TicketCrudService ticketCrudService = new TicketCrudService();
        TicketDTO ticket = ticketCrudService.createTicket(4,"MAR1", "MOON11");
        LOGGER.info("Created {}", ticket);
        TicketDTO findTicket = ticketCrudService.findTicket(ticket.getId());
        LOGGER.info("Found {}", findTicket);
        assert ticket.equals(findTicket);
        List<TicketDTO> listTicket = ticketCrudService.findAllTicket();
        LOGGER.info("Found All size {}", listTicket.size());
        assert listTicket.contains(ticket);
        TicketDTO toUpdateTicket = new TicketDTO();
        toUpdateTicket.setId(ticket.getId());
        toUpdateTicket.setCreatedAt(Timestamp.valueOf("2023-06-16 15:37:33"));
        toUpdateTicket.setClientId(client.getId());
        toUpdateTicket.setFromPlanetId("MAR1");
        toUpdateTicket.setToPlanetId("MOON11");
        LOGGER.info("Try update from {} to {}", ticket, toUpdateTicket);
        ticketCrudService.updateTicket(toUpdateTicket);
        LOGGER.info("Updated {}", ticketCrudService.findTicket(toUpdateTicket.getId()));

        LOGGER.info("Find tickets for client {}", client);
        List<TicketDTO> clientTickets = ticketCrudService.findClientTickets(client);
        LOGGER.info("Found tickets {}", String.join(",", clientTickets.stream().map(TicketDTO::toString).toList()));

        LOGGER.info("Try delete {}", ticket);
        ticketCrudService.deleteTicket(ticket.getId());
        LOGGER.info("Found ticket {}", ticketCrudService.findTicket(toUpdateTicket.getId()));

        LOGGER.info("Try delete {}", client);
        clientCrudService.deleteClient(client.getId());
        LOGGER.info("Found client {}", clientCrudService.findClient(toUpdate.getId()));
    }
}
