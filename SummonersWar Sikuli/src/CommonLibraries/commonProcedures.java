package CommonLibraries;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class commonProcedures extends commonActions {

	public void setupBotResolution() throws Exception{		
    	focusApp("NoxPlayer");
    	if (!isExist(pathWindow+"lowerRightCornerBig.png",s)){
    		//resizeMobizen();
    	}  
	}
	
	public void runGB10(int loop, String dungeon) throws Exception{
		double passRate=0.0;
		double failRate=0.0;
		for (int Lp=1;Lp<=loop; Lp++) {
/*			if (isExist(pathComDun+"clickBattle.png")) {
				existClick(pathComDun+"clickBattle.png");
				existClick(pathComDun+"clickCairosDungeon.png");
				existClick(pathComDun+"clickGiantsKeep.png");
				getImageRegion(pathComDun+"giantB10Option.png").find(pathG10+"giantsBattle.png").click();;
			}
			existClick(pathComDun+"clickStartBattle.png");*/
			
/*			if(checkOrObjectExists(pathComDun+"clickPlay.png", pathComDun+"clickPause.png")==2){
				existClick(pathComDun+"clickPlay.png");
			}*/
			focusApp("NoxPlayer", true);
			if(!waitElementExists(pathComDun+"clickStartBattle.png", 5)) {;
				clickBackToIsland();
				selectDungeon(dungeon, dungeon);
				existClick(pathComDun+"clickStartBattle.png");
			} else {
				existClick(pathComDun+"clickStartBattle.png");
			}
			
			while(!waitElementExists(pathComDun+"clickPlaySpeed.png")) {
				System.out.println("Waiting for ClickPlaySpeed");
				if(isExist(pathComDun+"clickPlaySpeed.png")) {
					System.out.println("ClickPlaySpeed Detected");
				} else if(isExist(pathComDun+"unstableNetwork.png")){
					System.out.println("Unstable Network Message Detected");
					existClick(pathComDun+"yesUnstable.png");
				} else {
					if(isExist(pathComDun+"clickStartBattle.png")) {
						existClick(pathComDun+"clickStartbattle.png");
					}
				}

			}
			
/*			if(checkOrObjectExists(pathComDun+"networkIssue.png",pathComDun+"rewardImg.png")==1) {
				existClick(pathComDun+"yesNetworkIssue.png");
			}
			
			if(checkOrObjectExists(pathComDun+"networkIssue.png",pathComDun+"reviveImg.png")==1) {
				existClick(pathComDun+"yesNetworkIssue.png");
			}			*/
			
			
			int getCheckOrExist=0;			
			while(getCheckOrExist!=1||getCheckOrExist!=2) {
				getCheckOrExist=checkOrObjectExists(pathComDun+"reviveImg.png", pathComDun+"rewardImg.png");
				if(getCheckOrExist==1||getCheckOrExist==2) {
					break;
				} else {
					if(isExist(pathComDun+"networkIssue.png")) {
						System.out.println("Network Issue Detected");					
						existClick(pathComDun+"yesNetworkIssue.png");					
					}					
				}
			}
			
			
			if(getCheckOrExist==1){
				focusApp("NoxPlayer", true);
				runSuccess = runSuccess + 1;
				//setScreenRegion().wait(1.0);

				existClick(pathComDun+"rewardImg.png");
				existClick(pathComDun+"treasureBox.png");
				if(checkOrObjectExists(pathComDun+"clickSell.png", pathComDun+"nonRuneOK.png")==2){
					if(!sellOrGetRune(returnProperty("Runeset"), getCleanRuneDetails(getRuneRegionCoordinates()))){
						existClick(pathComDun+"clickSell.png");
						existClick(pathComDun+"yesSell.png");
					}
					else {
						existClick(pathComDun+"clickGet.png");
					}
				}
				else {
					existClick(pathComDun+"nonRuneOK.png");
				}
				clickReplay();
			}			
			else {
				runFailure = runFailure + 1;
				focusApp("NoxPlayer", true);
				existClick(pathComDun+"reviveNo.png");
				waitElementExists(pathComDun+"rewardImg.png");
				existClick(pathComDun+"rewardImg.png");
				clickReplay();
			}		
			totalRuns = Lp;
			writeToTextFile(logFile, "Success: " + runSuccess + "/" + totalRuns + "(" + (runSuccess/totalRuns)*100 + ")");
			writeToTextFile(logFile, "Failure: " + runFailure + "/" + totalRuns + "(" + (runFailure/totalRuns)*100 + ")");
		}
	}
	
	public static void clickReplay() throws Exception{
		System.out.println("[" + getCurrentDateTime() + "] - Start Click replay");
		if(isExist(pathComDun+"networkIssue.png")) {
			System.out.println("Network Issue Detected");					
			existClick(pathComDun+"yesNetworkIssue.png");					
		}				
		waitElementExists(pathComDun+"clickReplay.png");
		System.out.println("[" + getCurrentDateTime() + "] - Checked if waitElementExists for click replay is present");
		while(!isExist(pathComDun+"clickBattle.png")) {			
			existClick(pathComDun+"clickReplay.png");
			System.out.println("[" + getCurrentDateTime() + "] - Check if Refill is present");
			RefillEnergy();
			System.out.println("[" + getCurrentDateTime() + "] - Check if connection delayed is present");
			if(isExist(pathComDun+"networkConnectionDelayed.png")) {
				existClick(pathComDun+"confirmDelayed");
			}			
		
		}
		System.out.println("[" + getCurrentDateTime() + "] - exiting clickReplay method");
	}
	
	public static void RefillEnergy() throws Exception{
		if(isExist(pathComDun+"notEnoughEnergy")){
			focusApp("NoxPlayer", true);
			writeToTextFile(logFile, "Energy Refill");
			existClick(pathComDun+"yesRefill.png");
			existClick(pathComDun+"refillEnergy.png");
			existClick(pathComDun+"yesPurchaseEnergy.png");
			existClick(pathComDun+"OkPurchaseSuccessful.png");
			existClick(pathComDun+"closeShop.png");
			waitElementExists(pathComDun+"clickReplay.png");
			while(!isExist(pathComDun+"clickBattle.png")) {			
				existClick(pathComDun+"clickReplay.png");
			}
		}		
	}
	
	public void clickBackToIsland() throws Exception{
		//Upper Right Window Corner
		int menuOpX = s.find(pathWindow+"menuOptions.png").x;		
		int menuOpW = s.find(pathWindow+"menuOptions.png").w;
		int menuOpY = s.find(pathWindow+"menuOptions.png").y;
		int menuOpH = s.find(pathWindow+"menuOptions.png").h;	
		
		Region menuOptionRegion = new Region(menuOpX,menuOpY,menuOpW,menuOpH);			
		//menuOptionRegion.highlight();
		while(getMainRegion.exists(pathWindow+"battleOption.png")==null) {
			menuOptionRegion.find(pathWindow+"backButton.png").click();
		}
		if(getMainRegion.exists(pathWindow+"battleOption.png")!=null){
			getMainRegion.find(pathWindow+"battleOption.png").click();
			getMainRegion.find(pathWindow+"cairosOption.png").click();
		}		
	}
	
	
	public void selectDungeon(String level, String dun) throws Exception {
		Region returnRegion = null;
		if(dun.startsWith("GB")) {
			existClick(pathWindow+"giantsKeep.png");
			switch(level) {
				case "GB7":
					returnRegion = getRegionByImage(level);
					waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
					break;
				case "GB8":
					returnRegion = getRegionByImage(level);
					waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
					break;
				case "GB9":
					returnRegion = getRegionByImage(level);
					waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
					break;
				case "GB10":
					returnRegion = getRegionByImage(level);
					waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
					break;
			}
		} else if (dun.startsWith("DB")) {
			existClick(pathWindow+"dragonsLair.png");
			switch(level) {
			case "DB7":
				returnRegion = getRegionByImage(level);
				waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
				break;
			case "DB8":
				returnRegion = getRegionByImage(level);
				waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);				
				break;
			case "DB9":
				returnRegion = getRegionByImage(level);
				waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
				break;
			case "DB10":
				returnRegion = getRegionByImage(level);
				waitExistClick(pathWindow+"battleDungeon.png",returnRegion,10);
				break;
			}			
		}
	}
	
	public Region getRegionByImage(String fileName) throws Exception {
		Region returnRegion = null;
		int getRegionX = getMainRegion.find(pathWindow+fileName+".png").x;		
		int getRegionW = getMainRegion.find(pathWindow+fileName+".png").w;
		int getRegiony = getMainRegion.find(pathWindow+fileName+".png").y;
		int getRegionH = getMainRegion.find(pathWindow+fileName+".png").h;	
		
		returnRegion = new Region(getRegionX,getRegiony,getRegionW,getRegionH);
		//returnRegion.highlight();
		return returnRegion;
	}
	
	public void checkCrystalExist() throws Exception{
		
		if(isExist(pathComDun+"zairossStage.png")){
			commonActions.getLeftCrystal.exists(pathComDun+"leftCrystal.png");
		}
	}
	
	public int checkOrObjectExists(String image1, String image2) throws Exception{
		int endTypeIndicator=0;
/*		while (getMainRegion.exists(image1)==null && getMainRegion.exists(image2)==null) {
			//setScreenRegion().wait(1.0);
			//System.out.println(image1 + "--" + String.valueOf(setScreenRegion().exists(image1)));
			//System.out.println(image2 + "--" + String.valueOf(setScreenRegion().exists(image2)));
			focusApp("NoxPlayer", true);
		}*/
		if(getMainRegion.exists(image2)!=null){
			//setScreenRegion().find(image2).highlight();
			endTypeIndicator=1;
		}
		else if(getMainRegion.exists(image1)!=null){
			endTypeIndicator=2;
		}
		System.out.print(Integer.valueOf(endTypeIndicator));
		return endTypeIndicator;
	}
	
	public int checkOrObjectExists(String image1, String image2, String image3) throws Exception{
		int endTypeIndicator=0;
		while (getMainRegion.exists(image1)==null && getMainRegion.exists(image2)==null && getMainRegion.exists(image3)==null) {
			//setScreenRegion().wait(1.0);
			//System.out.println(image1 + "--" + String.valueOf(setScreenRegion().exists(image1)));
			//System.out.println(image2 + "--" + String.valueOf(setScreenRegion().exists(image2)));
			focusApp("NoxPlayer", true);
		}
		if(getMainRegion.exists(image2)!=null){
			//setScreenRegion().find(image2).highlight();
			endTypeIndicator=1;
		}
		else if(getMainRegion.exists(image1)!=null){
			endTypeIndicator=2;
		}
		else if(getMainRegion.exists(image3)!=null){
			endTypeIndicator=3;
		}		
		System.out.print(Integer.valueOf(endTypeIndicator));
		return endTypeIndicator;
	}
	
	public int checkOrObjectExists(String image1, String image2, Region r) throws Exception{
		int endTypeIndicator=0;
		while (getMainRegion.exists(image1)==null || getMainRegion.exists(image2)==null) {
			setScreenRegion().wait(1.0);
		}
		if(getMainRegion.exists(image2)!=null){
			//setScreenRegion().find(image2).highlight();
			endTypeIndicator=1;
		}
		else if(getMainRegion.exists(image1)!=null){
			endTypeIndicator=2;
		} else {
			endTypeIndicator=5;
		}
		System.out.print(Integer.valueOf(endTypeIndicator));
		return endTypeIndicator;
	}	
	

}
