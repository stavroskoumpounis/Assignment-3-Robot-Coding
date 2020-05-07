package edu.lbic.csgp.tunnel.prototypes.v3;
/* 
 * Attempt at Object-oriented programming ¯\_(0_o)_/¯
 */
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import edu.lbic.csgp.tunnel.util.Utility;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {
	protected static Finch fin=new Finch();
	protected static HashMap<Double, String> tunnels;
	protected static ArrayList<Double> ranked;
	protected static final double FINCH_SPEED_CM_S= 12.27;
	private static double tunnel1,tunnel2,tunnel3;

	public static void main(String[] args){

		tunnelTestBegin();

		mapMyTunnels(); //create HashMap of Tunnel length connecting to String description of tunnel number

		rankTunnels(); //rank method ranks the tunnels by time(seconds)
		
		Display.printTestResults();
	}	
	/**
	 * Getter method to access the main Finch object.
	 * @return Finch Object
	 */
	protected static Finch getMainFinchObject(){ 
		return fin;
	}
	private static void tunnelTestBegin(){
		Utility.readyCheck("1st");

		tunnel1=TunnelTest.tunnelLength(); 

		Utility.readyCheck("2nd");

		tunnel2=TunnelTest.tunnelLength();

		Utility.readyCheck("3rd");

		tunnel3=TunnelTest.tunnelLength();
	}
	private static void rankTunnels(){

		ranked=new ArrayList<Double>(Arrays.asList(tunnel1,tunnel2,tunnel3));


		if (ranked.get(0) > ranked.get(1))
		{
			Collections.swap(ranked, 0, 1);
		}

		if (ranked.get(1) > ranked.get(2))
		{
			Collections.swap(ranked, 1, 2);
		}

		if (ranked.get(0) > ranked.get(1))
		{
			Collections.swap(ranked, 0, 1);
		}

	}
	protected static void mapMyTunnels(){
		tunnels = new HashMap<Double, String>(); 

		tunnels.put(tunnel1, "tunnel number 1" );
		tunnels.put(tunnel2, "tunnel number 2" );
		tunnels.put(tunnel3, "tunnel number 3" );
	}
}