/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;
import java.util.*;
import java.io.*;
/**
 *
 * @author Zackery Lavalais
 * This is the backbone of the game. All of the battles are dealt with in this class.
 * It also houses several other RPG-style elements like: Leveling up, healing, and checking your inventory.
 */
public class BattleSystem {
    public final static Random rnd = new Random();
    Scanner scan = new Scanner(System.in);
    int Hp;
    int turn = 0;
     
    Map<String, MonsterI> pokemon = new HashMap<>();
    Map<String, MoveI> moves = new HashMap<>();
    Set<Integer> id = new HashSet<>();
    
    public void addMonster(MonsterI madd){
        MonsterI m = pokemon.get(madd.getName());
        if(m == null){
            pokemon.put(madd.getName(), madd);
        }
    }
    
    public void addMove(MoveI moadd){
        MoveI m = moves.get(moadd.getName());
        if(m == null){
            moves.put(moadd.getName(),moadd);
        }
    }
    
    // All the following numbers come from the String[] containing the descriptions of the healing items.
    class HpPotion implements Potion{

        @Override
        public void use() {
           PokeRPG.Hp += 20;
        }
    }
    
    class PpPotion implements Potion{

        @Override
        public void use() {
            PokeRPG.Pp += 5;
        }
    }
    
    class HpPotion2 implements Potion{

        @Override
        public void use() {
            PokeRPG.Hp += 50;
        }
    }
    
    class PpPotion2 implements Potion{

        @Override
        public void use() {
            PokeRPG.Pp  += 10;
        }
    }
    
    class HpPotion3 implements Potion{

        @Override
        public void use() {
            PokeRPG.Hp += 70;
        }
    }
    
    class PpPotion3 implements Potion{

        @Override
        public void use() {
            PokeRPG.Pp += 15;
        }
    }
    
    class FreshWater implements Potion{

        @Override
        public void use() {
            PokeRPG.Hp += 35;
        }
    }
    
    class Lemonade implements Potion{

        @Override
        public void use() {
            PokeRPG.Pp  += 12;
        }
    }
    
    class SodaPop implements Potion{

        @Override
        public void use() {
            PokeRPG.Hp += 65;
        }
    }
    
    // This method is prompted at the end of the fight, if the player wins they get experience and money.
    void endFight(MonsterI player){
        System.out.println("The fight is over!");
        PokeRPG.experience += 10;
        PokeRPG.money += 30;
        if(PokeRPG.experience >= 30){
            levelUp();
        }
    }
    
    // After several battles, this method will prompt, telling the player that they have levelled up.
    void levelUp(){
        System.out.println("Level up!");
        PokeRPG.maxHp += 20;
        PokeRPG.Pp += 2;
        PokeRPG.level++;
        System.out.println("You are now level " + PokeRPG.level);
    }
    
