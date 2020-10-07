package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "magnetic_shaft")
public class MagneticShaft extends AbstractNameEntity {
    private static final long serialVersionUID = -1248491681459750775L;
}
