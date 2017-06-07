
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method;

import gui.MP3GUI;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;



/**
 *
 * @author Dat Pahm
 */
public class Method {
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    FileInputStream FIS;
    BufferedInputStream BIS;
    public static Player player;
    public static int isComplete;
    
    public void Stop(){ // hàm Stop, trở về 0s
        if(player != null){
            player.close();
           pauseLocation = 0;
           songTotalLength = 0;
        }
    }
    public void Pause() throws IOException{ // tạm dừng ở vị trí đang chơi
        if(player != null){
            pauseLocation =FIS.available();
            player.close();
        }
    }
    public void Play(String path){ // chơi nhạc
        try {
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
           
            try {
                player = new Player(BIS);
                songTotalLength = FIS.available();
                fileLocation = path +"";
            } catch (JavaLayerException ex) {
              
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                    if(player.isComplete() &&(MP3GUI.autoPlay== 1)){
                        
                        Play(fileLocation);
                    }
                } catch (JavaLayerException ex) {
                    
                }
            }
        }.start();
    }
    public boolean isComplete(){ // kiểm tra hoàn thành chưa
        player = this.player;
        if(player.isComplete()){
            return true;
        }
        return false;
        
    }
    
}
