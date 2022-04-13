package com.mycompany.todo;

import java.util.Date;
import java.util.Objects;

public class Todo {

	private int id;
	private String user;
	private String desc;
	private Date targetDate;
	private String isDone;

	public Todo(int id, String user, String desc, Date targetDate, String isDone) {
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getTargetDate() {
		return this.targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getIsDone() {
		return this.isDone;
	}

	public void setIsDone(String isDone) {
		this.isDone = isDone;
	}

	@Override
	public String toString() {
		return "Todo [" +
				" id='" + getId() + "'" +
				", user='" + getUser() + "'" +
				", desc='" + getDesc() + "'" +
				", targetDate='" + getTargetDate() + "'" +
				", isDone='" + getIsDone() + "'" +
				"]";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Todo)) {
			return false;
		}
		Todo todo = (Todo) o;
		return id == todo.id //&& Objects.equals(user, todo.user) && Objects.equals(desc, todo.desc)
		//&& Objects.equals(targetDate, todo.targetDate) && Objects.equals(isDone, todo.isDone)
		;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, desc, targetDate, isDone);
	}

}
