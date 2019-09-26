package cn.aftertomorrow.po;

public class Page {
	private Integer start;
	private Integer size;

	public Page(Integer start, Integer size) {
		super();
		this.start = start;
		this.size = size;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

}
