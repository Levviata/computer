package me.levviata.computer.bases.computers;

import me.levviata.computer.ComputerMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Objects;
import java.util.Random;

public class GenericComputerBase extends Block implements me.levviata.computer.utils.IHasModel
{
    public GenericComputerBase(String name) {
        super(Material.ROCK);
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(5);
        setResistance(1200);
        setHarvestLevel("pickaxe", 1);

        ComputerMod.BLOCKS.add(this);
        ComputerMod.ITEMS.add(new ItemBlock(this).setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    @Override
    public int quantityDropped(Random random) {
        return 1; // Adjust as needed
    }
    @Override
    public void registerModels() {
        ComputerMod.proxy.registerModel(Item.getItemFromBlock(this), 0, "inventory");
    }
}