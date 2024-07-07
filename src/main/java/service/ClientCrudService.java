package service;

import dao.ClientDao;
import entity.Client;
import model.ClientDTO;
import java.util.List;

public class ClientCrudService {

    private ClientDao clientDao = new ClientDao();

    public ClientCrudService() {
    }

    public ClientDTO findClient(long id) {
        Client entity = clientDao.findById(id);
        if (entity == null) return null;
        return toDto(entity);
    }

    public ClientDTO createClient(String client) {
        if (client.length() < 3 || client.length() > 200) {
            throw new IllegalArgumentException("Name must be between 2 and 1000 characters long.");
        }
        Client entity = clientDao.create(client);
        return toDto(entity);
    }

    public void updateClient(ClientDTO client) {
        clientDao.update(toEntity(client));
    }

    public void deleteClient(long id) {
        if (id < 1) {
            throw new IllegalArgumentException("Id must be between 1 and 9,223,372,036,854,775,807 digital input.");
        }
        clientDao.delete(new Client(id));
    }


    public List<ClientDTO> findAllClient() {
        List<Client> entityList = clientDao.findAll();
        return entityList.stream().map(ClientCrudService::toDto).toList();
    }

    public static ClientDTO toDto(Client entity) {
        if (entity == null) {
            return null;
        }
        ClientDTO dto = new ClientDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public static Client toEntity(ClientDTO client) {
        return new Client(client.getId(), client.getName());
    }
}
