package ua.notky.cartridge.consumables.repository.parts.drum;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.Drum;

public interface CrudDrumRepository extends JpaRepository<Drum, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Drum d WHERE d.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT d FROM Drum d WHERE d.id=?1")
    Drum getWithCartridges(int id);
}
