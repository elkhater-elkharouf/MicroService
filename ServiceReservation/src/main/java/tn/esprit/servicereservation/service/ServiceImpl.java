package tn.esprit.servicereservation.service;

import lombok.AllArgsConstructor;
import tn.esprit.servicereservation.entity.Service;
import tn.esprit.servicereservation.repository.ReservationRepo;
import tn.esprit.servicereservation.repository.ServiceRepo;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@org.springframework.stereotype.Service
public class ServiceImpl implements IService{

   ServiceRepo serviceRepo;
   ReservationRepo reservationRepo;

   //ReservationImpl reservationImpl;

    @Override
    public Service AddService(Service service) {
        return (this.serviceRepo.save(service));
    }

    @Override
    public Service UpdateService(int idService, Service service) {
       Service oldService=this.serviceRepo.findById(idService).orElse(null);
       if (oldService!=null){
            service.setIdService(idService);
           return this.serviceRepo.save(service);
       }
       return null;
    }

    @Override
    public void deleteService(int idService) {
        this.serviceRepo.deleteById(idService);
    }

    @Override
    public List<Service> allServices() {
        return this.serviceRepo.findAll();
    }

    @Override
    public Service findServiceById(int idService) {
        return this.serviceRepo.findById(idService).orElse(null);
    }

    @Override
    public List<Service> allServicesByOwner(int idOwner) {
        List<Service> myServices=new ArrayList<>();
        List<Service> allServices=this.serviceRepo.findAll();
            for(Service s:allServices){
                if (s.getOwner()==idOwner){
                    myServices.add(s);
                }
            }
        return myServices;
    }

    @Override
    public List<Service> UnreservedServices() {
        return this.serviceRepo.findUnreservedServices();
    }

    @Override
    public List<Service> MostReservedServices() {
        return this.serviceRepo.findTop3ByOrderByReservationsDesc();
    }
    @Override
    public Service mostReservedService() {
        List<Service> top3 = this.serviceRepo.findTop3ByOrderByReservationsDesc();
        top3.get(0);
        return top3.get(0);
    }
}
