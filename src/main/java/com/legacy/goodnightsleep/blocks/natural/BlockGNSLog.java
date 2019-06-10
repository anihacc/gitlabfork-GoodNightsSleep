package com.legacy.goodnightsleep.blocks.natural;

import com.legacy.goodnightsleep.registry.GNSCreativeTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockGNSLog extends BlockLog
{

    public BlockGNSLog()
    {
        super();

        this.setHardness(2.0F);
        this.setStepSound(soundTypeWood);
        this.setCreativeTab(GNSCreativeTabs.blocks);
    }


    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public boolean isWood(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return 0;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registry)
	{
		this.field_150167_a = new IIcon[1];
		this.field_150166_b = new IIcon[1];

		for (int i = 0; i < this.field_150167_a.length; ++i)
		{
			this.field_150167_a[i] = registry.registerIcon(this.getTextureName() + "_side");
			this.field_150166_b[i] = registry.registerIcon(this.getTextureName() + "_top");
		}
	}
}
