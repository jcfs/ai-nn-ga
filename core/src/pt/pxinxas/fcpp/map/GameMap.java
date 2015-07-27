package pt.pxinxas.fcpp.map;

public class GameMap {
	private Integer id;
	private String name;
	private String tmxFile;
	private String thumbnail;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the tmxFile
	 */
	public String getTmxFile() {
		return tmxFile;
	}

	/**
	 * @param tmxFile
	 *            the tmxFile to set
	 */
	public void setTmxFile(String tmxFile) {
		this.tmxFile = tmxFile;
	}

	/**
	 * @return the thumbnail
	 */
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * @param thumbnail the thumbnail to set
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
}
