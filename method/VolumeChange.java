/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;

/**
 *
 * @author Dat Pham
 *  Volume 
 * 
 */

public class VolumeChange { // bắt mic
     public FloatControl getVolumeControl() throws Exception {
    try {
        Mixer.Info mixers[] = AudioSystem.getMixerInfo();
        for (Mixer.Info mixerInfo : mixers) {
            Mixer mixer = AudioSystem.getMixer(mixerInfo);
            mixer.open();

            //we check only target type lines, because we are looking for "SPEAKER target port"
            for (Line.Info info : mixer.getTargetLineInfo()) {
                if (info.toString().contains("SPEAKER")) {
                    Line line = mixer.getLine(info);
                    try {
                        line.open();
                    } catch (IllegalArgumentException iae) {}
                    return (FloatControl) line.getControl(FloatControl.Type.VOLUME);
                }
            }
        }
    } catch (Exception ex) {
        System.out.println("problem creating volume control object:"+ex);
        throw ex;
    }
    throw new Exception("unknown problem creating volume control object");
}
     public void stateChange(float value){ // chỉnh volume theo giá trị của Slide
         try {
             getVolumeControl().setValue(value);
         } catch (Exception ex) {
             Logger.getLogger(VolumeChange.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

    public void getVolumeControl(float value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
