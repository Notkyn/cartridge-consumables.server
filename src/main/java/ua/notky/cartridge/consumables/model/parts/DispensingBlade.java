package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dispensing_blade")
public class DispensingBlade extends AbstractNameEntity {
    private static final long serialVersionUID = 3198359677083935885L;
}
