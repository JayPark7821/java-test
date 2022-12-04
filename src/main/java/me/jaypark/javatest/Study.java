package me.jaypark.javatest;

public class Study {

	private int limit;
	private String name;

	private StudyStatus status = StudyStatus.DRAFT;

	public Study(int limit, String name) {
		this.limit = limit;
		this.name = name;
	}

	public Study(int limit) {
		if (limit < 0) {
			throw new IllegalArgumentException("limit은 0보다 커야 한다.");
		}
		this.limit = limit;
	}

	public StudyStatus getStatus() {
		return this.status;
	}

	public int getLimit() {
		return this.limit;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Study{" +
			"limit=" + limit +
			", name='" + name + '\'' +
			", status=" + status +
			'}';
	}
}

