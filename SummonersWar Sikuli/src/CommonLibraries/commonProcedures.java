package CommonLibraries;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class commonProcedures extends commonActions {

	public void setupBotResolution() throws Exception{		
    	focusApp("Mobizen Mirroring");
    	if (!isExist(pathWindow+"lowerRightCornerBig.png",s)){
    		resizeMobizen();
    	}  
	}
	
	public void runGB10(int loop) throws Exception{
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
			focusApp("Mobizen Mirroring", true);
			waitElementExists(pathComDun+"clickStartBattle.png");
			existClick(pathComDun+"clickStartBattle.png");
			
			if(checkOrObjectExists(pathComDun+"reviveImg.png", pathComDun+"rewardImg.png")==1){
				focusApp("Mobizen Mirroring", true);
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
				waitElementExists(pathComDun+"clickReplay.png");
				existClick(pathComDun+"clickReplay.png");
				if(!isExist(pathComDun+"clickBattle.png")){
					existClick(pathComDun+"clickReplay.png");
				}
			}
			else {
				runFailure = runFailure + 1;
				focusApp("Mobizen Mirroring", true);
				existClick(pathComDun+"reviveNo.png");
				existClick(pathComDun+"rewardImg.png");
				existClick(pathComDun+"clickReplay.png");
				if(!isExist(pathComDun+"clickBattle.png")){
					existClick(pathComDun+"clickReplay.png");
				}
			}
			if(isExist(pathComDun+"notEnoughEnergy")){
				focusApp("Mobizen Mirroring", true);
				writeToTextFile(logFile, "Energy Refill");
				existClick(pathComDun+"yesRefill.png");
				existClick(pathComDun+"refillEnergy.png");
				existClick(pathComDun+"yesPurchaseEnergy.png");
				existClick(pathComDun+"OkPurchaseSuccessful.png");
				existClick(pathComDun+"closeShop.png");
				existClick(pathComDun+"clickReplay.png");
				if(!isExist(pathComDun+"clickBattle.png")){
					existClick(pathComDun+"clickReplay.png");
				}
			}		
			totalRuns = Lp;
			writeToTextFile(logFile, "Success: " + runSuccess + "/" + totalRuns + "(" + (runSuccess/totalRuns)*100 + ")");
			writeToTextFile(logFile, "Failure: " + runFailure + "/" + totalRuns + "(" + (runFailure/totalRuns)*100 + ")");
		}
	}
	
	public void checkCrystalExist() throws Exception{
		
		if(isExist(pathComDun+"zairossStage.png")){
			commonActions.getLeftCrystal.exists(pathComDun+"leftCrystal.png");
		}
	}
	
	public int checkOrObjectExists(String image1, String image2) throws Exception{
		int endTypeIndicator=0;
		while (getMainRegion.exists(image1)==null && getMainRegion.exists(image2)==null) {
			//setScreenRegion().wait(1.0);
			//System.out.println(image1 + "--" + String.valueOf(setScreenRegion().exists(image1)));
			//System.out.println(image2 + "--" + String.valueOf(setScreenRegion().exists(image2)));
			focusApp("Mobizen Mirroring", true);
		}
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
		}
		System.out.print(Integer.valueOf(endTypeIndicator));
		return endTypeIndicator;
	}
	

}
