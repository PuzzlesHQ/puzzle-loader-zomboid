package dev.puzzleshq.puzzleloader.zomboid.entrypoints;

import dev.puzzleshq.puzzleloader.loader.util.PuzzleEntrypointUtil;

public interface PreModInit {

    String ENTRYPOINT_KEY = "preInit";

    void onPreInit();

    static void invoke() {
        PuzzleEntrypointUtil.invoke(
                ENTRYPOINT_KEY,
                PreModInit.class,
                PreModInit::onPreInit
        );
    }

}
