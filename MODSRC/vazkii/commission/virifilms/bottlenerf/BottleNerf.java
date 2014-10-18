package vazkii.commission.virifilms.bottlenerf;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "BottleNerf", name = "BottleNerf", version = "1.0")
public class BottleNerf {

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onInteract(PlayerInteractEvent event) {
		ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
		if(stack != null && stack.getItem() == Items.glass_bottle && event.action == Action.RIGHT_CLICK_BLOCK ) {
			ForgeDirection dir = ForgeDirection.getOrientation(event.face);
			int x = event.x;
			int y = event.y;
			int z = event.z;
			if(event.world.getBlock(x, y, z) == Blocks.cauldron && event.world.getBlockMetadata(x, y, z) > 1)
				event.world.setBlockMetadataWithNotify(x, y, z, 1, 1 | 2);

			x = event.x + dir.offsetX;
			y = event.y + dir.offsetY;
			z = event.z + dir.offsetZ;
			if(event.world.getBlock(x, y, z) == Blocks.water) {
				event.world.setBlockToAir(x, y, z);
				--stack.stackSize;
				if(stack.stackSize == 0)
					event.entityPlayer.inventory.setInventorySlotContents(event.entityPlayer.inventory.currentItem, new ItemStack(Items.potionitem));
				else if(!event.entityPlayer.inventory.addItemStackToInventory(new ItemStack(Items.potionitem)))
					event.entityPlayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem), false);

			}
		}
	}

}
