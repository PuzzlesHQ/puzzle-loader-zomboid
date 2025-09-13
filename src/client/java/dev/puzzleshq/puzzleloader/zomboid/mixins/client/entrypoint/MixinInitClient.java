package dev.puzzleshq.puzzleloader.zomboid.mixins.client.entrypoint;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.gameStates.MainScreenState;

@Mixin(MainScreenState.class)
public class MixinInitClient {

    @Inject(method = "main", at = @At(value = "INVOKE", target = "Lorg/lwjglx/opengl/Display;setIcon(Lorg/lwjgl/glfw/GLFWImage$Buffer;)V", shift = At.Shift.BEFORE))
    private static void main(String[] args, CallbackInfo ci) {
        ModInit.invoke();
        ClientModInit.invoke();
    }

}
