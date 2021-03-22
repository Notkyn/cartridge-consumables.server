package ua.notky.cartridge.consumables.repository.refillcartridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.RefillCartridge;

import java.time.LocalDate;
import java.util.List;

public interface CrudRefillCartridgeRepository extends JpaRepository<RefillCartridge, Integer> {
    @Query("SELECT r.date FROM RefillCartridge r")
    List<LocalDate> getAllDates();

    @Query("SELECT r FROM RefillCartridge r JOIN FETCH r.department WHERE r.date=?1")
    List<RefillCartridge> getAllByDateWithDepartment(LocalDate date);
}
