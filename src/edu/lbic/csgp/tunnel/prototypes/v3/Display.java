package edu.lbic.csgp.tunnel.prototypes.v3;

import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

import edu.lbic.csgp.tunnel.util.Utility;

public class Display extends Main {
	private static int count=0;
	
	public static void printTestResults(){
		Utility.playfullRobotCalculating(fin,Utility.getBasePath());

		xTunnelWas("middle-sized",1);
		xTunnelWas("shortest",0);
		
		System.out.print("\nAnd the largest tunnel was :");
		fin.saySomething("And the largest tunnel was :");
		fin.sleep(700);
		fin.playClip(Utility.getBasePath()+"\\src\\drum.wav");
		fin.sleep(7000);
		fin.saySomething(tunnels.get(ranked.get(2)));
		System.out.print(tunnels.get(ranked.get(2)));
		printDistance(2);
		fin.sleep(2000);

		fin.saySomething("thank you for today",1600);

	}
	/**
	 * Prints the Tunnel distance in meters ,also formats the number to 2 floating points.
	 */
	private static void printDistance(int tunnelTimeIndex){
		DecimalFormat numf = new DecimalFormat("#.##");	
		//calculates distance in meters by multiplying speed and time (d=s*t)
		double distance=FINCH_SPEED_CM_S*ranked.get(tunnelTimeIndex);
		
		System.out.print(" (approximately "+numf.format(distance)+"cm long -- it took Finch "+numf.format(ranked.get(tunnelTimeIndex))+" seconds to travel through it)\n");
	}
	public static void xTunnelWas(String tunnel,int tunnelTimeIndex){
		System.out.print("\nThe "+tunnel+" tunnel was :"+tunnels.get(ranked.get(tunnelTimeIndex)));
		printDistance(tunnelTimeIndex);
		fin.saySomething("The "+tunnel+" tunnel was :"+tunnels.get(ranked.get(tunnelTimeIndex)));
		fin.sleep(3000);
	}
	/**
	 * Warns user to standClear so Finch can get an accurate light value reading
	 */
	public static void standClear(){
		fin.saySomething("stand clear for light test");
		fin.setLED(Color.red);
		JOptionPane.showMessageDialog(null, "Stand clear for light test");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	/**
	 * Method to set off a countdown before Finch can start moving
	 */
	public static void takeOff(){
		count++;
		System.out.println("\nTunnel Test #"+count+" commencing");
		Utility.threadPause(1000);
		Utility.finchCountdown(fin,3);
	}
}