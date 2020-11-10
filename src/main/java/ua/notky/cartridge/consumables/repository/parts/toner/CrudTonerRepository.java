package ua.notky.cartridge.consumables.repository.parts.toner;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.notky.cartridge.consumables.model.parts.Toner;

public interface CrudTonerRepository extends JpaRepository<Toner, Integer> {
    @Modifying
    @Query(value = "DELETE FROM Toner t WHERE t.id=?1")
    int delete(int id);

    @EntityGraph(attributePaths = {"cartridges"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT t FROM Toner t WHERE t.id=?1")
    Toner getWithCartridges(int id);
}
