package pokerpg;

/**
 *
 * @author Zackery Lavalais
 * This program uses interfaces made by Steven R. Brandt and has been modified by Zackery Lavalais.
 * April 28th, 2015.
 * This program is a text based battle arena featuring monsters and elements from the game series Pokemon.
 */

import java.util.*;
import java.io.*;
public class PokeRPG {

    /**
     * @param args the command line arguments
     */
    
    private final static Scanner scan = new Scanner(System.in);
    private final static Random rnd = new Random();
    // The amount of starting money in the game. (Used to buy items and such.)
    static int money = 300;
    static int id;
    // Your Pokemon's starting health. Since you can only choose from the first 68.
    static int Hp = 65;
    // Your Pokemon's starting Pp. It increases with level.
    static int Pp = 20;
    // Your Pokemon's experience. It increases with level.
    static int experience = 0;
    // Your Pokemon's level. It increases based on experience.
    static int level = 0;
    static int maxHp = Hp;
    
    // String containing the first stage of Pokemon.
    final static String firstStagePokemon[] = {
      "Bulbasaur", "Squirtle", "Charmander",
      "Caterpie", "Weedle", "Pidgey",
      "Ekans", "Pikachu", "Nidoran(boy)",
      "Vulpix", "Zubat", "Mankey",
      "Psyduck", "Mankey", "Growlithe",
      "Machop", "Geodude", "Abra",
      "Ponyta", "Magnemite", "Shellder",
      "Gastly", "Onix", "Cubone",
      "Horsea", "Scyther", "Magmar", 
      "Pinsir", "Magikarp", "Lapras", 
      "Eevee", "Chikorita", "Totodile", 
      "Dratini", "Cyndaquil", "Spinarak", 
      "Mareep", "Qwilfish", "Gligar", 
      "Teddiursa", "Houndour", "Sneasel",
      "Larvitar", "Treecko", "Torchic",
      "Mudkip", "Wurmple",  "Seedot",
      "Ralts", "Surskit", "Nincada",
      "Aron", "Roselia", "Skarmory",
      "Trapinch", "Cacnea", "Swablu", 
      "Zangoose", "Seviper", "Feebas",
      "Tropius", "Absol", "Heracross", 
      "Snorunt", "Clamperl", "Bagon",
      "Beldum", "Nidoran(girl)"
};
    
    // String containing the second stage of Pokemon.
    private final static String secondStagePokemon[] = {
      "Ivysaur", "Wartortle", "Charmeleon",
      "Metapod", "Kakuna", "Pidgeotto",
      "Nidorino", "Nidorina", "Golbat", 
      "Machoke", "Graveler", "Haunter",
      "Seadra", "Bayleef", "Croconaw",
      "Dragonair", "Quilava", "Flaaffy",
      "Pupitar", "Grovyle", "Combusken", 
      "Marshtomp", "Silcoon", "Cascoon",
      "Lairon", "Vibrava", "Shelgon", 
      "Metang"
    };
    
    // String containing the final(and most powerful stage) stage of Pokemon.
    private final static String finalStagePokemon[] = {
      "Butterfree", "Beedrill", "Venusaur", 
      "Blastoise", "Charizard", "Pidgeot",
      "Nidoking", "Nidoqueen", "Ninetales", 
      "Crobat", "Primeape", "Arcanine",  
      "Alakazam", "Machamp", "Golem", 
      "Rapidash", "Magneton", "Cloyster",
      "Gengar", "Steelix", 
      "Marowak", "Kingdra", "Scizor",
      "Gyarados", "Jolteon", "Flareon", 
      "Vaporeon", "Sylveon", "Umbreon", 
      "Espeon", "Glaceon", "Leafeon",
      "Dragonite", "Meganium", "Feraligatr", 
      "Typhlosion", "Ariados", "Ampharos",
      "Gliscor", "Ursaring", "Houndoom", 
      "Tyranitar", "Sceptile", "Blaziken", 
      "Swampert", "Beautifly", "Dustox",
      "Shiftry", "Gardevoir", "Gallade",
      "Masquerain", "Ninjask", "Shedinja", 
      "Aggron", "Manectric", "Sharpedo", 
      "Wailord", "Flygon","Cacturne", 
      "Altaria", "Milotic", "Glalie", 
      "Froslass", "Huntail", "Gorebyss",
      "Salamnence", "Metagross", "Greninja"
    };
    
    // An arraylist of items in your inventory.
    final static ArrayList<String> Items = new ArrayList<>();
    
