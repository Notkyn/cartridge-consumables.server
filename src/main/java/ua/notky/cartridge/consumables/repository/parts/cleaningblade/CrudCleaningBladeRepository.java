package ua.notky.cartridge.consumables.repository.parts.cleaningblade;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.CleaningBlade;

public interface CrudCleaningBladeRepository extends JpaRepository<CleaningBlade, Integer> {
    @Modifying
    @Query(value = "DELETE FROM CleaningBlade c WHERE c.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT b FROM CleaningBlade b WHERE b.id=?1")
    CleaningBlade getWithCartridges(int id);
}
