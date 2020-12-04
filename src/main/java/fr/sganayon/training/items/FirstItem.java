package fr.sganayon.training.items;

import fr.sganayon.training.setup.ModSetup;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;

public class FirstItem extends Item {

    public FirstItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .addToolType(ToolType.PICKAXE, 5)
                .group(ModSetup.itemGroup));
        setRegistryName("firstitem");
    }
}
