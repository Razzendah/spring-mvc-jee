package com.mycompany.todo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Size;

public class Todo {

	private int id;
	private String user;
	@Size(min = 6, message = "Enter at least 6 characters")
	private String desc;
	private Date targetDate;
	private boolean done;

	public Todo(int id, String user, String desc, Date targetDate, boolean done) {
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.targetDate = targetDate;
		this.done = done;
	}

	public Todo() {
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

	public boolean isDone() {
		return this.done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [" +
				" id='" + getId() + "'" +
				", user='" + getUser() + "'" +
				", desc='" + getDesc() + "'" +
				", targetDate='" + getTargetDate() + "'" +
				", isDone='" + isDone() + "'" +
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
		return Objects.hash(id, user, desc, targetDate, done);
	}

}
