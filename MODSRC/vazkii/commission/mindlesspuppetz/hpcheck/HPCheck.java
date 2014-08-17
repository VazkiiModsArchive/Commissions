package vazkii.commission.mindlesspuppetz.hpcheck;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "HPCheck", name = "HPCheck", version = "1.0")
public class HPCheck {

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onDamageTaken(LivingHurtEvent event) {
		if(event.entity instanceof EntityPlayer && ((EntityPlayer) event.entity).getCommandSenderName().equals((Minecraft.getMinecraft().thePlayer.getCommandSenderName()))) {
			Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("HP Lost: " + event.ammount + " from " + event.source.damageType + " (" + event.source.getEntity() + ")"));
			event.setCanceled(true);
		}
	}
	
}
