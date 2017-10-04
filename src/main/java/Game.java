import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


//test

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 700, HEIGHT = 500,
							TILE_SIZE = 50, MAP_WIDTH = 1000, MAP_HEIGHT = 1000;
	public static final int TILE_WIDTH = MAP_WIDTH/TILE_SIZE, 
							TILE_HEIGHT = MAP_HEIGHT/TILE_SIZE;
	public static final String TITLE = "game";
	
	public static long tickCounter = 0;
	public static boolean[] Keys = new boolean[128];
	public static int windowXPos = 0, windowYPos = 0;
	
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	
	public Game(){
		
		Images.loadImages();
		
		handler = new Handler();
		this.setFocusable(true);
		this.addKeyListener(new KeyInput());
		new Window(WIDTH, HEIGHT, TITLE, this);
		
		
		BasicTile.generateMap(handler);
		handler.player = new Player(200, 100, ID.PLAYER, TILE_SIZE, 1000, Images.LeifGWPersson);
		
	}
	
	

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1){
				tick();
				delta--;
			}
			if (running) render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	
	private void tick() {
		tickCounter++;
		if (tickCounter == 100000000) tickCounter = 0;
		handler.tick();		
	}



	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		handler.render(g, this);
		
		g.dispose();
		bs.show();
	}



	public static void main(String[] args) {
		new Game();
	}
	
	
}
