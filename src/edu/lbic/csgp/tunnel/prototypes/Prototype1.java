package edu.lbic.csgp.tunnel.prototypes;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javax.swing.JOptionPane;

public class Prototype1 {
	private static Finch fin=new Finch();
	private static double modif=0.0;

	public static void main(String[] args) {

		fin.saySomething("stand clear",1500);
		fin.saySomething("need to test something",1800);
		fin.setLED(Color.red);
		JOptionPane.showMessageDialog(null, "Proceed with room light test");


		modif=LightCalibration(); //tests the light level of the room for potentially modifying the algorithms bellow

		fin.setLED(Color.green, 2000);
		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 1st Tunnel Test");

		fin.setWheelVelocities(100, 100);

		double tunnel1=TunnelTest(modif);

		fin.sleep(1500);
		fin.stopWheels();
		fin.setWheelVelocities(-200, -200, 5000);

		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 2nd Tunnel Test");
		fin.saySomething("stand clear for light test");
		fin.setLED(Color.red);
		JOptionPane.showMessageDialog(null, "Stand clear for light test");

		modif=LightCalibration();

		fin.setLED(Color.green, 2000);

		fin.setWheelVelocities(100, 100);

		double tunnel2=TunnelTest(modif);

		fin.sleep(1500);
		fin.stopWheels();
		fin.setWheelVelocities(-200, -200, 5000);

		JOptionPane.showMessageDialog(null, "Click OK when you're ready to start the 3rd Tunnel Test");
		fin.saySomething("stand clear for light test");
		fin.setLED(Color.red);
		JOptionPane.showMessageDialog(null, "Stand clear for light test");

		modif=LightCalibration();

		fin.setLED(Color.green, 2000);

		fin.setWheelVelocities(100, 100);

		double tunnel3=TunnelTest(modif);

		fin.sleep(1500);
		fin.stopWheels();
		fin.setWheelVelocities(-200, -200, 5000);

		HashMap<Double, String> tunnels = new HashMap<Double, String>();
		tunnels.put(tunnel1, "tunnel number 1" );
		tunnels.put(tunnel2, "tunnel number 2" );
		tunnels.put(tunnel3, "tunnel number 3" );

		fin.saySomething("Calculating");
		System.out.println("\nCalculating intensely...");
		fin.sleep(1000);
		fin.playClip("C:\\Users\\STAV\\Documents\\uni\\MODULES\\NC1600B Group Project S2\\finchwavs\\R2D2-yeah.wav");

		Random ra = new Random();
		for (int i= 0;i<8;i++){
			int r=ra.nextInt(256),g=ra.nextInt(256),b=ra.nextInt(256);

			fin.setLED(r, g, b,180);
		}

		fin.sleep(800);




		ArrayList<Double> ranked=Rank(tunnel1,tunnel2,tunnel3);



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
		//		fin.saySomething("leave me alone now malaka",2000);			





	}
	private static double TunnelTest(double modif){
		int lefty,righty;
		long start,end=0;
		double i=110;
		i=i-(i*modif);


		while(true){


			lefty =fin.getLeftLightSensor();
			righty =fin.getRightLightSensor();

			if (lefty<(lefty-(lefty*0.4)) && righty<righty-(righty*0.4)){
				start=System.currentTimeMillis();
				fin.saySomething("Tunnel found");
				break;
			}
		}
		while(true){

			lefty =fin.getLeftLightSensor();
			righty =fin.getRightLightSensor();

			if (lefty>i && righty>i){
				end=System.currentTimeMillis();
				break;
			}
		}
		long total=end-start;
		double secs=(total*1.0)/1000;

		//System.out.println("tunel seconds: "+(secs));

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
	private static double LightCalibration(){

		int[] sensors=fin.getLightSensors();
		int avgsens = (sensors[0]+sensors[1])/2;

		if (avgsens>=120 && avgsens<=160){
			modif=0.05;
		}
		else if (avgsens>=90 && avgsens<120){
			modif=0.2;
		}
		else if (avgsens<90){
			modif=0.3;
		}
		return modif;

	}

}