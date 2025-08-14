package me.levviata.computer.proxy;

import me.levviata.computer.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

import java.util.Objects;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerModel(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(
                item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id)
        );
    }

    @Override
    public void registerVariantRenderer(Item item, int meta, String fileName, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, fileName), id));
    }
}
