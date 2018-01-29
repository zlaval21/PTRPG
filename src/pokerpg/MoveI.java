/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;

/**
 *
 * @author Zackery Lavalais
 */
public interface MoveI {
    
    /**
     * Gets the amount of damage that a certain move can do to an enemy Pokemon.
     */
    
  public int getDamage();

  /**
   * Returns an id for each move and Pokemon to help distinguish each move from each move
   * and also each Pokemon from each Pokemon.
   */
  
  public int getId();

  /**
   * The maximum amount of damage a certain move/skill can do.
   */
  
  public int getMaxDamage();
  
  public String toString();

  /**
   * The move's name. 
   */
  public String getName();
}
