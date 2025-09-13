package dev.puzzleshq.puzzleloader.zomboid.mixins.server.entrypoint;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.PostModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.server.ServerPostModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.network.GameServer;

@Mixin(GameServer.class)
public class MixinPostInitServer {

    @Inject(method = "main", at = @At(value = "INVOKE", target = "Lzombie/core/znet/SteamGameServer;EnableHeartBeats(Z)V", shift = At.Shift.AFTER))
    private static void invokePost(String[] par1, CallbackInfo ci) {
        PostModInit.invoke();
        ServerPostModInit.invoke();
    }

}
