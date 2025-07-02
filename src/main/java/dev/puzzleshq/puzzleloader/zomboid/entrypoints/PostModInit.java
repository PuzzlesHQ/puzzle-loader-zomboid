package dev.puzzleshq.puzzleloader.zomboid.entrypoints;

import dev.puzzleshq.puzzleloader.loader.util.PuzzleEntrypointUtil;

public interface PostModInit {

    String ENTRYPOINT_KEY = "init";

    void onPostInit();

    static void invoke() {
        PuzzleEntrypointUtil.invoke(
                ENTRYPOINT_KEY,
                PostModInit.class,
                PostModInit::onPostInit
        );
    }

}
