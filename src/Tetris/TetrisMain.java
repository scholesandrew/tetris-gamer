package Tetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TetrisMain extends Canvas implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400, HEIGHT = 565;
	private Image[] tetrisBlocks;
		
	public static void main(String[] args) {
		final JFrame frame = new JFrame("Tetris");
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		
		KeyGetter.loadKeys();
		try {
			Config.loadConfig();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JMenuBar bar = new JMenuBar();
		bar.setBounds(0, 0, WIDTH, 25);
		
		JMenu file = new JMenu("File");
		file.setBounds(0, 0, 45, 24);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//code for new game
				System.out.println("Starting New Game...");
			}
		});
		
		JMenuItem highScore = new JMenuItem("Highscore");
		highScore.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int highscore = 0; //replacxe with gethigh scotre method
				final JFrame alert = new JFrame("High Score");
				alert.setSize(200, 150);
				alert.setLayout(null);
				alert.setLocationRelativeTo(null);
				
				JLabel score = new JLabel("The highscore is: " + highscore);
				score.setBounds(0, 0, 200, 50);
				
				JButton okayButton = new JButton("Okay");
				okayButton.setBounds(50, 80, 100, 30);
				okayButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						alert.dispose();
					}
				});
				
				alert.add(score);
				alert.add(okayButton);
				alert.setResizable(false);
				alert.setVisible(true);
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Closing...");
				System.exit(0);
			}
		});
		
		JMenuItem options = new JMenuItem("Options");
		options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Config.openConfig(frame);
			}
		});
		
		TetrisMain tm = new TetrisMain();
		tm.setBounds(0, 25, WIDTH, HEIGHT - 25);
		
		frame.add(tm);
		file.add(newGame);
		file.add(highScore);
		file.add(options);
		file.add(exit);
		bar.add(file);
		frame.add(bar);
		frame.setVisible(true);
		tm.start();
	}
	public void start()
	{
		Thread t = new Thread(this);
		t.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
	public void run()
	{
		init();
		boolean running = true;
		while(running)
		{
			update();
			BufferStrategy buf = getBufferStrategy();
			if (buf == null)
			{
				createBufferStrategy(3);
				continue;
			}
			Graphics2D g = (Graphics2D) buf.getDrawGraphics();
			render(g);
			buf.show();
		}
	}
	
	public void init() {
		requestFocus();
		try {
			tetrisBlocks = imageloader.loadImage("/tetrisblock.png", 25);
		}
		catch(IOException e){
			System.out.println("Error in loading in tetris.png");
			System.exit(1);
		}
	}
	public void update()
	{
		
	}
	public void render(Graphics2D g)
	{
		g.setColor(Color.PINK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Calibri", Font.PLAIN, 20));
		g.drawString("Tetris", 170, 50);
		g.drawImage(tetrisBlocks[0], 100, 100, 25, 25, null);
	}
	public void keyPressed(KeyEvent e)
	{
		
	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	public void keyReleased(KeyEvent e)
	{
		
	}

}
