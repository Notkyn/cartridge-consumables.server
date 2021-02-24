package ua.notky.cartridge.consumables.web.controllers.workingday;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.WorkingDay;
import ua.notky.cartridge.consumables.service.model.workingday.WorkingDayService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public class AbstractWorkingDayController {
    private WorkingDayService service;

    List<WorkingDay> getAll(){
        log.info("Get all");
        return service.getAll();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    WorkingDay get(int id){
        log.info("Get id={}", id);
        return service.getWithDepartments(id);
    }

    void create(WorkingDay workingDay){
        log.info("Create {}", workingDay);
        checkNewBean(workingDay);
        service.create(workingDay);
    }

    void update(WorkingDay workingDay){
        log.info("Update id={}, {}",workingDay.getId(), workingDay);
        service.update(workingDay);
    }

    @Autowired
    public void setService(WorkingDayService service) {
        this.service = service;
    }
}