    // This is a list of the items in the game.
    private final static String healingItems[] = {
      "Potion", "Super Potion", "Ether", 
      "Hyper Potion", "Elixir", "Fresh Water",
      "Soda Pop", "Lemonade", "Moomoo Milk", 
      "Berry Juice", "Honey"
};
    
    // This contains all of the items/moves (Spells) in the game.
    private final static String Moves[] = {
      "Ember", "Bubble", "Absorb", "Thundershock",
      "Night Shade", "Rock Throw", "Magnitude",
      "Icy Wind", "Iron Head", "Tackle", "Gust",
      "Karate Chop", "Confusion", "Dragonbreath",
      "Bite", "Poison Sting", "Bug Bite", "Flamethrower",
      "Bubblebeam", "Mega Drain", "Thunderbolt",
      "Ominous Wind", "Rock Slide", "Sandstorm", 
      "Ice Beam", "Heavy Slam", "Body Slam",
      "Wing Attack", "Seismic Toss", "Psybeam",
      "Dragon Claw", "Crunch", "Poison Tail", 
      "Bug Buzz", "Fire Blast", "Hydro Pump", 
      "Giga Drain", "Zap Cannon", "Shadow Ball", 
      "Stone Edge", "Earthquake", "Blizzard",
      "Meteor Mash", "Giga Impact", "Sky Attack",
      "Cross Chop", "Psychic", "Draco Meteor", 
      "Foul Play", "Sludge Bomb", "Signal Beam"
    };
    
    // This gives a desctiption of what the irems in the game are for.
    private final static String healingItemDescription[] = {
      "Heals HP by 20.", "Heals HP by 50.",
      "Heals PP by 5.", "Heals HP by 70.",
      "Heals PP by 10.", "Heals HP by 35", 
      "Heals HP by 65.", "Heals PP by 12.", 
      "Heals PP by 15.", "The juice from very rare berries.",
      "The honey from a rare, bee-type Pokemon."
    };
    
    // This contains the descriptions of the items/moves.
    private final static String moveDescription[] = {
      "A type of attack dealing light fire-type damage.",
      "A type of attack dealing light water-type damage.",
      "A type of attack dealing light grass-type damage.",
      "A type of attack dealing light electric-type damage.",
      "A type of attack dealing light ghost-type damage.",
      "A type of attack dealing light rock-type damage.",
      "A type of attack dealing light ground-type damage.",
      "A type of attack dealing light ice-type damage.",
      "A type of attack dealing light steel-type damage.",
      "A type of attack dealing light normal-type damage.",
      "A type of attack dealing light flying-type damage.",
      "A type of attack dealing light fighting-type damage.",
      "A type of attack dealing light psychic-type damage.",
      "A type of attack dealing light dragon-type damage.",
      "A type of attack dealing light dark-type damage.",
      "A type of attack dealing light poison-type damage.",
      "A type of attack dealing light bug-type damage.",
      "A type of attack dealing medium fire-type damage.",
      "A type of attack dealing medium water-type damage.",
      "A type of attack dealing medium grass-type damage.",
      "A type of attack dealing medium electric-type damage.",
      "A type of attack dealing medium ghost-type damage.",
      "A type of attack dealing medium rock-type damage.",
      "A type of attack dealing medium ground-type damage.",
      "A type of attack dealing medium ice-type damage.",
      "A type of attack dealing medium steel-type damage.",
      "A type of attack dealing medium normal-type damage.",
      "A type of attack dealing medium flying-type damage.",
      "A type of attack dealing medium fighting-type damage.",
      "A type of attack dealing medium psychic-type damage.",
      "A type of attack dealing medium dragon-type damage.",
      "A type of attack dealing medium dark-type damage.",
      "A type of attack dealing medium poison-type damage.",
      "A type of attack dealing medium bug-type damage.",
      "A type of attack dealing heavy fire-type damage.",
      "A type of attack dealing heavy water-type damage.",
      "A type of attack dealing heavy grass-type damage.",
      "A type of attack dealing heavy electric-type damage.",
      "A type of attack dealing heavy ghost-type damage.",
      "A type of attack dealing heavy rock-type damage.",
      "A type of attack dealing heavy ground-type damage.",
      "A type of attack dealing heavy ice-type damage.",
      "A type of attack dealing heavy steel-type damage.",
      "A type of attack dealing heavy normal-type damage.",
      "A type of attack dealing heavy flying-type damage.",
      "A type of attack dealing heavy fighting-type damage.",
      "A type of attack dealing heavy psychic-type damage.",
      "A type of attack dealing heavy dragon-type damage.",
      "A type of attack dealing heavy dark-type damage.",
      "A type of attack dealing heavy poison-type damage.",
      "A type of attack dealing heavy bug-type damage.",
    };
    
