package ua.notky.cartridge.consumables.tools.data.model.parts;

import ua.notky.cartridge.consumables.model.parts.Drum;
import ua.notky.cartridge.consumables.tools.data.AbstractModelTool;

import java.util.List;

public class DrumTool extends AbstractModelTool {
    public static final int ID_DRUM_2 = 2;
    public static final int ID_DRUM_5 = 5;

    public static Drum DRUM_1 = new Drum(1, "drum_1");
    public static Drum DRUM_2 = new Drum(ID_DRUM_2, "drum_2");
    public static Drum DRUM_3 = new Drum(3, "drum_3");
    public static Drum DRUM_4 = new Drum(4, "drum_4");
    public static Drum DRUM_5 = new Drum(ID_DRUM_5, "drum_5");

    public static List<Drum> DRUMS = List.of(DRUM_1, DRUM_2, DRUM_3, DRUM_4, DRUM_5);

    public static Drum getNew(){
        return new Drum("New Drum");
    }

    public static Drum getCopy(Drum value){
        return new Drum(value.getId(), value.getName());
    }

    public static Drum getUpdated(Drum value){
        Drum update = getCopy(value);
        update.setName("Updated Drum");
        return update;
    }

}
