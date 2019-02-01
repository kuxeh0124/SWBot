package CommonLibraries;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.sikuli.basics.Settings;
import org.sikuli.script.*;

public class commonActions {
	public static String pathComDun = "imgs/CommonDungeon/";
	public static String pathG10 = "imgs/Giants/";
	public static String pathRune = "imgs/Runes/";	
	public static String pathWindow = "imgs/Window/";
	public static Screen s = new Screen();
	static LocalDateTime currentTime = LocalDateTime.now();
	static String getDate = String.valueOf(currentTime).replace("-", "").replaceAll(":", "").replace(".","");
	public static String logFile = "SWExecution" + getDate;
	public static Region getMainRegion = null;
	public static Region getRuneRegion = null;
	public static Region getRuneTitle = null;
	public static Region getRuneMainStat = null;
	public static Region getRuneMainSubStat = null;
	public static Region getRuneSub1 = null;
	public static Region getRuneSub2 = null;
	public static Region getRuneSub3 = null;
	public static Region getRuneSub4 = null;
	public static Region getLeftCrystal = null;
	public static Region getRightCrystal = null;
	public static Screen scr;
	public int runSuccess = 0;
	public int runFailure = 0;
	public int totalRuns = 0;
/*	public static void setScreenRegion() throws Exception{		
		setRegion.highlight();
	}*/
	
	public static String getCleanRuneDetails(Region[] getRegion ) throws Exception{
		Settings.OcrTextRead=true;
		String returnRune = "";
		String fullRuneName = "";
		Region runeRg = getRuneRegion;
		Region runeTitle = getRuneTitle;
		Region runeMainStat = getRuneMainStat;
		Region runeMainSubStat = getRuneMainSubStat;
		Region runeSub1 = getRuneSub1;
		Region runeSub2 = getRuneSub2;
		Region runeSub3 = getRuneSub3;
		Region runeSub4 = getRuneSub4;
		
		
		//Debugging
/*		getMainRegion.highlight();
		runeRg.highlight();
		runeTitle.highlight();
		runeMainStat.highlight();
		runeMainSubStat.highlight();
		runeSub1.highlight();
		runeSub2.highlight();
		runeSub3.highlight();
		runeSub4.highlight();*/
		
		String rTitle = EvaluateRuneName(runeTitle.text());
		String rClass = EvaluateRuneClass(runeTitle.text(), 
				runeSub1.text(), runeSub2.text(), runeSub3.text(), runeSub4.text());	
		String rPos = EvaluateRunePosition(runeTitle.text());
		
		String rMainStat = EvaluateRuneStats(runeMainStat.text());
		String rMainSubStat = EvaluateRuneStats(runeMainSubStat.text());
		String rSub1 = EvaluateRuneStats(runeSub1.text());
		String rSub2 = EvaluateRuneStats(runeSub2.text());
		String rSub3 = EvaluateRuneStats(runeSub3.text());
		String rSub4 = EvaluateRuneStats(runeSub4.text());
		
		String rTitleAct = (runeTitle.text());
		//String rClassAct = (runeTitle.text(), runeSub1.text(), runeSub2.text(), runeSub3.text(), runeSub4.text();	
		String rPosAct = (runeTitle.text());
		
		String rMainStatAct = (runeMainStat.text());
		String rMainSubStatAct = (runeMainSubStat.text());
		String rSub1Act = (runeSub1.text());
		String rSub2Act = (runeSub2.text());
		String rSub3Act = (runeSub3.text());
		String rSub4Act = (runeSub4.text());		
		
		System.out.println(rTitle + "|" + rClass + "|" + rPos
							+ "|" + rMainStat + "|" + rMainSubStat
							+ "|" + rSub1 + "|" + rSub2
							+ "|" + rSub3 + "|" + rSub4);
		returnRune = rTitle + "|" + rClass + "|" + rPos
				+ "|" + rMainStat + "|" + rMainSubStat
				+ "|" + rSub1 + "|" + rSub2
				+ "|" + rSub3 + "|" + rSub4;
		
		System.out.println(rTitleAct + "|" + rPosAct
				+ "|" + rMainStatAct + "|" + rMainSubStatAct
				+ "|" + rSub1Act + "|" + rSub2Act
				+ "|" + rSub3Act + "|" + rSub4Act);

		if(returnRune.contains("Can't Identify")){
			takeScreenshot();
			fullRuneName = runeTitle.text() + "|" + runeMainStat.text() + 
						   "|" + runeMainSubStat.text() + "|" + 
						   "|" + runeSub1.text() + "|" + 
						   "|" + runeSub2.text() + "|" + 
						   "|" + runeSub3.text() + "|" + 
						   "|" + runeSub4.text();
			writeToTextFile(logFile, fullRuneName);
			
		}
		return returnRune;
	}

