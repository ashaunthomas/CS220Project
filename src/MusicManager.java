import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicManager {
	
	private AudioInputStream BGMStream = null;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip clip = null;
	File[] tracks = new File[3];
    private int currentTrack = 0;
	
	MusicManager(){
		loadMusic();
	}
	
	public void play(int trackNumber){

			if(clip != null && clip.isActive()){
				clip.stop();
			}

		    try {
				BGMStream = AudioSystem.getAudioInputStream(tracks[trackNumber]);
			} catch (UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    format = BGMStream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    try {
				clip = (Clip) AudioSystem.getLine(info);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				clip.open(BGMStream);
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    clip.start();
		    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void mute(){
		clip.stop();
	}
	
	public void next(){
		currentTrack = (currentTrack+1 >= tracks.length)?(0):(++currentTrack);
		
		play(currentTrack);
	}
	
	private void loadMusic(){
		for(int i = 0; i < tracks.length; i++){
			tracks[i] = new File("Music\\Track" + i + ".wav");
		}
	}
}
