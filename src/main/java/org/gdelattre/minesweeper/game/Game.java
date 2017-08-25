package org.gdelattre.minesweeper.game;

import org.gdelattre.minesweeper.manager.Resolver;
import org.gdelattre.minesweeper.model.Grid;

import java.util.Scanner;

/**
 * Minesweeper launcher
 */
public class Game {

    private Grid grid;

    private Resolver resolver;

    public Game() {
        grid = new Grid();
        resolver = new Resolver(grid);
    }

    private void launch() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the X value  : ");
        int x = scanner.nextInt();
        System.out.println();
        System.out.print("Enter the Y value  : ");
        int y = scanner.nextInt();
        System.out.println();

        int result = 0;

        while (result != Resolver.GAME_OVER) {
            result = resolver.resolve(x, y);

            if (result == Resolver.INVALID_COORDINATES) {
                System.out.println("(" + x + ", " + y + ") are invalid values");
            }

            if (result == Resolver.SUCCESS) {
                System.out.println("Congratulations ! you won !");
                return;
            }

            if (result == Resolver.GAME_OVER) {
                System.out.println("You loose, try again !");
                return;
            }

            if (result > 0) {
                System.out.println("Number of adjacent mined cells : " + result);
            }

            if (result != Resolver.GAME_OVER) {
                launch();
            }
        }
    }

    public static void main(String... args) {
        final Game game = new Game();

        game.launch();
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Resolver getResolver() {
        return resolver;
    }

    public void setResolver(Resolver resolver) {
        this.resolver = resolver;
    }
}
