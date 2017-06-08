package MainExecutor;
import org.sikuli.script.*;
import java.awt.Robot;
import java.awt.event.*;
import CommonLibraries.commonActions;
import CommonLibraries.commonProcedures;

public class StartSikuliExecution extends commonActions{
	static Screen s = new Screen();
	static commonProcedures getComProcs = new commonProcedures();
	static Robot test;
        public static void main(String[] args) throws Exception{            
        	getComProcs.setupBotResolution();
        	focusApp("Mobizen Mirroring");
        	initializeRegions();
        	//getCleanRuneDetails(getRuneRegionCoordinates());
        	//getRuneRegionCoordinates();
//        	if(!sellOrGetRune(returnProperty("Runeset"), getCleanRuneDetails(getRuneRegionCoordinates()))){
//				existClick(pathComDun+"clickSell.png");
//				existClick(pathComDun+"yesSell.png");
//			}
//			else {
//				existClick(pathComDun+"clickGet.png");
//			}        	
        	String dungeon = returnProperty("DungeonToRun").toString();
        	switch (dungeon) {
	        	case "GB10":
	        		getComProcs.runGB10(Integer.valueOf(returnProperty("DungeonLoop")));
	        		break;
	        	case "DB10":
	        		
	        	case "NB10":
	        	
	        	case "Faimon":
	        	
	        	case "SD":
	        	
	        	default:
	        		System.out.println("Under Construction");
        	}
        	
/*        	String getRunes = returnProperty("Runeset");    	        	
        	boolean getRune = sellOrGetRune(getRunes, 
        			getCleanRuneDetails(getRuneRegionCoordinates()));
        	//System.out.println(String.valueOf(getRune));
        	if(getRune!=true){
        		existClick(pathComDun+"clickSell.png");
        		existClick(pathComDun+"yesSell.png");
        	}*/
        }
        
     
        
 
        

}
