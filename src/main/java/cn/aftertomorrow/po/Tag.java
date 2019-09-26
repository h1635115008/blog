package cn.aftertomorrow.po;

public class Tag {
	private Integer id;
	private String name;
	private String kind;
	private String unicode;

	public Integer getTag() {
		return id;
	}

	public void setTag(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + ", kind=" + kind + ", unicode=" + unicode + "]";
	}

}
