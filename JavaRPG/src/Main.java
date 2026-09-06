import java.util.Scanner;
import java.util.Random;

public class Main {

    static int invalidOption(Scanner scanner) {
            System.out.println("ERROR: Invalid option!");
            System.out.println("Type 1, 2 or 3.");
            return scanner.nextInt();
    }

    static void playerAttack(Player player, Monster monster, Random random) {
        System.out.println("You attack the " + monster.name + "!");
        player.attack = random.nextInt(11) + 5;

        if (player.attack >= 14) {
            player.attack = player.attack + 3;
            System.out.println("Critical hit!");
        }
        System.out.println("You dealt " + player.attack + " damage!");
        monster.health = monster.health - player.attack;
    }

    static void monsterAttack(Player player, Monster monster, Random random) {
        System.out.println("--- Monster turn ---");

        monster.attack = random.nextInt(8) + 2;


        if(player.defend) {
            monster.attack = monster.attack / 2;
            player.defend = false;
            System.out.println("Damage halved!");
        }
        System.out.println("The " + monster.name + " attacks for " + monster.attack + " damage!");
        player.health = player.health - monster.attack;
    }

    static void playerDefend (Player player) {
        System.out.println("You defend yourself!");
        player.defend = true;
    }

    static void battle(Player player, Monster monster, Random random, Scanner scanner) {
        System.out.println("Your HP: " + player.health + " | " + monster.name + " HP: " + monster.health);
        System.out.println("--- Player turn ---");
        System.out.println("What do you do? (1-Attack, 2-Defend, 3-Run)");
        boolean fight = true;

        while (fight) {
            int battle = scanner.nextInt();
            while(battle !=1 && battle !=2 && battle !=3) {
                battle = invalidOption(scanner);
            }
            switch (battle) {

                case 1: {
                    playerAttack(player, monster, random);

                    if (monster.health <= 0) {
                        fight = false;
                        System.out.println("You won the battle!");
                    } else {
                        monsterAttack(player, monster, random);

                        if (player.health <= 0) {
                            fight = false;
                            System.out.println("YOU DIED");
                            System.out.println("Game over!");
                        } else {
                            System.out.println(
                                    "Your HP: " + player.health + " | " + monster.name + " HP: " + monster.health
                            );

                            System.out.println("--- Player turn ---");
                            System.out.println("What do you do? (1-Attack, 2-Defend, 3-Run)");
                        }
                    }
                }
                break;

                case 2: {
                    playerDefend(player);

                        monsterAttack(player, monster, random);


                        if (player.health <= 0) {
                            fight = false;
                            System.out.println("YOU DIED");
                            System.out.println("Game over!");
                        } else {
                            System.out.println(
                                    "Your HP: " + player.health + " | " + monster.name + " HP: " + monster.health
                            );
                        }

                            System.out.println("--- Player turn ---");
                            System.out.println("What do you do? (1-Attack, 2-Defend, 3-Run)");
                    break;
                }


                case 3: {
                    fight = false;
                    System.out.println("You escaped successfully!");
                    break;
                }

            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to (RPG test)!");
        System.out.println("Type 1 to start, 2 to quit");
        int choice = scanner.nextInt();

        while (choice != 1 && choice != 2) {
            choice = invalidOption(scanner);
        }

            switch (choice) {

                case 2: {
                    System.out.println("Game closed.");
                    break;
                }

                case 1: {
                    System.out.println("Welcome to the game!");
                    System.out.println("Inside the dungeon, you find two paths.");
                    System.out.println("Which path do you go down? (1 or 2)");

                    int paths = scanner.nextInt();
                    while(paths != 1 && paths != 2) {
                        paths = invalidOption(scanner);
                    }
                    switch (paths) {

                        case 1: {
                            System.out.println("You went down the first path.");
                            System.out.println("A goblin appeared!");

                            Monster goblin = new Monster("Goblin", 30, 5);
                            Player player = new Player("Player", 100, 10, false);

                            // battle
                            battle(player, goblin, random, scanner);
                        }

                        break;

                        case 2: {
                            System.out.println("You went down the second path.");
                            System.out.println(
                                    "The path is empty. Theres two doors in front of you" +
                                            " - a wooden and rusted one. Which door do you go through? (1 or 2)"
                            );
                        }

                        int doors = scanner.nextInt();
                        while(doors != 1 && doors != 2) {
                            doors = invalidOption(scanner);
                        }
                        switch (doors) {

                            case 1: {
                                System.out.println("You opened the wooden door and went inside.");
                                System.out.println("A goblin appeared!");

                                Monster goblin = new Monster("Goblin", 30, 5);
                                Player player = new Player("Player", 100, 10, false);

                                // battle
                                battle(player, goblin, random, scanner);
                            }

                            break;

                            case 2: {
                                System.out.println("You opened the rusted door.");
                                System.out.println("A dragon appeared!");

                                Monster dragon = new Monster("Dragon", 90, 10);
                                Player player = new Player("Player", 100, 10, false);

                                // battle
                                battle(player, dragon, random, scanner);
                            }

                            break;

                        }

                    }
                }

            }
    }
}