package ua.notky.cartridge.consumables.model.parts;

import ua.notky.cartridge.consumables.model.AbstractNameEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cleaning_blade")
public class CleaningBlade extends AbstractNameEntity {
    private static final long serialVersionUID = -77875483920190370L;
}
