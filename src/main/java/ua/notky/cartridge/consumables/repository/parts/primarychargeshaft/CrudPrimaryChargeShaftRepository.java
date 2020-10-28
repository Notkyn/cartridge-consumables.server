package ua.notky.cartridge.consumables.repository.parts.primarychargeshaft;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.PrimaryChargeShaft;

public interface CrudPrimaryChargeShaftRepository extends JpaRepository<PrimaryChargeShaft, Integer> {
    @Modifying
    @Query(value = "DELETE FROM PrimaryChargeShaft p WHERE p.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM PrimaryChargeShaft p WHERE p.id=?1")
    PrimaryChargeShaft getWithCartridges(int id);
}
