package ua.notky.cartridge.consumables.repository.cartridge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.Cartridge;

public interface CrudCartridgeRepository extends JpaRepository<Cartridge, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Cartridge c  WHERE c.id=?1")
    int delete(int id);


    @Query("SELECT c FROM Cartridge c "
            + "JOIN FETCH c.toner "
            + "JOIN FETCH c.drum "
            + "JOIN FETCH c.magneticShaft "
            + "JOIN FETCH c.primaryChargeShaft "
            + "JOIN FETCH c.cleaningBlade "
            + "JOIN FETCH c.dispensingBlade "
            + " WHERE c.id=?1")
    Cartridge getWithAllParts(int id);
/*
//    @Query("SELECT c FROM Cartridge c JOIN FETCH c.cleaningBlade")
    @Query("SELECT c FROM Cartridge c")
    Cartridge getAll();*/
}
