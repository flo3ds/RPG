package gui.GenWorld;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLMap {

	private DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	private DocumentBuilder docBuilder = null;
	private Document doc;
	private String path;
	private ArrayList<Element> tileset = new ArrayList<Element>();
	private ArrayList<Element> layer = new ArrayList<Element>();

	public XMLMap(String path) {
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		this.path = path;
	}

	public void setTileset(Element tileset) {
		this.tileset.add(tileset);
	}

	public void setLayer(Element layer) {
		this.layer.add(layer);
	}

	public Document getDoc() {
		return doc;
	}

	private Element map() {
		Element map = doc.createElement("map");
		map.setAttribute("version", "1.0");
		map.setAttribute("orientation", "orthogonal");
		map.setAttribute("renderorder", "right-down");
		map.setAttribute("width", "20");
		map.setAttribute("height", "20");
		map.setAttribute("tilewidth", "32");
		map.setAttribute("tileheight", "32");
		map.setAttribute("nextobjectid", "1");
		return map;
	}

	public void write() {

		Element map = map();
		doc.appendChild(map);

		Iterator<Element> it = this.tileset.iterator();
		while (it.hasNext())
			map.appendChild(it.next());

		it = this.layer.iterator();
		while (it.hasNext())
			map.appendChild(it.next());

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(path));

		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);

		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("File saved!");
	}

}
