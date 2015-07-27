package pt.pxinxas.fcpp.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import pt.pxinxas.fcpp.map.GameMap;

import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapManager {

	private static final MapManager instance = new MapManager();

	private TmxMapLoader mapLoader = new TmxMapLoader(new LocalFileHandleResolver());

	private final Map<Integer, GameMap> gameMaps = new HashMap<Integer, GameMap>();

	private MapManager() {
		File fXmlFile = new File("maps/maps.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("map");
			for (int i = 0; i < nList.getLength(); i++) {
				Node item = nList.item(i);
				if (item.getNodeType() == Node.ELEMENT_NODE) {
					Element map = (Element) item;
					GameMap gameMap = new GameMap();
					gameMap.setId(Integer.valueOf(map.getAttribute("id")));
					gameMap.setName(map.getElementsByTagName("name").item(0).getTextContent());
					gameMap.setTmxFile(map.getElementsByTagName("tmx").item(0).getTextContent());
					gameMap.setThumbnail(map.getElementsByTagName("thumbnail").item(0).getTextContent());
					gameMaps.put(gameMap.getId(), gameMap);
				}
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<Integer, GameMap> getMaps() {
		return this.gameMaps;
	}

	public TiledMap load(Integer mapId) {
		return mapLoader.load("maps/" + gameMaps.get(mapId).getTmxFile());
	}

	/**
	 * @return the instance
	 */
	public static MapManager getInstance() {
		return instance;
	}

}
