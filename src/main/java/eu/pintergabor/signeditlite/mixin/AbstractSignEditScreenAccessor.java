package eu.pintergabor.signeditlite.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;


@Mixin(AbstractSignEditScreen.class)
public interface AbstractSignEditScreenAccessor {
	@Accessor()
	SignBlockEntity getBlockEntity();
}
