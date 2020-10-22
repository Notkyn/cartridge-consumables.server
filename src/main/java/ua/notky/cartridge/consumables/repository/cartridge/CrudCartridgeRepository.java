package ua.notky.cartridge.consumables.repository.cartridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.Cartridge;

public interface CrudCartridgeRepository extends JpaRepository<Cartridge, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Cartridge c WHERE c.id=?1")
    int delete(int id);
}