	private static String EvaluateRuneName(String runeName) throws Exception{
		if(runeName.contains("Rare") && runeName.contains("UR") && runeName.contains("—l-I")){
			return "Destroy";
		}
		else if(runeName.contains("Destory")||runeName.contains("Destroy")){
			return "Destroy";
		}		
		else if(runeName.contains("Vampire")){
			return "Vampire";
		}			
		else if (runeName.contains("Will")||runeName.contains("will")){
			return "Will";
		}
		else if (runeName.contains("Nemesis")){
			return "Nemesis";
		}		
		else if (runeName.contains("Rage")){
			return "Rage";
		}		
		else if (runeName.contains("Endure")){
			return "Endure";
		}
		else if (runeName.contains("Violent")){
			return "Violent";
		}
		else if (runeName.contains("Shield")){
			return "Shield";
		}
		else if (runeName.contains("Focus")){
			return "Focus";
		}		
		else if (runeName.contains("Blade")){
			return "Blade";
		}
		else if (runeName.contains("Swift")){
			return "Swift";
		}
		else if (runeName.contains("Ener")){
			return "Energy";
		}		
		else if (runeName.contains("Fatal")){
			return "Fatal";
		}	
		else if (runeName.contains("Despair")){
			return "Despair";
		}		
		else if (runeName.contains("Revenge")){
			return "Revenge";
		}			
		else if (runeName.contains("Guard")){
			return "Guard";
		}			
		else if(runeName.contains("wolent")){
			return "Violent";
		}		
		else {
				return "None";
		}
	}
	
	private static String EvaluateRuneClass(
			String runeName,
			String stat1, String stat2, String stat3, String stat4) throws Exception{
		String runeClass="";
		if(runeName.contains("Rare") && runeName.contains("UR") && runeName.contains("—l-I")){
			return "Rare";
		}
		else if(runeName.contains("Rare")){
			return "Rare";
		}
		else if(runeName.contains("Hero")){
			return "Hero";
		}	
		else if(runeName.contains("Legend")){
			return "Legend";
		}	
		else if(runeName.contains("Normal")){
			return "Normal";
		}
		else {
			if((stat1.length())!=0){
				runeClass="Magic";
			}
			
			if(stat2.length()!=0){
				runeClass="Rare";
			}
			
			if(stat3.length()!=0){
				runeClass="Hero";
			}
			
			if(stat4.length()!=0){
				runeClass="Legend";
			}
			
			if(runeClass==""){
				runeClass="Normal";
			}
			return runeClass;
		}
	}
	
	private static String EvaluateRunePosition (String runeName) throws Exception{
		if(runeName.contains("Rare") && runeName.contains("UR") && runeName.contains("—l-I")){
			return "Cant Determine Position";
		}
		else if(runeName.contains("1")){
			return "1";
		}
		else if(runeName.contains("2")){
			return "2";
		}	
		else if(runeName.contains("3")){
			return "3";
		}		
		else if(runeName.contains("4")){
			return "4";
		}		
		else if(runeName.contains("5")){
			return "5";
		}		
		else if(runeName.contains("6")){
			return "6";
		}			
		else {
			return "None";
		}
	}	
	
	private static String EvaluateRuneMainStat(String runeMainStat) throws Exception{
		switch (runeMainStat){
		case "ATK+15":
			return "1";
		case "ATK+22":
			return "1";
		default:
			return "Can't Determine";
		}
	}	
	
