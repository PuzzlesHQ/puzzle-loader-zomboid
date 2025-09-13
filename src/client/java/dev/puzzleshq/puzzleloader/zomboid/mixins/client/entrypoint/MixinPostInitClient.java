package dev.puzzleshq.puzzleloader.zomboid.mixins.client.entrypoint;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.client.ClientPostModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.gameStates.MainScreenState;

@Mixin(MainScreenState.class)
public class MixinPostInitClient {

    @Inject(method = "main", at = @At(value = "INVOKE", target = "Lzombie/core/opengl/RenderThread;renderLoop()V", shift = At.Shift.BEFORE))
    private static void main(String[] args, CallbackInfo ci) {
        PostModInit.invoke();
        ClientPostModInit.invoke();
    }

}
