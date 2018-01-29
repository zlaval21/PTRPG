/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;
import java.io.*;
/**
 *
 * @author Zackery Lavalais
 */
public interface SaveI {
    MonsterI loadMonster(File f) throws IOException;
    File saveMonster(MonsterI m) throws IOException;
}
