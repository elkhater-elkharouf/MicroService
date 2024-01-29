package tn.esprit.servicereservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.servicereservation.service.PdfGenerator;
import tn.esprit.servicereservation.entity.Reservation;
import tn.esprit.servicereservation.service.IReservation;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="*")
public class ReservationController {

    IReservation iReservation;
    PdfGenerator pdfGenerator;

    @PostMapping("/reservation/add")
    Reservation AddReservation(@RequestBody Reservation reservation){
        return this.iReservation.AddReservation(reservation);
    }
    @PutMapping("/reservation/update/{id}")
    Reservation UpdateReservation(@PathVariable("id")int idReservation,
                                  @RequestBody Reservation reservation){
        return this.iReservation.UpdateReservation(idReservation,reservation);
    }

    @DeleteMapping("/admin/reservation/delete/{id}")
    void deleteReservation(@PathVariable("id") int idReservation){
        this.iReservation.deleteReservation(idReservation);
    }
    @GetMapping("/user/reservation/getAll")
    List<Reservation> allReservation(){
        return this.iReservation.allReservation();
    }
    @GetMapping("/reservation/getById/{id}")
    Reservation findReservationById(@PathVariable("id")int id){
        return this.iReservation.findReservationById(id);
    }
    @GetMapping("/user/reservation/myReservations/{id}")
    List<Reservation> allReservationByOwner(@PathVariable("id")int idOwner){
        return this.iReservation.allReservationByOwner(idOwner);
    }
    @GetMapping("/admin/reservation/reservationByService/{id}")
    List<Reservation> allReservationByService(@PathVariable("id")int idService){
        return this.iReservation.allReservationByService(idService);
    }

    @GetMapping("/user/reservation/exportToPdf/{id}")
    public ResponseEntity<InputStreamResource> generatePdfFile(@PathVariable("id")int idUser)
    {
        List<Reservation> reservationList=this.iReservation.allReservationByOwner(idUser);

        ByteArrayInputStream bais=pdfGenerator.generate(reservationList);
        HttpHeaders headers= new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=myreservations.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bais));
    }

}
