package dev.puzzleshq.puzzleloader.zomboid;

import dev.puzzleshq.puzzleloader.zomboid.entrypoints.ModInit;
import dev.puzzleshq.puzzleloader.zomboid.entrypoints.PostModInit;
import dev.puzzleshq.puzzleloader.zomboid.entrypoints.PreModInit;

public class CommonPuzzleZomboid implements PreModInit, ModInit, PostModInit {

    @Override
    public void onPreInit() {
        System.out.println("CommonPuzzleZomboid");
    }

    @Override
    public void onInit() {
    }

    @Override
    public void onPostInit() {

    }

}