	public static String EvaluateRuneStats(String runeStat) throws Exception{
		if(runeStat.contains("HP") && runeStat.contains("96")){
			runeStat = runeStat.replace("96", "%");
		}
		if(runeStat.contains("ATK") && runeStat.contains("96")){
			runeStat = runeStat.replace("96", "%");
		}
		if(runeStat.contains("CRlRate")){
			runeStat = runeStat.replace("CRlRate", "CRIRate");
		}
		if((runeStat.contains("Accuracy+")|| runeStat.contains("Accuracv+") || runeStat.contains("v+") || runeStat.contains("Accuracv")) 
				&& runeStat.contains("96")) {
			runeStat = runeStat.replace("96", "%");
		}
		if(runeStat.contains("v+")){
			runeStat = runeStat.replace("v+", "Accuracy+");
		}
		if(runeStat.contains("Drng")){
			runeStat = runeStat.replace("Drng", "Dmg");
		}
		if(runeStat.contains("Dmsz")){
			runeStat = runeStat.replace("Dmsz", "Dmg");
		}
		if(runeStat.contains("Dmq")){
			runeStat = runeStat.replace("Dmq", "Dmg");
		}		
		if(runeStat.contains("Dma")){
			runeStat = runeStat.replace("Dma", "Dmg");
		}		
		if(runeStat.contains("_L")){
			runeStat = runeStat.replace("_L", "+");
		}
		if(runeStat.contains("96")){
			runeStat = runeStat.replace("96", "%");
		}
		if(runeStat.contains("0/u")){
			runeStat = runeStat.replace("0/u", "%");
		}
		if(runeStat.contains("°/a")){
			runeStat = runeStat.replace("°/a", "%");
		}
		if(runeStat.contains("BPD")){
			runeStat = runeStat.replace("BPD", "SPD");
		}
		if(runeStat.contains("J.-In")){
			runeStat = runeStat.replace("J.-In", "+");
		}
		if(runeStat.contains("wolent")){
			runeStat = runeStat.replace("wolent", "Violent");
		}
		if(runeStat.contains("\"/c")){
			runeStat = runeStat.replace("\"/c", "%");
		}
		if(runeStat.contains("\"/o")){
			runeStat = runeStat.replace("\"/o", "%");
		}		
		if(runeStat.contains("“/o")) {
			runeStat = runeStat.replace("“/o", "%");			
		}
		if(runeStat.contains("”/o")) {
			runeStat = runeStat.replace("”/o", "%");			
		}					
		if(runeStat.contains("°/o")){
			runeStat = runeStat.replace("°/o", "%");
		}		
		if(runeStat.contains("°/0")){
			runeStat = runeStat.replace("°/0", "%");
		}						
		if(runeStat.contains("°/.")){
			runeStat = runeStat.replace("°/.", "%");
		}		
		if(runeStat.contains("-I-")){
			runeStat = runeStat.replace("-I-", "+");
		}				
		if(runeStat.contains("_H1 lP")){
			runeStat = runeStat.replace("_H1 lP", "HP");
		}			
		if(runeStat.contains("cmDmg +3")) {
			runeStat = runeStat.replace("cmDmg +3", "CRIDmg+8%");
		}
		if(runeStat.contains("cmDmg")) {
			runeStat = runeStat.replace("cmDmg", "CRIDmg");
		}		
		if(runeStat.contains("\"/u")) {
			runeStat = runeStat.replace("\"/u", "%");
		}
		if(runeStat.contains("HeS|SIal'lC€")) {
			runeStat = runeStat.replace("HeS|SIal'lC€", "Resistance");
		}
		if(runeStat.contains("LJHIUmg")) {
			runeStat = runeStat.replace("LJHIUmg", "CRIDmg");
		}
		if(runeStat.length()==0){
			runeStat = "None";
		}		
		if(runeStat.contains("‘7o")) {
			runeStat = runeStat.replace("‘7o", "%");			
		}
		if(runeStat.contains("‘7o")) {
			runeStat = runeStat.replace("‘7o", "%");			
		}
		if(runeStat.contains("Hl\"")) {
			runeStat = runeStat.replace("Hl\"", "HP");
		}		
		if(runeStat.contains("°/o")) {
			runeStat = runeStat.replace("°/o", "%");
		}						
		if(runeStat.contains("UHIHale")) {
			runeStat = runeStat.replace("UHIHale", "CRIRate");
		}				
		if(runeStat.contains("/-\\lK")) {
			runeStat = runeStat.replace("/-\\lK", "ATK");
		}		
		runeStat = runeStat.replace("\n", "").replace("\r", "");
		return runeStat;
		
	}
	
	public static void focusApp(String AppName) throws Exception {
		App.focus(AppName);		
		//existClick(pathComDun+"tvOkayButton.png");
		//setScreenRegion().click(pathWindow+"upperLeftCorner.png");
	}
	
