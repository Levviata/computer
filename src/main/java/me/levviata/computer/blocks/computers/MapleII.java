package me.levviata.computer.blocks.computers;

import me.levviata.computer.ComputerMod;
import me.levviata.computer.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.util.Objects;
import java.util.Random;

public class MapleII extends Block implements IHasModel
{

    public MapleII() {
        super(Material.ROCK);
        String name = "mapleii";
        setTranslationKey(name);
        setRegistryName(name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setHardness(4);
        setResistance(1200);
        setHarvestLevel("pickaxe", 0);

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
