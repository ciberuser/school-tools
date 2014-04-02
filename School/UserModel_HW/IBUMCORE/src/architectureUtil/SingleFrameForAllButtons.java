package architectureUtil;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class SingleFrameForAllButtons {
	
	static JFrame scrolableFrame;
	
	static JScrollPane buttonsPane;
	
	static SingleFrameForAllButtons instance = new SingleFrameForAllButtons();

	SingleFrameForAllButtons(){
		buttonsPane = openFrame();
	}

	public JScrollPane openFrame(){
		
       final JFrame  frame = new JFrame("Assistive Tecnology Data Input");
       JPanel buttons = new JPanel();
       buttons.setLayout(new GridLayout(2000, 1)); // up to 2000 buttons (rows)
       final JScrollPane scrollPane = new JScrollPane(buttons);
       EventQueue.invokeLater(new Runnable() {
          @Override
          public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(scrollPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
//                frame.setSize(900, 200);
                frame.setVisible(true);
            }
        });
        return scrollPane;
	}
	
	

	public JScrollPane getScrollPane() {
			return buttonsPane;

    }


	public static SingleFrameForAllButtons getInstance() {
		return instance;
	}

	
}
