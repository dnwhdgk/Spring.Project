package com.bbs.vo;

public class Likey_R {

	private int boarder_id;
	private String user_id;
	
	public Likey_R() {}

	public Likey_R(int boarder_id, String user_id) {
		this.boarder_id = boarder_id;
		this.user_id = user_id;
	}

	public int getBoarder_id() {
		return boarder_id;
	}

	public void setBoarder_id(int boarder_id) {
		this.boarder_id = boarder_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
}