package dev.puzzleshq.puzzleloader.zomboid;

import dev.puzzleshq.puzzleloader.loader.mod.entrypoint.PreLaunchInitializer;

public class PrePuzzleZomboid implements PreLaunchInitializer {

    @Override
    public void onPreLaunch() {
        System.out.println("PrePuzzleZomboid");
    }

}
