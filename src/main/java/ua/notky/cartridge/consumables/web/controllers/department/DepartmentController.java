package ua.notky.cartridge.consumables.web.controllers.department;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.notky.cartridge.consumables.model.Department;
import ua.notky.cartridge.consumables.util.constant.ConstUrl;

import java.util.List;

@RestController
@RequestMapping(value = ConstUrl.UI_DEPARTMENT, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentController extends AbstractDepartmentController {
    @Override
    @GetMapping
    List<Department> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(ConstUrl.ID)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(ConstUrl.ID)
    Department get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void createOrUpdate(@Validated @RequestBody Department department) {
        if(department.isNew()) {
            super.create(department);
        } else {
            super.update(department);
        }
    }
}
