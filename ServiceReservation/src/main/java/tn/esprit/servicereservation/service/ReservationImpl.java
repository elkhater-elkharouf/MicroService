package tn.esprit.servicereservation.service;

import lombok.AllArgsConstructor;
import tn.esprit.servicereservation.entity.Reservation;
import tn.esprit.servicereservation.entity.Service;
import tn.esprit.servicereservation.repository.ReservationRepo;
import tn.esprit.servicereservation.repository.ServiceRepo;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@org.springframework.stereotype.Service
public class ReservationImpl implements IReservation{

    ReservationRepo reservationRepo;
    ServiceRepo serviceRepo;


    @Override
    public Reservation AddReservation(Reservation reservation) {
       /* Service service= this.serviceRepo.findById(idService).orElse(null);
        reservation.setService(service);*/
        return this.reservationRepo.save(reservation);
    }

    @Override
    public Reservation UpdateReservation(int idReservation, Reservation reservation) {
        Reservation oldReservation=this.reservationRepo.findById(idReservation).orElse(null);
        if (oldReservation!=null){
            //reservation.setIdReservation(idReservation);
            oldReservation.setDateReservation(reservation.getDateReservation());
            return this.reservationRepo.save(oldReservation);
        }
        return null;
    }

    @Override
    public void deleteReservation(int idReservation) {
        this.reservationRepo.deleteById(idReservation);
    }

    @Override
    public List<Reservation> allReservation() {
        return this.reservationRepo.findAll();
    }

    @Override
    public Reservation findReservationById(int id) {
        return this.reservationRepo.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> allReservationByOwner(int idOwner) {
        List<Reservation> myReservation=new ArrayList<>();
        List<Reservation> allReservation=this.reservationRepo.findAll();
        for(Reservation r:allReservation){
            if (r.getOwner()==idOwner){
                myReservation.add(r);
            }
        }
        return myReservation;
    }

    @Override
    public List<Reservation> allReservationByService(int idService) {
        List<Reservation> serviceReservation=new ArrayList<>();
        Service service=this.serviceRepo.findById(idService).orElse(null);
        if(service!=null){
            for(Reservation r:serviceReservation){
                if(r.getService().equals(service)){
                    serviceReservation.add(r);
                }
            }
        }
        return serviceReservation;
    }



}
