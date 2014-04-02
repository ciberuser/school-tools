package editor.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import editor.gui.Editor;

public class MenuListener implements ActionListener{
	
	public MenuListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
//		Component component = (Component) event.getSource();
		String command = event.getActionCommand();
		
		/*----------------FIle menu---------------*/
			
		if( command.equalsIgnoreCase("Exit")){
			exit();
		} else if( command.equalsIgnoreCase("New user model")){
			newUM();
		} else if( command.equalsIgnoreCase("New group model")){
			newGM();
		} else if( command.equalsIgnoreCase("Open user model")){
			openUM();
		} else if( command.equalsIgnoreCase("Open group model")){
			openGM();
		} else if(command.equalsIgnoreCase("Close")){
			close();
		} else if( command.equalsIgnoreCase("Import info-bead")){
			importInfoBead();
		} else if( command.equalsIgnoreCase("Save")){
			save();
		} else if( command.equalsIgnoreCase("Save model as")){
			saveUserModelAs();
		} else if( command.equalsIgnoreCase("Save info-pendant as")){
			saveInfoPendantAs();
		} else if( command.equalsIgnoreCase("Delete model or info-pendant from list")){
			deleteSavedModel();	
			
			/*--------------Edit menu -----------------*/
			
		} else if( command.equalsIgnoreCase("Reset info-bead colors")){
			resetInfoBeadColors();
		} else if( command.equalsIgnoreCase("Find info-bead")){
			findInfoBead();
		} else if( command.equalsIgnoreCase("Show info-pendant")){
			showInfoPendant();
		} else if( command.equalsIgnoreCase("Show sub-model")){
			showModel();
		} else if( command.equalsIgnoreCase("Copy info-bead")){
			copyInfoBead();
		} else if( command.equalsIgnoreCase("Paste info-bead")){
			pasteInfoBead();
		} else if( command.equalsIgnoreCase("Delete info-bead")){
			deleteInfoBead();
		} else if( command.equalsIgnoreCase("Delete sub-model from group model")){
			deleteSubModel();

		 
		
		
			/*-------------  View Menu -----------------------*/
		} else if( command.equalsIgnoreCase("Zoom in")){
			zoomIn();
		} else if( command.equalsIgnoreCase("Zoom out")){
			zoomOut();
		} else if( command.equalsIgnoreCase("Pan")){
			pan();
		} else if( command.equalsIgnoreCase("Rotate")){
			rotate();
		} else if( command.equalsIgnoreCase("Move info-bead")){
			moveInfobead();
		
		
			/*----------------  Run Menu  --------------------*/
		
		} else if( command.equalsIgnoreCase("Activate model")){
			activateModel();
			
			
			/*----------------  Help Menu -------------------*/
		} else if( command.equalsIgnoreCase("Introduction to info-bead User Modeling")){
			introductionIBUM();
			
		} else if( command.equalsIgnoreCase("Info-bead help")){
			infoBeadHelp();
			
		} else if( command.equalsIgnoreCase("About the Editor")){
			about();
		} else {
			JOptionPane.showMessageDialog(null, "**** Unknown menu item: " + command.toString());
		}
		
		
	}


	/*-----------FIle Menu --------------*/
	
	
	private void exit() {
		Editor.getInstance().saveBeforeQuit();
		Editor.getInstance().log("File menu command : Exit");
		System.exit(0);	
	}

	private void newUM() {
		Editor.getInstance().log("File menu command : New user model");
		Editor.getInstance().newUserModel();
	}

	private void newGM() {
		Editor.getInstance().log("File menu command : New group model");
		Editor.getInstance().newGroupModel();
	}

	private void openUM() {
		Editor.getInstance().log("File nmenu command : Open user model");
		Editor.getInstance().openUserModel();
	}

	private void openGM() {
		Editor.getInstance().log("File nmenu command : Open group model");
		Editor.getInstance().openGroupModel();
	}

	private void close() {
		Editor.getInstance().log("File menu command : Close User Model & Graph");
		Editor.getInstance().saveBeforeQuit();
		Editor.getInstance().Close();
	}

	private void importInfoBead() {
		Editor.getInstance().log("File mMenu command : Import info-bead");
		Editor.getInstance().importInfoBead();
	}

	private void save() {
		Editor.getInstance().log("File menu command : Save");
		Editor.getInstance().save();
		
	}

	private void saveUserModelAs() {
		Editor.getInstance().log("File menu command : Save model as");
		Editor.getInstance().saveModelAs();
	}
	

	private void saveInfoPendantAs() {
		Editor.getInstance().log("File menu command : Save info-pendant as");
		Editor.getInstance().saveInfoPendantAs();
	}
	
	
	private void deleteSavedModel(){
		Editor.getInstance().log("File menu command : Delete model or info-pendant from list");
		Editor.getInstance().deleteSavedModelItemFromList();

	}
	
	
	/* -------------------  Edit Menu ----------------------*/

	private void resetInfoBeadColors() {
		Editor.getInstance().log("Edit menu command : Reset info-bead colors");
		Editor.getInstance().resetInfoBeadColors();
		}


	private void findInfoBead() {
		Editor.getInstance().log("Edit menu command : Find info-bead");
		Editor.getInstance().findInfoBead();
	}
		
		
	private void showInfoPendant() {
		Editor.getInstance().log("Edit menu command : Show info-pendant");
		Editor.getInstance().showInfoPendant();
	}	
	
	private void showModel() {
		Editor.getInstance().log("Edit menu command : Show sub-model");
		Editor.getInstance().showSubModel();
	}
	
	
	private void copyInfoBead() {
		Editor.getInstance().log("Edit menu command : Copy info-bead");
		Editor.getInstance().copyInfoBead();
	}
	
	
	private void pasteInfoBead() {
		Editor.getInstance().log("Edit menu command : Paste info-bead");
		Editor.getInstance().pasteInfoBead();
	}
	
	
	private void deleteInfoBead() {
		Editor.getInstance().log("Edit menu command : Delete info-bead");
		Editor.getInstance().deleteInfoBead();
		
	}
	
	
