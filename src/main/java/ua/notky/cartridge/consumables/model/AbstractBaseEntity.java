package ua.notky.cartridge.consumables.model;

import lombok.*;
import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 6189444611266106785L;

    @Getter
    @Setter
    private Integer id;

    AbstractBaseEntity() {
    }

    AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AbstractBaseEntity that = (AbstractBaseEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }
}
