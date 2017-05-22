package com.elkadakh.others;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	//UI Buttons
	public static Texture largePlay, smallPlay, largeSound, largeSoundCanceled, smallSound, smallSoundCanceled, largeRate, smallRate, howTo;
	public static Texture lvl1Btn, lvl2Btn, lvl3Btn, lvl4Btn, lvl5Btn, lvl6Btn, lvl7Btn, lvl8Btn, lvl9Btn, lvl10Btn;
	public static Texture largeBackDark, largeBackLight, smallBackDark, smallBackLight, XsmallBackDark, XsmallBackLight;
	public static Texture dif1Dark80, dif1Light80, dif1Dark200, dif1Light200, dif1Dark300, dif1Light300;
	public static Texture dif2Dark80, dif2Light80, dif2Dark200, dif2Light200, dif2Dark300, dif2Light300;
	public static Texture dif3Dark80, dif3Light80, dif3Dark200, dif3Light200, dif3Dark300, dif3Light300;
	public static Texture dif4Dark80, dif4Light80, dif4Dark200, dif4Light200, dif4Dark300, dif4Light300;
	
	//Logos
	public static Texture logo800, logo1119, logo2000, logo4000;
	
	//Logo's 'U' letter
	public static Texture uLet1_800, uLet2_800, uLet3_800, uLet4_800;
    public static Animation uLet800Animation;
    
	public static Texture uLet1_1119, uLet2_1119, uLet3_1119, uLet4_1119;
    public static Animation uLet1119Animation;
    
	public static Texture uLet1_2000, uLet2_2000, uLet3_2000, uLet4_2000;
    public static Animation uLet2000Animation;
    
	public static Texture uLet1_4000, uLet2_4000, uLet3_4000, uLet4_4000;
    public static Animation uLet4000Animation;
	
	//Fonts
    public static BitmapFont font1;
    public static BitmapFont subText;
    public static BitmapFont subTextSmall;
	
	//Objects
	public static Texture canon;
	public static Texture canonBall;
	public static Texture canonBallWhite;
	
	//Level's 1 arrows
	public static Texture up;
	public static Texture upGreen;
	public static Texture left;
	public static Texture leftGreen;
	
	//Sounds
	public static Sound beep;
	public static Music crash;
	public static Music nextLevel;
	public static Music propellorSpin;
	
	//Helicopter
    public static Texture heli1Gray, heli2Gray, heli3Gray, heli4Gray;
    public static Texture heli1Green, heli2Green, heli3Green, heli4Green;
    public static Animation heliGrayAnimation, heliGreenAnimation;
    
    //Backgrounds
    public static Texture bgDarkLarge, bgLightLarge, bgTopDarkLarge, bgTopLightLarge;
    public static Texture bgDarkSmall, bgLightSmall, bgTopDarkSmall, bgTopLightSmall;
    
    //Levels' textures
    public static Texture lvl1Texture;
    
    //Fence texture
    public static Texture fenceSmall, fenceBig;
    
    //Target (Landing point)
    public static Texture target;
    public static Texture targetDif1;
    public static TextureRegion targetTR;
    public static Texture targetSmall;
    public static Texture targetSmallDif1;
    public static TextureRegion targetTRSmall;
    public static TextureRegion targetTRSmallDif1;
    public static TextureRegion targetTRDif1;
    
    //Teleport
    public static Texture teleport1,teleport2,teleport3,teleport4,teleport5,teleport6,teleport7,teleport8,teleport9,teleport10;
    public static Animation teleportAnimation;
    
    //Lightening 161
    public static Texture light161_1,light161_2,light161_3,light161_4;
    public static Animation light161Animation;
    
    //Lightening 141
    public static Texture light141_1,light141_2,light141_3,light141_4;
    public static Animation light141Animation;
    
    //Levels
    public static Texture level1;
    public static Texture level2;
    public static Texture level3;
    public static Texture level4;
    public static Texture level5;
    public static Texture level6;
    public static Texture level7;
    public static Texture level8a;
    public static Texture level8b;
    public static Texture level9;
    public static Texture level10;
    
    //Star
    public static Texture star, starBordered;
    
    public static void load() {
    	//UI Buttons
    	largePlay = new Texture(Gdx.files.internal("UI Buttons/play/largePlay.png"));
    	largeRate = new Texture(Gdx.files.internal("UI Buttons/rate/largeRate.png"));
    	smallRate = new Texture(Gdx.files.internal("UI Buttons/rate/smallRate.png"));
    	howTo = new Texture(Gdx.files.internal("UI Buttons/howto/howto.png"));
    	largeSound = new Texture(Gdx.files.internal("UI Buttons/sound/largeSound.png"));
    	largeSoundCanceled = new Texture(Gdx.files.internal("UI Buttons/sound/largeSoundCanceled.png"));
    	smallPlay = new Texture(Gdx.files.internal("UI Buttons/play/smallPlay.png"));
    	smallSound = new Texture(Gdx.files.internal("UI Buttons/sound/smallSound.png"));
    	smallSoundCanceled = new Texture(Gdx.files.internal("UI Buttons/sound/smallSoundCanceled.png"));
    	lvl1Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl1Button.png"));
    	lvl2Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl2Button.png"));
    	lvl3Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl3Button.png"));
    	lvl4Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl4Button.png"));
    	lvl5Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl5Button.png"));
    	lvl6Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl6Button.png"));
    	lvl7Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl7Button.png"));
    	lvl8Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl8Button.png"));
    	lvl9Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl9Button.png"));
    	lvl10Btn = new Texture(Gdx.files.internal("UI Buttons/levels/lvl10Button.png"));
    	largeBackDark = new Texture(Gdx.files.internal("UI Buttons/back/largeBackDark.png"));
    	largeBackLight = new Texture(Gdx.files.internal("UI Buttons/back/largeBackLight.png"));
    	smallBackDark = new Texture(Gdx.files.internal("UI Buttons/back/smallBackDark.png"));
    	smallBackLight = new Texture(Gdx.files.internal("UI Buttons/back/smallBackLight.png"));
    	XsmallBackDark = new Texture(Gdx.files.internal("UI Buttons/back/XsmallBackDark.png"));
    	XsmallBackLight = new Texture(Gdx.files.internal("UI Buttons/back/XsmallBackLight.png"));
    	
    	dif1Dark80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/dark80.png")); 
    	dif1Light80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/light80.png")); 
    	dif1Dark200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/dark200.png")); 
    	dif1Light200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/light200.png"));  
    	dif1Dark300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/dark300.png"));  
    	dif1Light300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif1/light300.png")); 
    	
    	dif2Dark80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/dark80.png")); 
    	dif2Light80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/light80.png")); 
    	dif2Dark200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/dark200.png")); 
    	dif2Light200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/light200.png"));  
    	dif2Dark300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/dark300.png"));  
    	dif2Light300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif2/light300.png")); 
    	
    	dif3Dark80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/dark80.png")); 
    	dif3Light80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/light80.png")); 
    	dif3Dark200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/dark200.png")); 
    	dif3Light200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/light200.png"));  
    	dif3Dark300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/dark300.png"));  
    	dif3Light300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif3/light300.png")); 
    	
    	dif4Dark80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/dark80.png")); 
    	dif4Light80 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/light80.png")); 
    	dif4Dark200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/dark200.png")); 
    	dif4Light200 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/light200.png"));  
    	dif4Dark300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/dark300.png"));  
    	dif4Light300 = new Texture(Gdx.files.internal("UI Buttons/DiffLvls/dif4/light300.png")); 
    	
    	//Logo
    	logo800 = new Texture(Gdx.files.internal("Others/logo800/logo800.png"));
    	logo1119 = new Texture(Gdx.files.internal("Others/logo1119/logo1119.png"));
    	logo2000 = new Texture(Gdx.files.internal("Others/logo2000/logo2000.png"));
    	logo4000 = new Texture(Gdx.files.internal("Others/logo4000/logo4000.png"));
    	
    	//Logos 'U' letter
    	uLet1_800 = new Texture(Gdx.files.internal("Others/logo800/uLetterAnimation/u1.png"));
    	uLet2_800 = new Texture(Gdx.files.internal("Others/logo800/uLetterAnimation/u2.png"));
    	uLet3_800 = new Texture(Gdx.files.internal("Others/logo800/uLetterAnimation/u3.png"));
    	uLet4_800 = new Texture(Gdx.files.internal("Others/logo800/uLetterAnimation/u4.png"));
    	
	    TextureRegion[] uLet800 = {new TextureRegion(uLet1_800, 5, 14, 124, 94), new TextureRegion(uLet2_800,  5, 14, 124, 94), 
	    		new TextureRegion(uLet3_800, 5, 14, 124, 94), new TextureRegion(uLet4_800, 5, 14, 124, 94)  };
	    uLet800Animation = new Animation(0.035f, uLet800);
	    uLet800Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
    	uLet1_1119 = new Texture(Gdx.files.internal("Others/logo1119/uLetterAnimation/u1.png"));
    	uLet2_1119 = new Texture(Gdx.files.internal("Others/logo1119/uLetterAnimation/u2.png"));
    	uLet3_1119 = new Texture(Gdx.files.internal("Others/logo1119/uLetterAnimation/u3.png"));
    	uLet4_1119 = new Texture(Gdx.files.internal("Others/logo1119/uLetterAnimation/u4.png"));
    	
	    TextureRegion[] uLet1119 = {new TextureRegion(uLet1_1119, 3, 3, 245, 187), new TextureRegion(uLet2_1119, 3, 3, 245, 187), 
	    		new TextureRegion(uLet3_1119, 3, 3, 245, 187), new TextureRegion(uLet4_1119, 3, 3, 245, 187)  };
	    uLet1119Animation = new Animation(0.035f, uLet1119);
	    uLet1119Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
    	uLet1_2000 = new Texture(Gdx.files.internal("Others/logo2000/uLetterAnimation/u1.png"));
    	uLet2_2000 = new Texture(Gdx.files.internal("Others/logo2000/uLetterAnimation/u2.png"));
    	uLet3_2000 = new Texture(Gdx.files.internal("Others/logo2000/uLetterAnimation/u3.png"));
    	uLet4_2000 = new Texture(Gdx.files.internal("Others/logo2000/uLetterAnimation/u4.png"));
    	
	    TextureRegion[] uLet2000 = {new TextureRegion(uLet1_2000, 15, 7, 324, 285), new TextureRegion(uLet2_2000, 15, 7, 324, 285), 
	    		new TextureRegion(uLet3_2000, 15, 7, 324, 285), new TextureRegion(uLet4_2000, 15, 7, 324, 285)  };
	    uLet2000Animation = new Animation(0.035f, uLet2000);
	    uLet2000Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
    	uLet1_4000 = new Texture(Gdx.files.internal("Others/logo4000/uLetterAnimation/u1.png"));
    	uLet2_4000 = new Texture(Gdx.files.internal("Others/logo4000/uLetterAnimation/u2.png"));
    	uLet3_4000 = new Texture(Gdx.files.internal("Others/logo4000/uLetterAnimation/u3.png"));
    	uLet4_4000 = new Texture(Gdx.files.internal("Others/logo4000/uLetterAnimation/u4.png"));
    	
	    TextureRegion[] uLet4000 = {new TextureRegion(uLet1_4000, 0, 100, 690, 590), new TextureRegion(uLet2_4000, 0, 100, 690, 590), 
	    		new TextureRegion(uLet3_4000, 0, 100, 690, 590), new TextureRegion(uLet4_4000, 0, 100, 690, 590)  };
	    uLet4000Animation = new Animation(0.035f, uLet4000);
	    uLet4000Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
    	//Fonts
    	font1 = new BitmapFont(Gdx.files.internal("Fonts/text.fnt"));
    	subText = new BitmapFont(Gdx.files.internal("Fonts/subText.fnt"));
    	subTextSmall = new BitmapFont(Gdx.files.internal("Fonts/subTextSmall.fnt"));
    	
    	//Objects
    	canonBall = new Texture(Gdx.files.internal("Objects/canonBall.png"));
    	canonBallWhite = new Texture(Gdx.files.internal("Objects/canonBallWhite.png"));
    	
    	//Level's 1 arrows
    	up = new Texture(Gdx.files.internal("lvls1Arrows/up.png"));
    	upGreen = new Texture(Gdx.files.internal("lvls1Arrows/upGreen.png"));
    	left = new Texture(Gdx.files.internal("lvls1Arrows/left.png"));
    	leftGreen = new Texture(Gdx.files.internal("lvls1Arrows/leftGreen.png"));
    	
    	//Sounds
    	beep = Gdx.audio.newSound(Gdx.files.internal("SFX/beep.mp3"));
    	crash = Gdx.audio.newMusic(Gdx.files.internal("SFX/crash.mp3"));
    	crash.setVolume((float) 0.5);
    	nextLevel = Gdx.audio.newMusic(Gdx.files.internal("SFX/nextLevel.wav"));
    	propellorSpin = Gdx.audio.newMusic(Gdx.files.internal("SFX/propellorSpin.wav"));
    	crash.setLooping(false);
    	
    	//Helicopter textures and animation
    	heli1Gray = new Texture(Gdx.files.internal("Helicopter/gray/heli1Gray.png"));
    	heli2Gray = new Texture(Gdx.files.internal("Helicopter/gray/heli2Gray.png"));
    	heli3Gray = new Texture(Gdx.files.internal("Helicopter/gray/heli3Gray.png"));
    	heli4Gray = new Texture(Gdx.files.internal("Helicopter/gray/heli4Gray.png"));
	    
    	heli1Green = new Texture(Gdx.files.internal("Helicopter/green/heli1Green.png"));
    	heli2Green = new Texture(Gdx.files.internal("Helicopter/green/heli2Green.png"));
    	heli3Green = new Texture(Gdx.files.internal("Helicopter/green/heli3Green.png"));
    	heli4Green = new Texture(Gdx.files.internal("Helicopter/green/heli4Green.png"));
	    
	    TextureRegion[] heliGrayImages = {new TextureRegion(heli1Gray, 0, 0, 74, 34), new TextureRegion(heli2Gray, 0, 0, 74, 34), 
	    		new TextureRegion(heli3Gray, 0, 0, 74, 34), new TextureRegion(heli4Gray, 0, 0, 74, 34)  };
	    
	    TextureRegion[] heliGreenImages = {new TextureRegion(heli1Green, 0, 0, 74, 34), new TextureRegion(heli2Green, 0, 0, 74, 34), 
	    		new TextureRegion(heli3Green, 0, 0, 74, 34), new TextureRegion(heli4Green, 0, 0, 74, 34)  };
	    
	    heliGrayAnimation = new Animation(0.035f, heliGrayImages);
	    heliGreenAnimation = new Animation(0.035f, heliGreenImages);
	    heliGrayAnimation.setPlayMode(Animation.PlayMode.LOOP);
	    heliGreenAnimation.setPlayMode(Animation.PlayMode.LOOP);
	    
	    //Backgrounds textures
	    bgDarkLarge = new Texture(Gdx.files.internal("Backgrounds/Big screens/bgDarkLarge.png"));
	    bgLightLarge = new Texture(Gdx.files.internal("Backgrounds/Big screens/bgLightLarge.png"));
	    bgTopDarkLarge = new Texture(Gdx.files.internal("Backgrounds/Big screens/bgTopDarkLarge.png"));
	    bgTopLightLarge = new Texture(Gdx.files.internal("Backgrounds/Big screens/bgTopLightLarge.png"));
	    
	    bgDarkSmall = new Texture(Gdx.files.internal("Backgrounds/Small screens/bgDarkSmall.png"));
	    bgLightSmall = new Texture(Gdx.files.internal("Backgrounds/Small screens/bgLightSmall.png"));
	    bgTopDarkSmall = new Texture(Gdx.files.internal("Backgrounds/Small screens/bgTopDarkSmall.png"));
	    bgTopLightSmall = new Texture(Gdx.files.internal("Backgrounds/Small screens/bgTopLightSmall.png"));
	    
	    //Fence texture
	    fenceSmall = new Texture(Gdx.files.internal("Levels/fenceSmall.png"));
	    fenceBig = new Texture(Gdx.files.internal("Levels/fenceBig.png"));
	    
	    //Target (Landing point)
	    target = new Texture(Gdx.files.internal("Others/target.png"));
	    targetTR = new TextureRegion(target, 0, 0, 35, 35); //For rotation
	    targetSmall = new Texture(Gdx.files.internal("Others/targetSmall.png"));
	    targetTRSmall = new TextureRegion(targetSmall, 0, 0, 28, 28); //For rotation
	    
	    targetDif1 = new Texture(Gdx.files.internal("Others/targetDif1.png"));
	    targetTRDif1 = new TextureRegion(targetDif1, 0, 0, 35, 35); //For rotation
	    targetSmallDif1 = new Texture(Gdx.files.internal("Others/targetSmallDif1.png"));
	    targetTRSmallDif1 = new TextureRegion(targetSmallDif1, 0, 0, 28, 28); //For rotation
	    
	    //Teleport (animated)
    	teleport1 = new Texture(Gdx.files.internal("Others/teleport/frame_000.gif"));
    	teleport2 = new Texture(Gdx.files.internal("Others/teleport/frame_001.gif"));
    	teleport3 = new Texture(Gdx.files.internal("Others/teleport/frame_002.gif"));
    	teleport4 = new Texture(Gdx.files.internal("Others/teleport/frame_003.gif"));
    	teleport5 = new Texture(Gdx.files.internal("Others/teleport/frame_004.gif"));
    	teleport6 = new Texture(Gdx.files.internal("Others/teleport/frame_005.gif"));
    	teleport7 = new Texture(Gdx.files.internal("Others/teleport/frame_006.gif"));
    	teleport8 = new Texture(Gdx.files.internal("Others/teleport/frame_007.gif"));
    	teleport9 = new Texture(Gdx.files.internal("Others/teleport/frame_008.gif"));
    	teleport10 = new Texture(Gdx.files.internal("Others/teleport/frame_008.gif"));
	    
	    TextureRegion[] teleportImages = {
	    		new TextureRegion(teleport1, 5, 4, 195, 195), 
	    		new TextureRegion(teleport3, 5, 4, 195, 195), 
	    		new TextureRegion(teleport4, 5, 4, 195, 195), 
	    		new TextureRegion(teleport5, 5, 4, 195, 195), 
	    		new TextureRegion(teleport6, 5, 4, 195, 195), 
	    		new TextureRegion(teleport7, 5, 4, 195, 195), 
	    		new TextureRegion(teleport8, 5, 4, 195, 195), 
	    		new TextureRegion(teleport9, 5, 4, 195, 195), 
	    		new TextureRegion(teleport10, 5, 4, 195, 195),
	    		};
	    
	    teleportAnimation = new Animation(0.03f, teleportImages);
	    teleportAnimation.setPlayMode(Animation.PlayMode.LOOP);
	    
	    //light161 (animated)
    	light161_1 = new Texture(Gdx.files.internal("Objects/161h/1.png"));
    	light161_2 = new Texture(Gdx.files.internal("Objects/161h/2.png"));
    	light161_3 = new Texture(Gdx.files.internal("Objects/161h/3.png"));
    	light161_4 = new Texture(Gdx.files.internal("Objects/161h/4.png"));
	    
	    TextureRegion[] light161Images = {
	    		new TextureRegion(light161_1, 0, 0, 5, 161), 
	    		new TextureRegion(light161_2, 0, 0, 5, 161), 
	    		new TextureRegion(light161_3, 0, 0, 5, 161), 
	    		new TextureRegion(light161_4, 0, 0, 5, 161), 
	    		};
	    
	    light161Animation = new Animation(0.05f, light161Images);
	    light161Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
	    //light141 (animated)
    	light141_1 = new Texture(Gdx.files.internal("Objects/141h/1.png"));
    	light141_2 = new Texture(Gdx.files.internal("Objects/141h/2.png"));
    	light141_3 = new Texture(Gdx.files.internal("Objects/141h/3.png"));
    	light141_4 = new Texture(Gdx.files.internal("Objects/141h/4.png"));
	    
	    TextureRegion[] light141Images = {
	    		new TextureRegion(light161_1, 0, 0, 5, 141), 
	    		new TextureRegion(light161_2, 0, 0, 5, 141), 
	    		new TextureRegion(light161_3, 0, 0, 5, 141), 
	    		new TextureRegion(light161_4, 0, 0, 5, 141), 
	    		};
	    
	    light141Animation = new Animation(0.05f, light141Images);
	    light141Animation.setPlayMode(Animation.PlayMode.LOOP);
	    
	    //Levels
	    level1 = new Texture(Gdx.files.internal("Levels/level1.png"));
	    level2 = new Texture(Gdx.files.internal("Levels/level2.png"));
	    level3 = new Texture(Gdx.files.internal("Levels/level3.png"));
	    level4 = new Texture(Gdx.files.internal("Levels/level4.png"));
	    level5 = new Texture(Gdx.files.internal("Levels/level5.png"));
	    level6 = new Texture(Gdx.files.internal("Levels/level6.png"));
	    level7 = new Texture(Gdx.files.internal("Levels/level7.png"));
	    level8a = new Texture(Gdx.files.internal("Levels/level8a.png"));
	    level8b = new Texture(Gdx.files.internal("Levels/level8b.png"));
	    level9 = new Texture(Gdx.files.internal("Levels/level9.png"));
	    level10 = new Texture(Gdx.files.internal("Levels/level10.png"));
	    
	    //star
	    star = new Texture(Gdx.files.internal("Others/starForLvls.png"));
	    starBordered = new Texture(Gdx.files.internal("Others/starForLvlsBordered.png"));
    }
    
    public static void dispose() {
        //Must dispose of the texture when game is finished
    	largePlay.dispose();
    	smallPlay.dispose();
    	largeSound.dispose();
    	largeSoundCanceled.dispose();
    	smallSound.dispose();
    	smallSoundCanceled.dispose();
    	largeRate.dispose();
    	smallRate.dispose();
    	lvl1Btn.dispose();
    	lvl2Btn.dispose();
    	lvl3Btn.dispose();
    	lvl4Btn.dispose();
    	lvl5Btn.dispose();
    	lvl6Btn.dispose();
    	lvl7Btn.dispose();
    	lvl8Btn.dispose();
    	lvl9Btn.dispose();
    	largeBackDark.dispose();
    	largeBackLight.dispose();
    	smallBackDark.dispose();
    	smallBackLight.dispose();
    	XsmallBackDark.dispose();
    	XsmallBackLight.dispose();
    	logo800.dispose();
    	logo1119.dispose();
    	logo2000.dispose();
    	uLet1_800.dispose();
    	uLet2_800.dispose();
    	uLet3_800.dispose();
    	uLet4_800.dispose();
    	uLet1_1119.dispose();
    	uLet2_1119.dispose();
    	uLet3_1119.dispose();
    	uLet4_1119.dispose();
    	uLet1_2000.dispose();
    	uLet2_2000.dispose();
    	uLet3_2000.dispose();
    	uLet4_2000.dispose();
    	uLet1_4000.dispose();
    	uLet2_4000.dispose();
    	uLet3_4000.dispose();
    	uLet4_4000.dispose();
    	font1.dispose();
    	subText.dispose();
    	subTextSmall.dispose();
    	canon.dispose();
    	canonBall.dispose();
    	up.dispose();
    	upGreen.dispose();
    	left.dispose();
    	leftGreen.dispose();
    	beep.dispose();
    	crash.dispose();
    	nextLevel.dispose();
    	propellorSpin.dispose();
    	heli1Gray.dispose();
    	heli2Gray.dispose();
    	heli3Gray.dispose();
    	heli4Gray.dispose();
    	heli1Green.dispose();
    	heli2Green.dispose();
    	heli3Green.dispose();
    	heli4Green.dispose();

    	bgLightLarge.dispose();
    	bgTopLightLarge.dispose();
    	bgLightSmall.dispose();
    	bgTopLightSmall.dispose();
    	lvl1Texture.dispose();
    	fenceSmall.dispose();
    	fenceBig.dispose();
    	target.dispose();
    	targetSmall.dispose();
    	teleport1.dispose();
    	teleport2.dispose();
    	teleport3.dispose();
    	teleport4.dispose();
    	teleport5.dispose();
    	teleport6.dispose();
    	teleport7.dispose();
    	teleport8.dispose();
    	teleport9.dispose();
    	teleport10.dispose();
    	light161_1.dispose();
    	light161_2.dispose();
    	light161_3.dispose();
    	light161_4.dispose();
    	light141_1.dispose();
    	light141_2.dispose();
    	light141_3.dispose();
    	light141_4.dispose();
    	level1.dispose();
    	level2.dispose();
    	level3.dispose();
    	level4.dispose();
    	level5.dispose();
    	level6.dispose();
    	level7.dispose();
    	level8a.dispose();
    	level8b.dispose();
    	level9.dispose();
    }
}
