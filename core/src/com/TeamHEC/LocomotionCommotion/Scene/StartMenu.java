package com.TeamHEC.LocomotionCommotion.Scene;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.TeamHEC.LocomotionCommotion.Game.LoadGame;
import com.TeamHEC.LocomotionCommotion.UI_Elements.Sprite;
import com.TeamHEC.LocomotionCommotion.UI_Elements.SpriteButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;

public class StartMenu extends Scene{

	private Sprite sm_main_title, sm_main_lines;
	@SuppressWarnings("unused")
	private SpriteButton newGameButton, loadGameButton, replaymodeButton, howToPlayButton, exitButton;

	//Start Menu NewGame Page
	private Sprite sm_newgame_menutext;
	private SpriteButton newGameBackButton, newGameGoButton;
	private SpriteButton turn50Button, turn100Button, turn150Button;

	//Start Menu LoadGame Page
	private Sprite sm_loadgame_title, sm_loadgame_examples;

	//Start Menu Preferences/Replay Mode Page
	private Sprite sm_preferences_vertline, sm_replaymode_titletext;
	private SpriteButton sm_replaymode_gobutton, sm_replaymode_browsebutton;

	//Start Menu HowtoPlay Page
	private Sprite sm_howtoplay_line, sm_howtoplay_title;
	private Sprite sm_howtoplay_frame;
	private SpriteButton loadGameBckButton, sm_replaymode_backbutton, settingsButton;
	private SpriteButton displayButton, soundButton, controlButton;
	private SpriteButton homeButton, nextButton, prevButton, preferencesBackButton;

