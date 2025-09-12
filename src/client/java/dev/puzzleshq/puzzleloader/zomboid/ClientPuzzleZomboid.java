package dev.puzzleshq.puzzleloader.zomboid;

import dev.puzzleshq.puzzleloader.zomboid.entrypoints.ClientModInit;
import dev.puzzleshq.puzzleloader.zomboid.entrypoints.ClientPostModInit;
import dev.puzzleshq.puzzleloader.zomboid.entrypoints.ClientPreModInit;

public class ClientPuzzleZomboid implements ClientPostModInit, ClientModInit, ClientPreModInit {

    @Override
    public void onClientInit() {
        System.out.println("ClientPuzzleZomboid");
    }

    @Override
    public void onClientPostInit() {

    }

    @Override
    public void onClientPreInit() {

    }

}
