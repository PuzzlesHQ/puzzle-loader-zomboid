package dev.puzzleshq.puzzleloader.zomboid.mixins.server.entrypoint;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PreModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.server.ServerPreModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.network.GameServer;

@Mixin(GameServer.class)
public class MixinPreInitServer {

    @Inject(method = "main", at = @At("HEAD"))
    private static void invokePre(String[] par1, CallbackInfo ci) {
        PreModInit.invoke();
        ServerPreModInit.invoke();
    }

}
