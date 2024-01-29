package tn.esprit.servicereservation.service;

import tn.esprit.servicereservation.entity.Service;

import java.util.List;

public interface IService {

    Service AddService(Service service);
    Service UpdateService(int idService, Service service);
    void deleteService(int idService);
    List<Service> allServices();

    Service findServiceById(int id);
    List<Service> allServicesByOwner(int idOwner);
    List<Service> UnreservedServices();
    List<Service> MostReservedServices();
    Service mostReservedService();
}
