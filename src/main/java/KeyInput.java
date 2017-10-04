import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyInput implements KeyListener{
	
	
	public void keyPressed(KeyEvent e){
		Game.Keys[e.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent e){
		Game.Keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) { }



}
