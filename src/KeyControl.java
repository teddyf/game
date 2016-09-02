import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter{

	Player player;
	public KeyControl(Player player){
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W){
			player.setVelY(player.getVelY()+5);
		}
		if (key == KeyEvent.VK_S){
			player.setVelY(player.getVelY()-5);
		}
		if (key == KeyEvent.VK_A){
			player.setVelX(player.getVelX()-5);
		}
		if (key == KeyEvent.VK_D){
			player.setVelX(player.getVelX()+5);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_W){
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_S){
			player.setVelY(0);
		}
		if (key == KeyEvent.VK_A){
			player.setVelX(0);
		}
		if (key == KeyEvent.VK_D){
			player.setVelX(0);
		}
	}
}
