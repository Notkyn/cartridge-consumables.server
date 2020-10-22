package ua.notky.cartridge.consumables.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cartridge")
public class Cartridge extends AbstractNameEntity {
    private static final long serialVersionUID = 4466775734839249457L;

    public Cartridge() {
    }

    public Cartridge(String name) {
        super(name);
    }

    public Cartridge(Integer id, String name) {
        super(id, name);
    }
}
