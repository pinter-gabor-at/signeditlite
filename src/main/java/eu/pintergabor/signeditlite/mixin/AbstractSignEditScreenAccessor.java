package eu.pintergabor.signeditlite.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.world.level.block.entity.SignBlockEntity;


@Mixin(AbstractSignEditScreen.class)
public interface AbstractSignEditScreenAccessor extends ScreenAccessor {

	/**
	 * It should have been accessible outside its class ...
	 */
	@Accessor()
	SignBlockEntity getSign();
}
