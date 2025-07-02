package dev.puzzleshq.puzzleloader.zomboid.mixins.entrypoint;

import dev.puzzleshq.puzzleloader.zomboid.entrypoints.PreModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.network.GameServer;

@Mixin(GameServer.class)
public class MixinPreInitServer {

    @Inject(method = "main", at = @At("HEAD"))
    private static void main(String[] par1, CallbackInfo ci) {
        PreModInit.invoke();
    }

}
