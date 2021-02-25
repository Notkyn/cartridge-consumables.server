package ua.notky.cartridge.consumables.repository.refillcartridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.RefillCartridge;

public interface CrudRefillCartridgeRepository extends JpaRepository<RefillCartridge, Integer> {

    @Query("SELECT r FROM RefillCartridge r JOIN FETCH r.departments WHERE r.id=?1")
    RefillCartridge getWithDepartments(int id);
}