    public MonsterI[] battle() {
        
        System.out.println();
        System.out.println("===============================================\n");
        
        MonsterI[] monsters = pokemon.values().toArray(new MonsterI[0]);
        
        int p1 = rnd.nextInt(monsters.length);
        int p2 = rnd.nextInt(monsters.length-1);
        
        if(p2 >= p1)
           p2++;
        
        
        MonsterI m1 = monsters[p1];
        MonsterI m2 = monsters[p2];

        System.out.printf("The battle is between %s and %s.%n",PokeRPG.yourPokemon, m2.getName());
        System.out.println("");
        
        MoveI[] movesArray = moves.values().toArray(new MoveI[0]);
        
        int z1 = rnd.nextInt(movesArray.length);
        int z2 = rnd.nextInt(movesArray.length-1);
        
        if(z2 >= z1)
           z2++;
        
        MoveI a1 = movesArray[z1];
        MoveI a2 = movesArray[z2];
        
        m1.setMove(a1, a1);
        m2.setMove(a2, a2);
        
        System.out.println(m1.yourPokemonToString() + "\n");
        System.out.println(m1.getMove() + "\n");
        System.out.println(m2 + "\n");
        System.out.println(m2.getMove() + "\n");
        
        do{
           MoveI m = (MoveI) m1.getMove();
           MoveI j = (MoveI) m2.getMove();
           turn = turn+1;
           if(turn > 1){ 
           System.out.println("Turn #" + (turn - 1));
           System.out.println("");
           System.out.println("Current Hp: " + PokeRPG.Hp);
           }
           
           if(m2.getHp() <= 0) {
              endFight(m1);
              System.out.printf("%s is defeated.%n",m2.getName());
              System.out.println("You won!");
              System.out.println("Winner: " + m1.getYourPokemon());
              break;
      }
           else if(PokeRPG.Hp <= 0){
               endFight(m2);
               System.out.printf("%s is defeated.%n",m1.getYourPokemon());
               System.out.println("You lost...");
               System.out.println("Winner: " + m2.getName());
               break;
           }
           int damage = 0; // The amount of damage you do.
           int damage2 = 0; // The amount of damage your oppnonent does.
           if(turn == 1){
               continue;
           }
           else if(turn % 2 != 0){
               damage = m2.Damage(m);
           }
           else if(turn % 2 == 0){
               damage2 = m1.damageTaken(j);
           }
           System.out.println("Enemy's current HP: " + m2.getHp());
           System.out.println("What would you like to do?\n");
           System.out.println("1. Heal.");
           System.out.println("2. Keep battling.");
           System.out.println("3. Use other items.\n");
           int choice  = scan.nextInt();
           if(choice == 1){
               if(PokeRPG.Items.contains("Potion")){
                   HpPotion potion = new HpPotion();
                   potion.use();
                   System.out.println("You restored 20 points of health!\n");
                   continue;
               }
               else if(PokeRPG.Items.contains("Super Potion")){
                   HpPotion2 potion = new HpPotion2();
                   potion.use();
                   System.out.println("You restored 50 points of health!\n");
                   continue;
               }
               else if(PokeRPG.Items.contains("Hyper Potion")){
                   HpPotion3 potion = new HpPotion3();
                   potion.use();
                   System.out.println("You restored 70 points of health!\n");
                   continue;
               }
           }
           else if(choice == 2){
               if(turn % 2 != 0){
               System.out.println(" " + m1.getYourPokemon() + " did " + damage + " damage to " + m2.getName() + "\n");
            }
               else if(turn % 2 == 0){
               System.out.println(" " + m2.getName()+ " did " + damage2 + " damage to " + m1.getYourPokemon() + "\n");
               continue;
           }
           }
           else{
               System.out.println("What item would you like to use?\n");
               PokeRPG.printItems();
               Scanner scn = new Scanner(System.in);
               String itemChoice = scn.next();
               if(PokeRPG.Items.contains("Ether") && itemChoice.equals("ether")){
                   PpPotion potion = new PpPotion();
                   potion.use();
                   System.out.println("You restored 5 points of Pp!\n");
                   continue;
               }
               
               else if(PokeRPG.Items.contains("Elixir") && itemChoice.equals("elixir")){
                   PpPotion2 potion = new PpPotion2();
                   potion.use();
                   System.out.println("You restored 10 points of Pp!\n");
                   continue;
               }
               
               else if(PokeRPG.Items.contains("Moomoo Milk") && itemChoice.equals("moomoo milk")){
                   PpPotion3 potion = new PpPotion3();
                   potion.use();
                   System.out.println("You restored 15 points of Pp!\n");
                   continue;
               }
               
               else if(PokeRPG.Items.contains("Fresh Water") && itemChoice.equals("fresh water")){
                   FreshWater water = new FreshWater();
                   water.use();
                   System.out.println("You restored 35 points of Hp!\n");
                   continue;
               }
               
               else if(PokeRPG.Items.contains("Lemonade") && itemChoice.equals("lemonade")){
                   Lemonade lemon = new Lemonade();
                   lemon.use();
                   System.out.println("You restored 12 points of Pp!\n");
                   continue;
               }
               
               else if(PokeRPG.Items.contains("Soda Pop") && itemChoice.equals("soda pop")){
                   SodaPop pop = new SodaPop();
                   pop.use();
                   System.out.println("You restored 35 points of Hp!\n");
                   continue;
               }
               
           }
        } while(true);
        return new MonsterI[]{m1, m2};
        }

    
    
     public static void arena(BattleSystem bs, SaveI s) throws IOException{
        for(int i = 0; i < 1; i++) {
        MonsterI[] players = bs.battle();

        File f = s.saveMonster(players[0]);
        MonsterI m = s.loadMonster(f);

        f = s.saveMonster(players[1]);
        m = s.loadMonster(f);
        }
        PokeRPG.Menu();
     }
}
