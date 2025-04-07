package eu.pintergabor.signeditlite.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;


@Mixin(Screen.class)
public interface ScreenAccessor {

	/**
	 * It should have been public ...
	 */
	@Invoker()
	@SuppressWarnings("UnusedReturnValue")
	<T extends GuiEventListener & Renderable & NarratableEntry>
	T invokeAddRenderableWidget(T widget);
}
