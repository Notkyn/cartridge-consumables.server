package ua.notky.cartridge.consumables.repository.parts.magneticshaft;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.MagneticShaft;

public interface CrudMagneticShaftRepository extends JpaRepository<MagneticShaft, Integer> {
    @Modifying
    @Query(value = "DELETE FROM MagneticShaft m WHERE m.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM MagneticShaft m WHERE m.id=?1")
    MagneticShaft getWithCartridges(int id);
}
