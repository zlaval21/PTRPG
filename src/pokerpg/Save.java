/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pokerpg;
import java.io.*;
import java.util.*;
/**
 *
 * @author Zack
 */
public class Save implements SaveI {
    
    @Override
    public MonsterI loadMonster(File f) throws IOException {
    Scanner s = new Scanner(f);
    return new Monster(s.next(),s.nextInt(),s.nextInt());
    }

    @Override
    public File saveMonster(MonsterI m) throws IOException {
        
        File f = new File(String.format("monster%d.txt",m.getId()));
        FileWriter fw = new FileWriter(f);
        PrintWriter pw = new PrintWriter(fw);
        pw.printf("%s %d %d%n",m.getName(),m.getMaxHp(),m.getId());
        pw.close();
        return f;
  }
}
