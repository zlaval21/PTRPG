/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;
import java.util.Random;
/**
 *
 * @author Zackery Lavalais
 * This class is used to make the structure of the moves/skills in the game. 
 * It gets how much damage they can do as well as what they're called and their IDs.
 */
public class Move implements MoveI {
    
    public static Random rnd = new Random();
    
    String name;
    int maxDamage;
    int Pp;
    int id = Monster.idSequence++;
    
    public Move(String name, int maxD, int Pp){
        this.name = name;
        this.maxDamage = maxD;
        this.Pp = Pp;
    }
    @Override
    public int getDamage() {
        return maxDamage;
        }

    @Override
    public int getId() {
        return id;
        }

    @Override
    public int getMaxDamage() {
        return 1+rnd.nextInt(maxDamage);
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString(){
        return String.format("Move %s, id = %d, can do %d points of damage.",name, id, maxDamage);
    }
}
