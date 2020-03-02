/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockbreaker;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chenshandi
 */
public class Animate implements Runnable {
    BlockBreakerPanel bp;
    Animate(BlockBreakerPanel b){
        bp=b;
    }
    public void run(){
        while(true){
            bp.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Animate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