//	private void addSubModel() {
//		Editor.getInstance().log("Edit menu command : Add sub-model to group model");
//		Editor.getInstance().addSubModelToGroupModel();
//		
//	}
//	
	
	private void deleteSubModel() {
		Editor.getInstance().log("Edit menu command : Delete sub-model from group model");
		Editor.getInstance().deleteSubModelFromGroupModel();
		
	}
	
	/*-------------  View Menu -----------------------*/

	private void zoomIn() {
		Editor.getInstance().log("View menu command : Zoom in");
		Editor.getInstance().viewZoomIn();
	}
	
	private void zoomOut() {
		Editor.getInstance().log("View menu command : Zoom out");
		Editor.getInstance().viewZoomOut();
	}
	
	
	private void pan() {
		Editor.getInstance().log("View menu command : Pan");
		Editor.getInstance().viewPan();
	}
	
	
		private void rotate() {
		Editor.getInstance().log("View menu command : rotate");
		Editor.getInstance().viewRotate();
	}
		
		
	private void moveInfobead() {
		Editor.getInstance().log("View menu command : rotate");
		Editor.getInstance().viewMoveInfobead();

	}
		
		
	/*-------------  Run Menu -----------------------*/

	private void activateModel() {
		Editor.getInstance().log("Run menu command : Activate model");
		Editor.getInstance().launchModel();
	}
		
	
	/*-------------  Help Menu -----------------------*/

	private void introductionIBUM() {
		// TODO : add closing procedures if needed
		Editor.getInstance().log("Help menu command : Introduction to info-bead User Modeling");
		Editor.getInstance().helpIntroduction();
	}
	
	
	private void infoBeadHelp() {
		// TODO : add closing procedures if needed
		Editor.getInstance().log("Help menu command : Info-bead help");
		Editor.getInstance().helpInfobead();
	}
	
	private void about() {
		Editor.getInstance().log("Help menu command : About the Editor");
		Editor.getInstance().helpAbout();
	}
	
}


