package editor.schema;

import java.awt.Point;

import editor.gui.Editor.ElementType;

/**
 * the editor's place holders for the elements of the user model
 */
public class Vertex {

	/* ==== vertex fields === */

	private int id;
	private String label;
	private double x;
	private double y;

	/* === element fields === */

	private ElementType type;
	private String name;
	private String[] inputs;
	private String[] outputs;

	/**
	 * constructor
	 */
	public Vertex() {
	}

	/**
	 * constructor
	 * 
	 * @param id
	 *            serial id of vertex in the user model
	 * @param type
	 *            the type of the element that the vertex represents
	 *            (INFOBEAD/SENSORINFOBEAD/SERVICEINFOBEAD...)
	 * @param name
	 *            the file name of the element without the ".class" extension
	 * @param inputInterfaces
	 *            a list of input interface names that the element implements
	 * @param outputInterfaces
	 *            a list of output interface names that the element implements
	 */
	public Vertex(int id, ElementType type, String name,
			String[] inputInterfaces, String[] outputInterfaces) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.inputs = inputInterfaces;
		this.outputs = outputInterfaces;
		this.label = name + " #" + id;
	}

	@Override
	public String toString() {

		return "id #" + id + " , type : " + type + ", name : " + name;
	}

	/* ~~~~~~~~~~~~~~~~~ getters & setters ~~~~~~~~~~~~~~~~~ */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setLocation(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}

	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getX() {
		return x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public ElementType getType() {
		return type;
	}

	public void setType(ElementType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getInputs() {
		return inputs;
	}

	public void setInputs(String[] inputs) {
		this.inputs = inputs;
	}

	public String[] getOutputs() {
		return outputs;
	}

	public void setOutputs(String[] outputs) {
		this.outputs = outputs;
	}



}