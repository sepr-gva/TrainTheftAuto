package com.TeamHEC.LocomotionCommotion.UI_Elements;

import com.TeamHEC.LocomotionCommotion.LocomotionCommotion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;

/**
 * 
 * @author Michael Dipper <md859@york.ac.uk>
 *
 */
public class ReplayModeUI {
	//Class for replay mode only UI elements.
	//Includes corner frame, previous, play/pause and next buttons.
	private final static Array<Actor> actors = new Array<Actor>();
	
	public static SpriteButton previousButton, nextButton, playPauseButton;
	public static Sprite cornerFrame;
	
	public void create(Stage stage){
		actors.clear();
		
		cornerFrame = new Sprite((LocomotionCommotion.screenX-Game_TextureManager.getInstance().game_menuobject_replaycornerframe.getWidth())+3,2
				,Game_TextureManager.getInstance().game_menuobject_replaycornerframe);
		actors.add(cornerFrame);
		
		previousButton = new SpriteButton(1412, 117, Game_TextureManager.getInstance().game_menuobject_replaypreviousbutton){
			@Override
			protected void onClicked(){
				
			}
		};
		actors.add(previousButton);
		
		nextButton = new SpriteButton(1576, 117, Game_TextureManager.getInstance().game_menuobject_replaynextbutton){
			@Override
			protected void onClicked(){
				
			}
		};
		actors.add(nextButton);
		
		playPauseButton = new SpriteButton(1494, 117, Game_TextureManager.getInstance().game_menuobject_replayplaybutton){
			@Override
			protected void onClicked(){
				if (this.getTexture() == Game_TextureManager.getInstance().game_menuobject_replayplaybutton){
					this.setTexture(Game_TextureManager.getInstance().game_menuobject_replaypausebutton);
				}
				else {
					this.setTexture(Game_TextureManager.getInstance().game_menuobject_replayplaybutton);
				}
				
			}
		};
		actors.add(playPauseButton);
		
		for (Actor a : actors){
			a.setTouchable(Touchable.enabled);
			stage.addActor(a);
		}
	}
}
