/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;

/**
 *
 * @author Steven R. Brandt
 * Modified by Zackery Lavalais
 */

public interface MonsterI {
    
  /**
   * Gives the Pokemon a move.
   */
    
  public MoveI getMove();

  /**
   * Sets the move to the Pokemon.
   */
  
  public void setMove(MoveI m, MoveI j);

  /**
   * Gets the Pokemon's name.
   */
  
  public String getName();
  
  /**
   * Used to get your Pokemon from the static method in PokeRPG.java.
   */
  
  public String getYourPokemon();

  /**
   * Same thing as MoveI's get id.
   */
  
  public int getId();
  /**
   * Just a helper method used to see the Opponent's HP in realtime.
   */
  public int getHp();

  /**
   * Checks to see if the Pokemon was beaten.
   */
  
  public boolean Fainted();

  /**
   * Checks to see if the Pokemon can actually do any damage with its current move.
   */
  
  public int Damage(MoveI m);

  /**
   * The amount of health that a Pokemon has.
   */
  
  public int getMaxHp();
  
  public String toString();
  
  /**
   * Does the same as the normal toString() except with your Pokemon instead of the random opponent.
   */
  
  public String yourPokemonToString();
  
  /**
   * Checks to see if your Pokemon has been beaten.
   */
  public boolean yourPokemonFainted();
  
  /**
   * Checks to see what damage your Pokemon is currently taking.
   */
  public int damageTaken(MoveI m);
}
