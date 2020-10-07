package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "drum")
public class Drum extends AbstractNameEntity {
    private static final long serialVersionUID = 5718901225574994067L;
}
