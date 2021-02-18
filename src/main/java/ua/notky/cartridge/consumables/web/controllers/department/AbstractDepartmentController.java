package ua.notky.cartridge.consumables.web.controllers.department;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.service.model.department.DepartmentService;

import java.util.List;

import static ua.notky.cartridge.consumables.util.ValidationUtil.checkNewBean;

@Slf4j
public abstract class AbstractDepartmentController {
    private DepartmentService service;

    List<Department> getAll(){
        log.info("Get all");
        return service.getAllWithCartridge();
    }

    void delete(int id){
        log.info("Delete id={}", id);
        service.delete(id);
    }

    Department get(int id){
        log.info("Get id={}", id);
        return service.getWithCartridge(id);
    }

    void create(Department department){
        log.info("Create {}", department);
        checkNewBean(department);
        service.create(department);
    }

    void update(Department department){
        log.info("Update id={}, {}",department.getId(), department);
        service.update(department);
    }

    @Autowired
    public void setService(DepartmentService service) {
        this.service = service;
    }
}
