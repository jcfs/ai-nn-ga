package pt.pxinxas.fcpp.manager;

import pt.pxinxas.fcpp.entity.Block;
import pt.pxinxas.fcpp.game.ClientGameStaticContext;
import pt.pxinxas.fcpp.util.Point;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class ClientMapManager {

	private static final ClientMapManager instance = new ClientMapManager();
	private OrthogonalTiledMapRenderer renderer;
	private TiledMap map;

	public ClientMapManager() {
	}

	public void update(float delta) {
		renderer.setView(CameraManager.getInstance().getActiveCamera());
	}

	public void renderBottomLayer() {
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(1));
	}

	public void renderTopLayer() {
		renderer.renderTileLayer((TiledMapTileLayer) map.getLayers().get(2));
	}

	public TiledMap loadMap(Integer mapId) {
		map = MapManager.getInstance().load(mapId);
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("collision");
		for (int x = 0; x < layer.getWidth(); x++) {
			for (int y = 0; y < layer.getHeight(); y++) {
				Cell cell = layer.getCell(x, y);
				if (cell != null
						&& (cell.getTile().getId() == 9 || (cell.getTile().getProperties() != null && cell.getTile().getProperties()
								.containsKey("col")))) {
					int tileWidth = cell.getTile().getTextureRegion().getRegionWidth();
					int tileHeight = cell.getTile().getTextureRegion().getRegionHeight();
					new Block(x * tileWidth + tileWidth / 2, y * tileHeight + tileHeight / 2, tileWidth, tileHeight);

				} else if (cell != null && cell.getTile().getProperties().containsKey("spawn")) {
					int tileWidth = cell.getTile().getTextureRegion().getRegionWidth();
					int tileHeight = cell.getTile().getTextureRegion().getRegionHeight();

					int xPosition = x * tileWidth + tileWidth / 2;
					int yPosition = y * tileHeight + tileHeight / 2;

					ClientGameStaticContext.getInstance().setSpawnPoint(new Point(xPosition, yPosition));
				} else if (cell != null && cell.getTile().getProperties().containsKey("target")) {
					int tileWidth = cell.getTile().getTextureRegion().getRegionWidth();
					int tileHeight = cell.getTile().getTextureRegion().getRegionHeight();

					int xPosition = x * tileWidth + tileWidth / 2;
					int yPosition = y * tileHeight + tileHeight / 2;

					ClientGameStaticContext.getInstance().setTargetPoint(new Point(xPosition, yPosition));
				}
			}
		}
		this.renderer = new OrthogonalTiledMapRenderer(map, SpriteBatchManager.getInstance().getBatch());
		return map;
	}

	/**
	 * @return the instance
	 */
	public static ClientMapManager getInstance() {
		return instance;
	}

}