	public static void focusApp(String AppName, boolean tvTrue) throws Exception {
		App.focus(AppName);
		//if(tvTrue){
		//	s.click(pathComDun+"phoneOptions.png");
		//}
	}
	//TODO: This has to be refined
	//Any|Any|Any|ATK*%|Any|SPD|CRIRate|CRIDmg|Any
	public static boolean sellOrGetRune(String runeParams, String actualRune) {	
		String[] eachParam = runeParams.split("\\:");
		String[] actualRuneArr = actualRune.split("\\|");
		int getARLen = actualRuneArr.length-1;
		int getEachParamLen = eachParam.length-1;
		int matchCtr = 0;
		int forStarter=0;
		boolean retVal=false;
		String getRune = "";
		if(actualRune.contains("Can't Identify")){
			forStarter=getEachParamLen+1;
			retVal = true;
		}
		for (int r=forStarter; r<=getEachParamLen; r++){	
			matchCtr = 0;
			//System.out.println(String.valueOf(r));
			String[] runeParamsArr = eachParam[r].split("\\|");	
			int getLen = (runeParamsArr.length)-1;
			for (int i=0; i<=getLen; i++){
				if(runeParamsArr[i].equalsIgnoreCase("any")){
					matchCtr = matchCtr+1;
				}
				else if(runeParamsArr[i].contains("*") && i<=4){
					String[] getStatVal = runeParamsArr[i].split("\\*");							
					if(actualRuneArr[i].contains(getStatVal[0]) && 
							actualRuneArr[i].contains(getStatVal[1])){
						matchCtr = matchCtr+1;
					}
				}
				else if (actualRuneArr[i].contains(runeParamsArr[i])){
					matchCtr = matchCtr+1;
				}
				else if (i>4) {					
					for (int y=5; y<=getLen; y++){
						if(runeParamsArr[i].contains("*")) {
							String[] getStatVal2 = runeParamsArr[i].split("\\*");
							if(actualRuneArr[y].contains(getStatVal2[0]) && 
									actualRuneArr[y].contains(getStatVal2[1])){
								matchCtr = matchCtr+1;
								break;
							}			
						}						
						else if (actualRuneArr[y].contains(runeParamsArr[i])){
							matchCtr = matchCtr+1;
							break;
						}
					}
				}
			}
			if(matchCtr==getLen+1){
				retVal = true;
				break;
			}
			else{
				retVal = false;
			}
		}
		if(retVal){
			getRune = "get rune";
		}
		else {
			getRune = "sell rune";
		}
		writeToTextFile(logFile, actualRune +"--"+ getRune);
		return retVal;
	}
	
	public static void initializeRegions() throws Exception{
		setScreenRegion();
		getRuneRegionCoordinates();
		//getCrystalCoordinatesZaiross();
	}
	
	public static Region setScreenRegion() throws Exception{			
		
		//Upper Right Window Corner
		int uRRWx = s.find(pathWindow+"upperRightCorner.png").x;
		int uRRWw = s.find(pathWindow+"upperRightCorner.png").w;
		
		//Upper Left Window Corner
		int uLRWx = s.find(pathWindow+"upperLeftCorner.png").x;
		int uLRWy = s.find(pathWindow+"upperLeftCorner.png").y;
		int uLRWh = s.find(pathWindow+"upperLeftCorner.png").h;
		
		//Bottom Right Window Corner
		int bRRWx = s.find(pathWindow+"lowerRightCornerBig.png").x;
		int bRRWy = s.find(pathWindow+"lowerRightCornerBig.png").y;
		int bRRWh = s.find(pathWindow+"lowerRightCornerBig.png").h;

		
		//Get left most corner coordinate
		int regionX = uLRWx;
		int regionY = uLRWy+uLRWh;
		int regionH = (bRRWy+bRRWh)-regionY;
		int regionW = bRRWx-regionX;

		
		Region mobScreen = new Region(regionX,regionY,regionW,regionH);		
		//mobScreen.highlight();
		getMainRegion = mobScreen;
		return mobScreen;
	}
	
