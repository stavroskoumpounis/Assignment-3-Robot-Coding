package edu.lbic.csgp.tunnel.prototypes;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

public class Prototype2 {
	private static Finch fin=new Finch();

	public static void main(String[] args) {

		fin.saySomething("stand clear",1500);

		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 1st Tunnel Test");

		fin.setWheelVelocities(100, 100);

		double tunnel1=TunnelTest(); //This is the first tunnel test , the modifier from the light calibration enters as a parameter to modify values if needed

		finstopcomeback(); //method for Finch to stop moving forward and come back in reverse

		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 2nd Tunnel Test");

		fin.setWheelVelocities(100, 100);

		double tunnel2=TunnelTest();

		finstopcomeback();

		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 3rd Tunnel Test");

		fin.setWheelVelocities(100, 100);

		double tunnel3=TunnelTest();

		finstopcomeback();

		HashMap<Double, String> tunnels = new HashMap<Double, String>();
		tunnels.put(tunnel1, "tunnel number 1" );
		tunnels.put(tunnel2, "tunnel number 2" );
		tunnels.put(tunnel3, "tunnel number 3" );

		ArrayList<Double> ranked=Rank(tunnel1,tunnel2,tunnel3);

		PrintTunnelResults(ranked,tunnels);

	}
	private static double TunnelTest(){
		int lefty,righty;
		long start,end=0;

		lefty =fin.getLeftLightSensor();
		righty =fin.getRightLightSensor();
		while(true){



			if (fin.getLeftLightSensor()<(lefty-(lefty*0.4)) && fin.getRightLightSensor()<(righty-(righty*0.4))){
				start=System.currentTimeMillis();
				fin.saySomething("Tunnel found");
				break;
			}
		}
		lefty =fin.getLeftLightSensor();
		righty =fin.getRightLightSensor();
		while(true){


			if (fin.getLeftLightSensor()>(lefty+(lefty*0.4)) && fin.getRightLightSensor()>(righty+(righty*0.4))){
				end=System.currentTimeMillis();
				fin.saySomething("I found the Light");
				break;
			}
		}
		long total=end-start;
		double secs=(total*1.0)/1000;

		return secs;

	}

	private static ArrayList<Double> Rank(double tun1,double tun2,double tun3){

		ArrayList<Double> rank=new ArrayList<Double>(Arrays.asList(tun1,tun2,tun3));


		if (rank.get(0) > rank.get(1))
		{
			Collections.swap(rank, 0, 1);
		}

		if (rank.get(1) > rank.get(2))
		{
			Collections.swap(rank, 1, 2);
		}

		if (rank.get(0) > rank.get(1))
		{
			Collections.swap(rank, 0, 1);
		}

		return rank;
	}
	public static void finstopcomeback(){

		fin.sleep(1500);
		fin.stopWheels();
		fin.setWheelVelocities(-200, -200, 3000);
	}

	public static void finrandcolor(){
		Random ra = new Random();
		for (int i= 0;i<8;i++){
			int r=ra.nextInt(256),g=ra.nextInt(256),b=ra.nextInt(256);

			fin.setLED(r, g, b,180);
		}
	}

	private static void PrintTunnelResults(ArrayList<Double> ranked, HashMap<Double, String> tunnels){
		fin.saySomething("Calculating");
		System.out.println("\nCalculating intensely...");
		fin.sleep(1000);
		fin.playClip("C:\\Users\\STAV\\Documents\\uni\\MODULES\\NC1600B Group Project S2\\finchwavs\\R2D2-yeah.wav");

		finrandcolor(); // method to make the finch playfully flash its nose like it's R2D2

		fin.sleep(800);

		System.out.println("\nThe middle-sized tunnel was :"+tunnels.get(ranked.get(1)));
		fin.saySomething("The middle-sized tunnel was :"+tunnels.get(ranked.get(1)));
		fin.sleep(3000);
		System.out.println("\nThe shortest tunnel was :"+tunnels.get(ranked.get(0)));
		fin.saySomething("The shortest tunnel was :"+tunnels.get(ranked.get(0)));
		fin.sleep(3000);
		System.out.print("\nAnd the largest tunnel was :");
		fin.saySomething("And the largest tunnel was :");
		fin.sleep(700);
		fin.playClip("C:\\Users\\STAV\\Documents\\uni\\MODULES\\NC1600B Group Project S2\\finchwavs\\drum.wav");
		fin.sleep(7000);
		fin.saySomething(""+tunnels.get(ranked.get(2)));
		System.out.print(""+tunnels.get(ranked.get(2)));
		fin.sleep(2000);

		fin.saySomething("wa ba la ba dub dub",1600);

	}
}