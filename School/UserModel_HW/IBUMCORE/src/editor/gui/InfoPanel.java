package editor.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class InfoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InfoTextArea info;
	
    public InfoPanel() {

        info = new InfoTextArea();
        
        JScrollPane scrollpane = new JScrollPane(info,
            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        this.setLayout(new BorderLayout(10, 10));
        this.add("Center", scrollpane);
    }

    
	class InfoTextArea extends JTextArea {
		
		 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
//		final String initialInformation = "User Modeling Editor Messages\n\n";
		 
		    public InfoTextArea() {
		        super(5, 2);
//		        super(20, 2);
		        this.setEditable(false);
		        this.setBackground(Color.black);
		        this.setForeground(Color.white);
				this.setFont(new Font("Times New Roman", Font.PLAIN, 14));
//		        this.setText(initialInformation);
		        JScrollPane msgScroller = new JScrollPane(); 
		        this.add(msgScroller);
		    }
		    
		    @Override
		    public void append (String text) {
		    	Calendar cal = Calendar.getInstance();
		    	cal.getTime();
		    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    	
		    	
		        String oldText = this.getText();
		        String textToAppend = oldText + "\n" + 
		        		sdf.format(cal.getTime()) + " : " + text;
		        this.setText(textToAppend);
		        
		        
		    }

		    
		    public void write (String text) {
		        String oldText = this.getText();
		        String textToAppend = oldText + "\n" + text;
		        this.setText(textToAppend);
		    }
	
	}

	public void log(String log){
		info.append(log);
	}
	
	public void write(String txt){
		info.write(txt);
	}
	
	
	public InfoTextArea getInfo() {
		return info;
	}


	public void setInfo(InfoTextArea info) {
		this.info = info;
	}
	
	
}