	public static Region[] getCrystalCoordinatesZaiross() throws Exception{
		Region returnRegion[];
		//Upper Right Rune Window Corner
/*		int uRRWx = setScreenRegion().find(pathRune+"upperRightRuneWindow.png").x;
		int uRRWw = setScreenRegion().find(pathRune+"upperRightRuneWindow.png").w;
		
		//Upper Left Rune Window Corner
		int uLRWx = setScreenRegion().find(pathRune+"upperLeftRuneWindow.png").x;
		int uLRWy = setScreenRegion().find(pathRune+"upperLeftRuneWindow.png").y;
		
		//Bottom Left Rune Window Corner
		int bLRWy = setScreenRegion().find(pathRune+"bottomLeftRuneWindow.png").y;
		int bLRWh = setScreenRegion().find(pathRune+"bottomLeftRuneWindow.png").h;*/
		
		int xSSR = getMainRegion.x;
		int ySSR = getMainRegion.y;
		int hSSR = getMainRegion.h;
		int wSSR = getMainRegion.w;
		
		
		//Get left most corner coordinate
/*		int regionX = uLRWx;
		int regionY = uLRWy;
		int regionH = (bLRWy+bLRWh)-regionY;
		int regionW = (uRRWx+uRRWw)-regionX;*/
		
		int regionX = xSSR;
		int regionY = ySSR;
		int regionH = hSSR;
		int regionW = wSSR;
		
		returnRegion = new Region[2];
		
		Region regionRightCrystal = new Region (regionX+985, regionY+150, regionW-1230, regionH-520);		
		Region regionLeftCrystal = new Region (regionX+620, regionY+100, regionW-1250, regionH-580);
		
		//regionRightCrystal.highlight();
		//regionLeftCrystal.highlight();
		
		return returnRegion;
		
	}
	
	public static Region[] getRuneRegionCoordinates() throws Exception{
		Region returnRegion[];
		//Upper Right Rune Window Corner
/*		int uRRWx = setScreenRegion().find(pathRune+"upperRightRuneWindow.png").x;
		int uRRWw = setScreenRegion().find(pathRune+"upperRightRuneWindow.png").w;
		
		//Upper Left Rune Window Corner
		int uLRWx = setScreenRegion().find(pathRune+"upperLeftRuneWindow.png").x;
		int uLRWy = setScreenRegion().find(pathRune+"upperLeftRuneWindow.png").y;
		
		//Bottom Left Rune Window Corner
		int bLRWy = setScreenRegion().find(pathRune+"bottomLeftRuneWindow.png").y;
		int bLRWh = setScreenRegion().find(pathRune+"bottomLeftRuneWindow.png").h;*/
		
		int xSSR = getMainRegion.x+265;
		int ySSR = getMainRegion.y+118;
		int hSSR = getMainRegion.h-175;
		int wSSR = getMainRegion.w-532;
		
		
		//Get left most corner coordinate
/*		int regionX = uLRWx;
		int regionY = uLRWy;
		int regionH = (bLRWy+bLRWh)-regionY;
		int regionW = (uRRWx+uRRWw)-regionX;*/
		
		int regionX = xSSR;
		int regionY = ySSR;
		int regionH = hSSR;
		int regionW = wSSR;
		
		returnRegion = new Region[8];
		
		Region runeRg = new Region(regionX, regionY, regionW, regionH);		
		Region runeTitle = new Region(regionX+0, regionY+10, regionW-50, 30);
		Region runeMainStat = new Region(regionX+(regionW/4)+0, regionY+(regionH/6)-8, regionW-(regionW/4)-130, 27);
		Region runeMainSubStat = new Region(regionX+(regionW/4)+0, regionY+(regionH/6)+25, regionW-(regionW/4)-130, 27);
		Region runeSub1 = new Region(regionX+18, regionY+(regionH/6)+55, regionW-200, 23);
		Region runeSub2 = new Region(regionX+18, regionY+(regionH/6)+78, regionW-100, 23);
		Region runeSub3 = new Region(regionX+18, regionY+(regionH/6)+105, regionW-200, 23);
		Region runeSub4 = new Region(regionX+18, regionY+(regionH/6)+130, regionW-200, 23);
		getRuneRegion = runeRg;
		getRuneTitle = runeTitle;
		getRuneMainStat = runeMainStat;
		getRuneMainSubStat = runeMainSubStat;
		getRuneSub1 = runeSub1;
		getRuneSub2 = runeSub2;
		getRuneSub3 = runeSub3;
		getRuneSub4 = runeSub4;
		
		//getMainRegion.highlight();
		//runeRg.highlight();
		//runeTitle.highlight();
		//runeMainStat.highlight();
		//runeMainSubStat.highlight();
		//runeSub1.highlight();
		//runeSub2.highlight();
		//runeSub3.highlight();
		//runeSub4.highlight();
		
		//Obsolete
		returnRegion[0]=runeRg;
		returnRegion[1]=runeTitle;
		returnRegion[2]=runeMainStat;
		returnRegion[3]=runeMainSubStat;
		returnRegion[4]=runeSub1;
		returnRegion[5]=runeSub2;
		returnRegion[6]=runeSub3;
		returnRegion[7]=runeSub4;
		
		return returnRegion;
	}
    public static boolean waitElementExists(String elementFName) throws Exception{
    	int timeOut = 10;
    	int startTime = 0;
    	while (getMainRegion.exists(elementFName)==null){
    		//getMainRegion.wait(0.5);
    		startTime++;
    		if(startTime==timeOut){
    			break;
    		}
    		if(getMainRegion.exists(elementFName)!=null) {
    			break;
    		}
    	}
    	
    	if(startTime==timeOut){
    		return false;
    	}
    	else {
    		return true;
    	}        	
    }
    
