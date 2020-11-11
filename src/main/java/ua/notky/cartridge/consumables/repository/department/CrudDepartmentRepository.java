package ua.notky.cartridge.consumables.repository.department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.Department;

public interface CrudDepartmentRepository extends JpaRepository<Department, Integer> {

    @Modifying
    @Query(value = "DELETE FROM Department d WHERE d.id=?1")
    int delete(int id);

    @Query("SELECT d FROM Department d "
            + "JOIN FETCH d.cartridge "
            + " WHERE d.id=?1")
    Department getWithCartridge(int id);
}
