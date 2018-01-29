/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;
/**
 *
 * @author Zackery Lavalais
 * This class is used for Pokemon creation. It's used to make the name and get the Hp of the Monster.
 * It also gets their maxHp, checks their damages and also checks to see if they fainted or not.
 */
public class Monster implements MonsterI{
    
    String name;
    MoveI move;
    MoveI move2;
    int Hp;
    int maxHp;
    
    public Monster(String name, int Hp, int id){
        this.name = name;
        this.Hp = Hp;
        this.maxHp = Hp;
        this.id = id;
    }
    
    public Monster(String name, int Hp){
        this.name = name;
        this.Hp = Hp;
        this.maxHp = Hp;
        this.id = idSequence++;
    }
    
    @Override
    public int getMaxHp() {
        return maxHp;
    }
    
    @Override
    public int getHp() {
        return Hp;
    }
    
    static int idSequence = 1;
    int id;
    
     @Override
    public boolean Fainted() {
        return Hp <= 0;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public MoveI getMove() {
       return move;
    }

    @Override
    public void setMove(MoveI m, MoveI j) {
        move = m;
        move2 = j;
        }

    @Override
    public int Damage(MoveI m) {
        int d = m.getDamage();
        Hp -= d;
        String state = "Okay";
        if(Hp < 0)
            state = "Fainted";
        return d;
    }
    
    @Override
    public String toString(){
        return String.format("Pokemon name = %s, id = %d, Hp = %d, maxHp = %d.",name,id,Hp,maxHp);
    }

    @Override
    public String getYourPokemon() {
        return PokeRPG.yourPokemon;
    }
    
    @Override
    public String yourPokemonToString(){
        return String.format("Your Pokemon's name = %s, id = %d, Hp = %d, maxHp = %d", PokeRPG.yourPokemon, PokeRPG.id, PokeRPG.Hp, PokeRPG.maxHp);
    }

    @Override
    public boolean yourPokemonFainted() {
        return PokeRPG.Hp <= 0;
    }

    @Override
    public int damageTaken(MoveI m) {
        int d = m.getDamage();
        PokeRPG.Hp -= d;
        String state = "Okay";
        if(Hp < 0)
            state = "Fainted";
        return d;
    }
}
