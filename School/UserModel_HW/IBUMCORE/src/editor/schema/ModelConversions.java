package editor.schema;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Conversions among models, where casting is not possible
 * @author Eyal
 *
 */
public class ModelConversions {

	/**
	 * Converts a model into a user model
	 * @param model the model to be converted
	 * @return the user model
	 */
	public UserModel model2UserModel(Model model){
		UserModel userModel = new UserModel();
		userModel.setEdgeMap(model.getEdgeMap());
		userModel.setVertexMap(model.getVertexMap());
		userModel.setInputLinkMap(model.getInputLinkMap());
		userModel.setOutputLinkMap(model.getOutputLinkMap());
		userModel.setModelId(model.getModelId());
		return userModel;
	}
	
	
	/**
	 * Converts a user model into a  model
	 * @param uModel the model to be converted
	 * @return the model
	 */
	public Model userModel2Model(UserModel uModel){
		Model model = new Model();
		model.setEdgeMap(uModel.getEdgeMap());
		model.setVertexMap(uModel.getVertexMap());
		model.setInputLinkMap(uModel.getInputLinkMap());
		model.setOutputLinkMap(uModel.getOutputLinkMap());
		model.setModelId(uModel.getModelId());
		return model;
	}
	
	
	public GroupModel model2GroupModel (Model model) {
		GroupModel groupModel = new GroupModel();
		groupModel.setEdgeMap(model.getEdgeMap());
		groupModel.setVertexMap(model.getVertexMap());
		groupModel.setInputLinkMap(model.getInputLinkMap());
		groupModel.setOutputLinkMap(model.getOutputLinkMap());
		groupModel.setModelId(model.getModelId());
		return groupModel;
	}
	
	
	public GroupModel userModel2GroupModel (UserModel userModel) {
		GroupModel groupModel = new GroupModel();
		groupModel.setEdgeMap(userModel.getEdgeMap());
		groupModel.setVertexMap(userModel.getVertexMap());
		groupModel.setInputLinkMap(userModel.getInputLinkMap());
		groupModel.setOutputLinkMap(userModel.getOutputLinkMap());
		groupModel.setModelId(userModel.getModelId());
		return groupModel;
	}
	
	
	public ArrayList<Integer> hashSet2ArrayList (HashSet<Integer> hs){
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.addAll(hs);
		return al;
	}
	
	
	public HashSet<Integer> arrayList2HashSet (ArrayList<Integer> al){
		HashSet<Integer> hs = new HashSet<Integer>();
		hs.addAll(al);
		return hs;
	}
	
	
}