    /** HP is hit points, or how much damage a unit can take.
     * PP is power points, or how many more times the unit can use the move.
     **/
    private final static int prices[]= {
        30, 60, 40, 80, 90, 35, 50, 55, 70, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 300, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800, 800,
        1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100, 1100
    };
    
    // This is just the intro dialogue to the game. Not much else.
    public static void intro(){
        System.out.println("Welcome to the world of PokeRPG! This is where the you and a Pokemon of your choosing will do battle against other random Pokemon.");
        System.out.println("");
        System.out.println("Now, let me explain everything to you, so you aren't completely lost.");
        System.out.println("");
        System.out.println("Each Pokemon has a certain move.");
        System.out.println("");
        System.out.println("Some Pokemon are stronger than others as well as some moves are stronger than others.");
        System.out.println("");
        System.out.println("As you level up, your Hp and Pp will increase, making you an unstoppable force after a while.");
        System.out.println("");
        System.out.println("You're also allowed to use items on your Pokemon to keep them healthier in battle.");
        System.out.println("");
        System.out.println("Good luck out there, player~!");
        System.out.println("");
    }
    
    // This method prompts both the gender and name of the user.
    public static void gender(){
        String gender;
        String name;
        System.out.println("===============================\n");
        System.out.println("Are you male or female? (Please enter male for male and female for female) \n");
        gender = scan.next();
        // This makes the selection not case sensitive.
        if(gender.equals("male") || gender.equals("Male")){
            System.out.println("What is your name? \n");
            System.out.println("1. Gary");
            System.out.println("2. Green");
            System.out.println("3. Ash");
            System.out.println("4. Red");
            System.out.println("5. Enter your own name\n");
            System.out.println("(Enter a number 1-5)\n");
            int selection = scan.nextInt();
            if(selection == 1){
                System.out.println("Gary, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 2){
                System.out.println("Green, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 3){
                System.out.println("Ash, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 4){
                System.out.println("Red, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 5){
                name = scan.next();
                System.out.println(name + ", your adventure in the world of Pokemon is about to begin!\n");
            }
            else 
                System.out.println("That's not a valid entry, please enter a number 1-5.\n");
        }
        
        // This is for if the user chooses the female gender (Also not case sensitive)
        else if(gender.equals("female") || gender.equals("Female")){
            System.out.println("What is your name? \n");
            System.out.println("1. Misty");
            System.out.println("2. Dawn");
            System.out.println("3. May");
            System.out.println("4. Iris");
            System.out.println("5. Enter your own name\n");
            System.out.println("(Enter a number 1-5)\n");
            int selection = scan.nextInt();
            if(selection == 1){
                System.out.println("Misty, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 2){
                System.out.println("Dawn, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 3){
                System.out.println("May, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 4){
                System.out.println("Iris, your adventure in the world of Pokemon is about to begin!\n");
            }
            else if(selection == 5){
                name = scan.next();
                System.out.println(name + ", your adventure in the world of Pokemon is about to begin!\n");
            }
            else 
                System.out.println("That's not a valid entry, please enter a number 1-5.\n");
        }
        else {
            System.out.println("That's not a valid gender. Please enter either male or female.");
            System.out.println("===============================");
        }
    }
    
    // The gaming menu. From here you can either fight, check what items are in your inventory, buy items, or stop playing the game.
    public static void Menu(){
        System.out.println("");
        System.out.println("===============================");
        System.out.println("");
        System.out.println("What would you like to do?\n");
        System.out.println("1. Fight!\n");
        System.out.println("2. Check Inventory!\n");
        System.out.println("3. Buy items!\n");
        System.out.println("4. Quit!\n");
        System.out.println("5. Check moves!\n");
        System.out.println("You have $" + money + ".");
        System.out.println("(Enter a number 1-5)\n");
        int selection = scan.nextInt();
        System.out.println("");
        if(selection == 1){
            ;
        }
        else if(selection == 2){
            printItems();
            PokeRPG.Menu();
        }
        else if(selection == 3){
            System.out.println("===============================\n");
            System.out.println("Welcome to the shop! Would you like to browse our wares? Yes or no? \n");
            String answer = scan.next();
            if(answer.equals("No") || answer.equals("no")){
                System.out.println("I hope to see you again!\n");
                PokeRPG.Menu();
            }
            if(answer.equals("Yes") || answer.equals("yes")){
                System.out.println("");
                if(money < 30){
                    System.out.println("You don't have enough money to buy anything!");
                    PokeRPG.Menu();
                }
                for(int j = 0; j <= healingItems.length-1; j++){
                   System.out.println((j+1) + " " + "$" + prices[j] + " " + healingItems[j] + " - " + healingItemDescription[j]);
                }
                System.out.println("The numbers indicate which items you'd like to buy.\n");
                int choice = scan.nextInt();
                for(int i = 1; i <= healingItems.length-1; i++){
                    if(i == choice){
                        System.out.println("You just bought a(n) " + healingItems[i-1] + ".\n");
                        money -= prices[i-1]; // Uses the prices array to determine how much money to spend
                        addItems(healingItems[i-1]); // Adds the item you just bought to the inventory.
                        System.out.println("Would you like to buy another?\n");
                        String answer2 = scan.next();
                        if(answer2.equals("yes") || answer2.equals("Yes")){
                            if(money < 30){
                                System.out.println("You don't have enough money to buy anything!");
                                PokeRPG.Menu();
                            }
                            System.out.println("You just bought a(n) " + healingItems[i-1] + ".\n");
                            addItems(healingItems[i-1]);
                            PokeRPG.Menu();
                        }
                        else {
                            System.out.println("Would you like to do anything else?\n");
                            String answer3 = scan.next();
                            if(answer3.equals("No") || answer3.equals("no")){
                                System.out.println("I hope to see you again!");
                                PokeRPG.Menu();
                            }
                        }
                    }
                    else
                        break;
                }
            }
        }
        else if (selection == 4){
            System.out.println("Thank you for playing!");
        }
        else{
            for(int i = 0; i <= Moves.length-1;i++){
                System.out.println(Moves[i] + " - " + moveDescription[i]);
            }
        }
    }
    
    // This is where the user chooses which Pokemon they want to play the game with.
    static String choosePokemon(){
        System.out.println("===============================");
        System.out.println("Now, it's time to choose your Pokemon from this list. Choose Wisely!");
        System.out.println("");
        int i = 0;
        String yourPokemon = "";
        for(String s : firstStagePokemon){
            System.out.println(i + ". " + s);
            i++;
        }
        System.out.println("");
        System.out.println("Enter a number 0 through " + (firstStagePokemon.length-1));
        int selection = scan.nextInt();
        for(int j = 0; j <= firstStagePokemon.length; j++){
            if(j == selection){
            System.out.println("You've chosen the Pokemon, " + firstStagePokemon[selection] + ".\n");
            yourPokemon = firstStagePokemon[selection];
            id = selection;
            }
        }
        return yourPokemon;
    }
    
    // Gets the Id of your starting Pokemon. Same concept as the HP at the start.
    static int getYourPokemonId(){
        return id;
    }
    
    // Makes a static string called yourPokemon used in the Monster and BatteSystem classes.
    static String yourPokemon = PokeRPG.choosePokemon();
    
    // Adds itesm to the user's inventory.
    public static void addItems(String item){
        Items.add(item);
    }
    
    // Gives you a list of the items in the inventory. (As seen when you choose the check items choice from the menu.)
    public static void printItems(){
        Items.stream().forEach((s) -> {
            System.out.println(s);
        });
    }
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        int i = 0;
        PokeRPG.intro();
        PokeRPG.gender();
        PokeRPG.Menu();
        BattleSystem bs = new BattleSystem();
        for(int z = 0; z <= firstStagePokemon.length-1; z++){
            bs.addMonster(new Monster(firstStagePokemon[z],(rnd.nextInt(40)+20))); //The numbers are the Hp as it adds the monster to the BattleSystem
        }
        for(int y = 0; y <= secondStagePokemon.length-1; y++){
            bs.addMonster(new Monster(secondStagePokemon[y],(rnd.nextInt(60)+20)));
        }
        for(int x = 0; x <= finalStagePokemon.length-1; x++){
            bs.addMonster(new Monster(finalStagePokemon[x],(rnd.nextInt(75)+20)));
        }
        for(String s : Moves){
            if(i < 17){ // Adds the first 17, or the weakest, moves.
            bs.addMove(new Move(Moves[i],(rnd.nextInt(10)+5),25));
            i++;
            }
            else if(i < 34){ // adds the next 17, or the medium damage, moves.
                bs.addMove(new Move(Moves[i],(rnd.nextInt(15)+5), 15));
                i++;
            }
            else{ // adds the strongest moves.
                bs.addMove(new Move(Moves[i], (rnd.nextInt(20)+5), 15));
                i++;
            }
        }
        BattleSystem.arena(bs, new Save());
    }
}
