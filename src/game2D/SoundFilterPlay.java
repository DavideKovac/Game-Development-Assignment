package game2D;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundFilterPlay extends Thread{

		String filename;	// The name of the file to play
		boolean finished;	// A flag showing that the thread has finished
		
		public SoundFilterPlay(String fname) {
			filename = fname;
			finished = false;
		}

		/**
		 * run will play the actual sound but you should not call it directly.
		 * You need to call the 'start' method of your sound object (inherited
		 * from Thread, you do not need to declare your own). 'run' will
		 * eventually be called by 'start' when it has been scheduled by
		 * the process scheduler.
		 * @return 
		 */
		public void run()
		{
				try {
					File file = new File(filename);
					AudioInputStream stream = AudioSystem.getAudioInputStream(file);
					AudioFormat	format = stream.getFormat();
					SoundFilter filter= new SoundFilter(stream);
					AudioInputStream f = new AudioInputStream(filter,format,stream.getFrameLength());
					DataLine.Info info = new DataLine.Info(Clip.class, format);

					Clip clip = (Clip)AudioSystem.getLine(info);
					clip.open(f);
					clip.start();
					Thread.sleep(100);
					while (clip.isRunning()) { Thread.sleep(100); }
					clip.close();
				}
				catch (Exception e) { finished=false;	}
				finished=true;
			
		}
}
