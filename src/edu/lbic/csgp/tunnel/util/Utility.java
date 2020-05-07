package edu.lbic.csgp.tunnel.util;

import java.awt.Color;
import java.io.File;
import java.util.Random;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Utility {
	/**
	 * Gets the path of the project's directory, which can change if this program is used on another computer/system.
	 * @return String
	 */
	public static String getBasePath(){
		final String BASE_PATH = new File("").getAbsolutePath().replace("\\", "\\\\");
		return BASE_PATH;
	}
	public static void playfullRobotCalculating(Finch fin,String basePath){
		fin.saySomething("Calculating");
		System.out.println("\nCalculating intensely...");
		fin.sleep(1000);
		fin.playClip(basePath+"\\src\\R2D2-yeah.wav");

		finchRandomColor(fin);

		fin.sleep(800);
	}
	/**
	 * Makes the Finch playfully flash its nose like it's R2D2
	 */
	public static void finchRandomColor(Finch fin){
		Random ra = new Random();
		for (int i= 0;i<8;i++){
			int r=ra.nextInt(256),g=ra.nextInt(256),b=ra.nextInt(256);

			fin.setLED(r, g, b,180);
		}
	}
	public static void threadPause(long milliseconds){ 
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method for Finch to stop moving forward and come back in reverse
	 * @param mFinch Object
	 */
	public static void finchStopComeBack(Finch mFinch){
		mFinch.sleep(1500);
		mFinch.setLED(Color.RED);
		mFinch.stopWheels();
		mFinch.sleep(500);
		mFinch.setLED(Color.YELLOW);
		mFinch.setWheelVelocities(-200, -200, 3000);
		mFinch.setLED(Color.RED);
	}
	/**
	 * Method that makes Finch countdown from specified length
	 * @param mFinch
	 */
	public static void finchCountdown(Finch mFinch,int length) {
		System.out.println("Taking off in:");
		mFinch.saySomething("taking off",1000);
		System.out.println(length);
		mFinch.saySomething("in "+length,1000);
		for(int i = length-1; i > 0; i--){
			System.out.println(i);
			mFinch.saySomething(String.valueOf(i),1000);
		}
		mFinch.setLED(Color.GREEN);
	}
	/**
	 * Ready check validation method for pausing
	 * @param tunnelTestNo
	 */
	public static void readyCheck(String tunnelTestNo){
		Object[] options = { "Ready" };
		Object[] options2={"YES", "NO"};
		while(true){
			int choice=JOptionPane.showOptionDialog(null, "Click on Ready when you're ready to start the "+tunnelTestNo+" Tunnel Test", "Ready Check",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					null, options, options[0]);

			if (choice==0){
				int choice2=JOptionPane.showOptionDialog(null, "Are you sure?", "Readiness validation",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options2, options2[0]);
				if(choice2==0){
					break;
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please select Ready for Finch to start");
			}
		}
	}
}