	// Other stuff
	public static String gameName, player1name, player2name, replayName;
	public static int turnChoice = 50;
	public static TextField gameNameBox, playerNameBox1, playerNameBox2, replayChosenBox;
	private static boolean gameNameEntered, player1NameEntered, player2NameEntered, replayChosen;

	Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));
	
	private final JFileChooser replayChooser = new JFileChooser();
	private FileNameExtensionFilter fNameFilter;
	File replayFile;
	int returnVal;
	
	public StartMenu()
	{
		sm_main_title = new Sprite(6, 650, SM_TextureManager.getInstance().sm_main_title);
		actors.add(sm_main_title);

		sm_main_lines = new Sprite(-229,-145, SM_TextureManager.getInstance().sm_main_linesimg);
		actors.add(sm_main_lines);

		// Start MenuNewGame Page
		sm_newgame_menutext =  new Sprite(80,1150+250, SM_TextureManager.getInstance().sm_newgame_MenuText);
		actors.add(sm_newgame_menutext);

		newGameButton = new SpriteButton(600, 480, SM_TextureManager.getInstance().sm_main_newgamebtn){

			@Override
			protected void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2;

			@Override
			public void act(float delta)
			{
				if(started)
				{
					if (animationTracker1<950){
						changeCam(0,15);
						animationTracker1+=15;
					}
					else{
						if(animationTracker2<90){
							changeCam(-15,0);
							animationTracker2+=15;
						}
						else{
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}
		};
		actors.add(newGameButton);

		loadGameButton = new SpriteButton(600, 406, SM_TextureManager.getInstance().sm_main_loadgamebtn){

			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2;

			@Override
			public void act(float delta)
			{
				if(started){
					if (animationTracker1<1680){
						changeCam(15,0);
						animationTracker1+=15;
					}
					else{
						if(animationTracker2<40){
							changeCam(0,10);
							animationTracker2+=10;
						}

						else{
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}

		};

        // Not yet implemented. Hidden.
		// actors.add(loadGameButton);

		replaymodeButton = new SpriteButton(577, 325, SM_TextureManager.getInstance().sm_main_replaymodebtn){

			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2, animationTracker3;

			@Override
			public void act(float delta)
			{
				if(started)
				{
					if (animationTracker1<900){
						changeCam(30,0);
						animationTracker1+=30;
					}
					else{
						if(animationTracker2<1000){
							changeCam(0,-30);
							animationTracker2+=30;
						}
						else
						{
							if(animationTracker3<500)
							{
								changeCam(-30,0);
								animationTracker3 +=30;
							}else{
								started = false;
								animationTracker1=0;
								animationTracker2=0;
								animationTracker3=0;
							}
						}
					}
				}
			}

		};
        actors.add(replaymodeButton);

		howToPlayButton = new SpriteButton(590, 255, SM_TextureManager.getInstance().sm_main_howtoplaybtn){
			@Override
			public void onClicked()
			{
				started = false;
				
				try {
					File userManual = new File(getClass().getClassLoader().getResource(".").getPath() + "userManual.pdf");
					Desktop.getDesktop().open(userManual);
				}
				catch (IOException e){
					System.out.println("Cannot open PDF");
				}
			}

			int animationTracker1, animationTracker2;

			@Override
			public void act(float delta)
			{
				if(started){
					if (animationTracker1<45){
						changeCam(0,-15);
						animationTracker1+=15;
					}
					else{
						if(animationTracker2<1700){
							changeCam(-50,0);
							animationTracker2+=50;
						}

						else{
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}

		};
        actors.add(howToPlayButton);

		exitButton = new SpriteButton(600, 86, SM_TextureManager.getInstance().sm_main_exitButton){

			@Override
			public void onClicked()
			{
				Gdx.app.exit();
			}
		};
		actors.add(exitButton);

		newGameGoButton = new SpriteButton(-100, 1200, SM_TextureManager.getInstance().sm_newgame_GoBtn){

			@Override
			public void onClicked()
			{
				if (player1NameEntered){
					LocomotionCommotion.player1name=playerNameBox1.getText();
				}
				
				else{
					LocomotionCommotion.player1name="Blue";
				}
				
				if (player2NameEntered){
					LocomotionCommotion.player2name=playerNameBox2.getText();
				}
				
				else{
					LocomotionCommotion.player2name="Orange";
				}
				
				if (gameNameEntered){
					LocomotionCommotion.gameName=gameNameBox.getText();
				}
				else{
					//Could find the smallest available integer for saving replay information
					LocomotionCommotion.gameName="TestGame";
				}
				
				LocomotionCommotion.turnChoice = turnChoice;
				LocomotionCommotion.getInstance().setGameScreen(false);

				resetNewGameScreen();
			}
			
			public void resetNewGameScreen()
			{
				playerNameBox1.setText("");
				playerNameBox2.setText("");
				turn50Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn50Btn);
				turn100Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn100_unselected_Btn);
				turn150Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn150_unselected_Btn);
				player1name= null;
				player2name= null;
				turnChoice=50;
			}
		};
		actors.add(newGameGoButton);

		newGameBackButton = new SpriteButton(1150, 1800, SM_TextureManager.getInstance().sm_newgame_BackBtn){

			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2;	

			@Override
			public void act(float delta)
			{
				if(started){
					if (animationTracker1<90){
						changeCam(15,0);
						animationTracker1+=15;
					}
					else{
						if(animationTracker2<950){
							changeCam(0,-15);
							animationTracker2+=15;
						}
						else{
							resetNewGameScreen();
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}

			public void resetNewGameScreen(){
				StartMenu.playerNameBox1.setText("");
				StartMenu.playerNameBox2.setText("");
				turn50Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn50Btn);
				turn100Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn100_unselected_Btn);
				turn150Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn150_unselected_Btn);
				StartMenu.player1name= null;
				StartMenu.player2name= null;
				StartMenu.turnChoice=0;
			}
		};
		actors.add(newGameBackButton);

		turn50Button = new SpriteButton(490, 1412, SM_TextureManager.getInstance().sm_newgame_Turn50Btn){

			@Override
			public void onClicked()
			{
				StartMenu.turnChoice = 50;
				setTexture(SM_TextureManager.getInstance().sm_newgame_Turn50Btn);
				turn100Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn100_unselected_Btn);
				turn150Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn150_unselected_Btn);
			}

		};
		actors.add(turn50Button);

		turn100Button = new SpriteButton(590, 1412, SM_TextureManager.getInstance().sm_newgame_Turn100_unselected_Btn){

			@Override
			public void onClicked()
			{
				StartMenu.turnChoice = 100;
				turn50Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn50_unselected_Btn);
				setTexture(SM_TextureManager.getInstance().sm_newgame_Turn100Btn);
				turn150Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn150_unselected_Btn);
			}

		};
		actors.add(turn100Button);

		turn150Button = new SpriteButton(690, 1412, SM_TextureManager.getInstance().sm_newgame_Turn150_unselected_Btn){

			@Override
			public void onClicked()
			{
				turnChoice = 150;
				turn50Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn50_unselected_Btn);
				turn100Button.setTexture(SM_TextureManager.getInstance().sm_newgame_Turn100_unselected_Btn);
				setTexture(SM_TextureManager.getInstance().sm_newgame_Turn150Btn);
			}

		};
		actors.add(turn150Button);

		sm_loadgame_title = new Sprite(1680+350,665, SM_TextureManager.getInstance().sm_loadgame_Title);
		actors.add(sm_loadgame_title);

		loadGameBckButton = new SpriteButton(1680+150, 850, SM_TextureManager.getInstance().sm_newgame_BackBtn){

			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2;	

			@Override
			public void act(float delta)
			{
				if(started)
				{
					if (animationTracker1<50){
						changeCam(0,-10);
						animationTracker1+=15;
					}
					else{
						if(animationTracker2<1680){
							changeCam(-15,0);
							animationTracker2+=15;
						}

						else{
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}
		};
		actors.add(loadGameBckButton);

		sm_loadgame_examples = new Sprite(1680+350,500, SM_TextureManager.getInstance().sm_loadgame_Examples);
		actors.add(sm_loadgame_examples);

		//Start Menu Replay Mode Page
		replayChosenBox = new TextField("", skin);
		skin.getFont("default-font").setScale(1.5f, 1.5f);
		replayChosenBox.setX(560);
		replayChosenBox.setY(-320);
		replayChosenBox.setSize(430, 60);
		replayChosenBox.setMessageText("Click ... to select a file.");
		replayChosen = false;
		
		sm_replaymode_browsebutton = new SpriteButton(1000, -320, SM_TextureManager.getInstance().sm_replaymode_BrowseButton){
			public void onClicked(){
				fNameFilter = new FileNameExtensionFilter("JSON Files", "json");
				replayChooser.setFileFilter(fNameFilter);
				returnVal = replayChooser.showOpenDialog(null);
				
				if (returnVal == JFileChooser.APPROVE_OPTION){
					replayFile = replayChooser.getSelectedFile();
					replayChosen = true;
					replayChosenBox.setText(replayFile.getAbsolutePath());
				}
			}
		};
		actors.add(sm_replaymode_browsebutton);
		
		sm_replaymode_gobutton = new SpriteButton(1750, -850, SM_TextureManager.getInstance().sm_replaymode_GoButton){
			@Override
			public void onClicked(){
				if (replayChosen){
					LocomotionCommotion.loadedReplay.createJSONObject(replayFile);
					LocomotionCommotion.player1name = "Replay1";
					LocomotionCommotion.player2name = "Replay2";
					LocomotionCommotion.turnChoice = LocomotionCommotion.getReplayTurn().getInt("turnLimit");
					LocomotionCommotion.getInstance().setGameScreen(true);
				}
			}
		};
		actors.add(sm_replaymode_gobutton);
		
		sm_replaymode_backbutton = new SpriteButton(1390, -900+ 745, SM_TextureManager.getInstance().sm_newgame_BackBtn){

			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2, animationTracker3;	

			@Override
			public void act(float delta)
			{
				if(started)
				{
					if (animationTracker1<510){
						changeCam(30,0);
						animationTracker1+=30;
					}
					else{
						if(animationTracker2<1000){
							changeCam(0,30);
							animationTracker2+=30;
						}
						else
						{
							if(animationTracker3<900)
							{
								changeCam(-30,0);
								animationTracker3 +=30;
							}else{
								started = false;
								animationTracker1=0;
								animationTracker2=0;
								animationTracker3=0;
							}
						}
					}
				}
			}
		};
		actors.add(sm_replaymode_backbutton);

		sm_replaymode_titletext = new Sprite(500,-900+720, SM_TextureManager.getInstance().sm_replaymode_Title);
		actors.add(sm_replaymode_titletext);

		//StartMenu HowtoPlay screen
		sm_howtoplay_line = new Sprite(-1700+1300,175, SM_TextureManager.getInstance().sm_howtoplay_line);
		actors.add(sm_howtoplay_line);

		sm_howtoplay_title = new Sprite(-1700+350,650, SM_TextureManager.getInstance().sm_howtoplay_title);
		actors.add(sm_howtoplay_title);

		nextButton = new SpriteButton(-1700+ 590, 150, SM_TextureManager.getInstance().sm_howtoplay_nextbtn){

			@Override
			public void onClicked()
			{
				changeCam(0, 0);
			}
		};
		actors.add(nextButton);

		prevButton = new SpriteButton(-1700+ 460, 150, SM_TextureManager.getInstance().sm_howtoplay_previousbtn){

			@Override
			public void onClicked()
			{
				changeCam(0, 0);
			}
		};
		actors.add(prevButton);


		homeButton = new SpriteButton(-1700+ 570, 160, SM_TextureManager.getInstance().sm_howtoplay_homebtn){

			@Override
			public void onClicked()
			{
				changeCam(0, 0);
			}
		};
		actors.add(homeButton);

		sm_howtoplay_frame = new Sprite(-1700+240,220, SM_TextureManager.getInstance().sm_howtoplay_frame);
		actors.add(sm_howtoplay_frame);

		preferencesBackButton = new SpriteButton(-1700+ 1275, 655, SM_TextureManager.getInstance().sm_newgame_BackBtn){
			@Override
			public void onClicked()
			{
				started = true;
			}

			int animationTracker1, animationTracker2;

			@Override
			public void act(float delta)
			{
				if(started){
					if (animationTracker1<1700){
						changeCam(50,0);
						animationTracker1+=50;
					}
					else{
						if(animationTracker2<45){
							changeCam(0,15);
							animationTracker2+=15;
						}

						else{
							started = false;
							animationTracker1=0;
							animationTracker2=0;
						}
					}
				}
			}

		};
		actors.add(preferencesBackButton);
		
		//Text boxes for Player 1 and 2 names
		
		gameNameBox = new TextField("", skin);
		skin.getFont("default-font").setScale(1.5f, 1.5f);
		gameNameBox.setX(480);
		gameNameBox.setY(1150+532);
		gameNameBox.setSize(430, 60);
		gameNameBox.setMessageText("Game Name");
		gameNameEntered = false;
		TextFieldListener gameField = new TextFieldListener(){
			public void keyTyped (TextField textbox0, char key){
				if (key == '\n') textbox0.getOnscreenKeyboard().show(false);
				gameName = textbox0.getText();
				gameNameEntered = true;
			}
		};
		gameNameBox.setTextFieldListener(gameField);
		
		playerNameBox1 = new TextField("", skin);
		skin.getFont("default-font").setScale(1.5f, 1.5f);
		playerNameBox1.setX(480);
		playerNameBox1.setY(1150+447);
		playerNameBox1.setSize(430, 60);
		playerNameBox1.setMessageText("Player One");
		player1NameEntered = false;
		TextFieldListener player1 = new TextFieldListener() {
			public void keyTyped (TextField textbox1, char key) {
				if (key == '\n') textbox1.getOnscreenKeyboard().show(false);
				player1name = textbox1.getText();
				player1NameEntered = true;
			}
		};
		playerNameBox1.setTextFieldListener(player1);

		playerNameBox2 = new TextField("", skin);
		playerNameBox2.setX(480);
		playerNameBox2.setY(1150+362);
		playerNameBox2.setSize(430, 60);
		playerNameBox2.setMessageText("Player Two");
		player2NameEntered = false;
		TextFieldListener player2 = new TextFieldListener() {
			public void keyTyped (TextField textbox2, char key) {
				if (key == '\n') textbox2.getOnscreenKeyboard().show(false);
				player2name = textbox2.getText();
				player2NameEntered = true;
			}
		};
		playerNameBox2.setTextFieldListener(player2);

		actors.add(gameNameBox);
		actors.add(playerNameBox1);
		actors.add(playerNameBox2);
		actors.add(replayChosenBox);
	}
}