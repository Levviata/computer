package me.levviata.computer.block.computer;

import me.levviata.computer.ComputerMod;
import me.levviata.computer.gui.SnakeScreen;
import me.levviata.computer.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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
    public boolean onBlockActivated(net.minecraft.world.World worldIn, net.minecraft.util.math.BlockPos pos, net.minecraft.block.state.IBlockState state,
                                    net.minecraft.entity.player.EntityPlayer playerIn, net.minecraft.util.EnumHand hand, net.minecraft.util.EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            playerIn.sendMessage(new net.minecraft.util.text.TextComponentString("MapleII block activated!"));

            Minecraft.getMinecraft().displayGuiScreen(new SnakeScreen());
        }
        return true; // Return true to indicate the interaction was handled
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
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
