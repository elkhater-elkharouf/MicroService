package tn.esprit.servicereservation.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.servicereservation.entity.Service;
import tn.esprit.servicereservation.service.IService;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins="*")
public class ServiceController {

    IService iService;

    @PostMapping("/service/add")
    public Service AddService(@RequestBody Service service){
        return this.iService.AddService(service);
    }
    @PutMapping("/admin/service/update/{id}")
    public Service UpdateService(@PathVariable("id")int idService,
                                 @RequestBody Service service){
        return this.iService.UpdateService(idService,service);
    }

    @DeleteMapping("/admin/service/delete/{id}")
    void deleteService(@PathVariable("id")int idService){
        this.iService.deleteService(idService);
    }

    @GetMapping("/service/getAll")
    public List<Service> allServices(){
      return this.iService.allServices();
    }
    @GetMapping("/service/getById/{id}")
    Service findServiceById(@PathVariable("id")int id){
        return this.iService.findServiceById(id);
    }
    @GetMapping("/user/service/myServices/{id}")
    List<Service> allServicesByOwner(@PathVariable("id")int idOwner){
        return this.iService.allServicesByOwner(idOwner);
    }

    @GetMapping("/service/unreservedServices")
    List<Service> UnreservedServices(){
        return this.iService.UnreservedServices();
    }

    @GetMapping("/service/mostReservedServices")
    List<Service> MostReservedServices(){
        return this.iService.MostReservedServices();
    }

    @GetMapping("/service/oneMostReservedService")
    Service oneMostReservedService(){
        return this.iService.mostReservedService();
    }

}
