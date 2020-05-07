package edu.lbic.csgp.tunnel.prototypes.v3;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import edu.lbic.csgp.tunnel.util.Utility;

class TunnelTest{
	private static long entryTime,exitTime;
	private static double[] modif=new double[2]; //The left modifier is the 0th array element, and the right modifier is the 1st element.
	private static String place;
	private static int[] lightSensors0=new int[2]; //The left sensor is the 0th array element, and the right sensor is the 1st element.

	protected static double tunnelLength(){
		//Call for the main finch object is inputed as a parameter to improve readability in the methods used.
		tunnelEntranceDetection(Main.getMainFinchObject());

		tunnelExitDetection(Main.getMainFinchObject());

		return ((exitTime-entryTime)*1.0)/1000; //Calculates and returns the time Finch was in the tunnel(between entry and exit) in seconds
	}
	private static void tunnelEntranceDetection(Finch mFinch){
		place="Room";
		Display.standClear();

		getLight(mFinch); //gets and sets the light values from the sensors to the fields leftLightSensor0,rightLightSensor0,to check against bellow.
		Display.takeOff();
		mFinch.setWheelVelocities(100, 100); //sets Finch to move forward at the pace of 100 until the program ends/exits.

		while(true){
			if (mFinch.getLeftLightSensor()<(lightSensors0[0]*modif[0]) && mFinch.getRightLightSensor()<(lightSensors0[1]*modif[1])){ //if light values went down 40%
				entryTime=System.currentTimeMillis();  //gets the system's current time in milliseconds if the above ,if condition, is satisfied.
				mFinch.saySomething("Tunnel found");
				System.out.println("\n**Tunnel found**\n");
				break;
			}
		}
	}
	private static void tunnelExitDetection(Finch mFinch){
		place="Tunnel";

		getLight(mFinch);
		while(true){
			if (mFinch.getLeftLightSensor()>(lightSensors0[0]*(2-modif[0])) && mFinch.getRightLightSensor()>(lightSensors0[1]*(2-modif[1]))){ //if light values went up 40%
				exitTime=System.currentTimeMillis();
				mFinch.saySomething("Exit found");
				System.out.println("\n**Exit found**");
				break;
			}
		}
		Utility.finchStopComeBack(mFinch);
	}
	private static void getLight(Finch mFinch){

		lightSensors0 = mFinch.getLightSensors();

		System.out.println("*"+place+" scanned for light values*\n");

		lightSensitivityCalibration(0); //for left sensor

		lightSensitivityCalibration(1); //for right sensor

	}
	private static void lightSensitivityCalibration(int index){ 
		String brightness;
		double temp;

		if (lightSensors0[index]>=120){
			temp=0.6;
			brightness="Bright";
		}
		else if (lightSensors0[index]>=90 && lightSensors0[index]<120){
			temp=0.8;
			brightness="Medium";
		}
		else{
			temp=0.9;
			brightness="Dark";
		}

		if(place.equals("Room")){
			modif[index]=temp;
		}
		
		if(index==1){
			System.out.println(place+" brightness is <"+brightness+">");
			if (place.equals("Room")){
				Utility.threadPause(1000);
				System.out.println("*Light sensitivity modified*");
			}
		}
	}
}