package dev.puzzleshq.puzzleloader.zomboid.mixins.server.entrypoint;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.common.ModInit;
import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.server.ServerModInit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zombie.network.GameServer;

@Mixin(GameServer.class)
public class MixinInitServer {

    @Inject(method = "main", at = @At(value = "INVOKE", target = "Lzombie/core/Translator;loadFiles()V", shift = At.Shift.AFTER))
    private static void invokeInit(String[] par1, CallbackInfo ci) {
        ModInit.invoke();
        ServerModInit.invoke();
    }

}