    public static boolean waitElementExists(String elementFName, int timeOut) throws Exception{
    	int startTime = 0;
    	while (getMainRegion.exists(elementFName)==null){
    		getMainRegion.wait(0.5);
    		startTime++;
    		if(startTime==timeOut){
    			break;
    		}
    	}
    	
    	if(startTime==timeOut){
    		return false;
    	}
    	else {
    		return true;
    	}        	
    }
    
    public static String returnProperty(String propertyKey) throws Exception{
    	Properties prop = new Properties();
    	FileInputStream in = new FileInputStream("resources/parameters.properties");
    	prop.load(in);
    	//System.out.println(prop.getProperty(propertyKey));
    	return prop.getProperty(propertyKey);
    }
    
    public static void existClick(String fileName) throws Exception{
    	if(isExist(fileName)){
    		focusApp("NoxPlayer");
    		getMainRegion.find(fileName).click();    		
    	}
    }
    
    public static void waitExistClick(String fileName, Region r, int timeOut) throws Exception{
    	int ctr=1;
    	while (!isExist(fileName, r)) {
    		focusApp("NoxPlayer");
    		ctr++;
    		if (ctr>=timeOut) {
    			break;
    		}
    	}    	
    	
    	if(isExist(fileName,r)){    	
    		r.find(fileName).click();
    		System.out.println(fileName);
    	}
    }    
    
    public static void clickWaitForNext(String fileName, String expFile) throws Exception {
    	existClick(fileName);    	
    	
    }
    
    public static boolean isExist(String fileName) throws Exception{
    	int ctr=1;
    	int maxctr=1;
/*    	while(getMainRegion.exists(fileName)==null) {
    		//s.wait(0.5);
    		ctr++;
    		if (ctr>=maxctr) {
    			break;
    		}
    	}*/
    	
    	if(getMainRegion.exists(fileName)!=null){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public static boolean isExist(String fileName, Screen s) throws Exception{
    	if(s.exists(fileName)!=null){
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public static boolean isExist(String fileName, Region r) throws Exception{
    	int ctr=1;
    	int maxctr=5;    	
    	while(r.exists(fileName)==null) {
    		s.wait(0.5);
    		ctr++;
    		if (ctr>=maxctr) {
    			break;
    		}
    	}    	
    	if(r.exists(fileName)!=null){
    		return true;
    	}
    	else {
    		return false;
    	}
    }    
    public static Region getImageRegion(String fileName) throws Exception{
    	int getx = getMainRegion.find(fileName).x;
    	int gety = getMainRegion.find(fileName).y;
    	int geth = getMainRegion.find(fileName).h;
    	int getw = getMainRegion.find(fileName).w;
    	Region getRegion = new Region(getx, gety, getw, geth);
    	return getRegion;
    }
    
    public static void writeToTextFile(String LogFileName,String textToInput) {
    	try(FileWriter fw = new FileWriter("logs/" + LogFileName + ".txt", true);
    		    BufferedWriter bw = new BufferedWriter(fw);
    		    PrintWriter out = new PrintWriter(bw))
    		{
    		    out.println(textToInput);
    		} catch (IOException e) {
    		    //exception handling left as an exercise for the reader
    		}
    }
    
	public static void takeScreenshot(){
        try {
            Robot robot = new Robot();
            String format = "jpg";
            String getDateTime = String.valueOf(currentTime).replace("-", "").replaceAll(":", "").replace(".","");
            String fileName = "screenshots/" + getDateTime + "." + format;            
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            ImageIO.write(screenFullImage, format, new File(fileName));
             
            System.out.println("A full screenshot saved!");
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
	}    
	
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String returnDateTime = dateFormat.format(date);
		return returnDateTime;		
	}
    

}