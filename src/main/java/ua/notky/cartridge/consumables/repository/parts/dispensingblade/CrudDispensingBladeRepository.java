package ua.notky.cartridge.consumables.repository.parts.dispensingblade;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.DispensingBlade;

public interface CrudDispensingBladeRepository extends JpaRepository<DispensingBlade, Integer> {
    @Modifying
    @Query(value = "DELETE FROM DispensingBlade d WHERE d.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM DispensingBlade d WHERE d.id=?1")
    DispensingBlade getWithCartridges(int id);
}
