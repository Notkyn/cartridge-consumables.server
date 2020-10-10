package ua.notky.cartridge.consumables.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import ua.notky.cartridge.consumables.model.AbstractBaseEntity;
import ua.notky.cartridge.consumables.util.exception.IllegalEntityException;
import ua.notky.cartridge.consumables.util.exception.NotFoundDataException;

@Slf4j
public class ValidationUtil {
    public static <T extends AbstractBaseEntity> void checkNew(T entity) {
        log.info("Check Entity is New - id must be null: [id={}]", entity.getId());
        if (!entity.isNew()) {
            throw new IllegalEntityException(entity + " must be new (id=null)");
        }
    }

    public static <T extends AbstractBaseEntity> void checkUpdated(T entity) {
        log.info("Check Entity is Updated - must have id: [id={}]", entity.getId());
        if (entity.isNew()) {
            throw new IllegalEntityException(entity + " must have id");
        }
    }

    public static <T> void checkNotNull(T object){
        log.info("Check Not Null Object=[{}]", object);
        Assert.notNull(object, "object must be not null");
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        log.info("Check Not Found With Id - [found={}], [id={}]", found, id);
        checkNotFound(found, "id=" + id);
    }

    private static void checkNotFound(boolean found, String arg) {
        log.info("Check Not Found - [found={}], [msg={}]", found, arg);
        if (!found) {
            throw new NotFoundDataException(arg);
        }
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        log.info("Check Not Found With Id - [object={}], [id={}]", object, id);
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFoundWithName(T object, String name) {
        log.info("Check Not Found With Name - [object={}], [name={}]", object, name);
        return checkNotFound(object, "name=" + name);
    }

    private static <T> T checkNotFound(T object, String msg) {
        log.info("Check Not Found - [object={}], [msg={}]", object, msg);
        checkNotFound(object != null, msg);
        return object;
    }
}
