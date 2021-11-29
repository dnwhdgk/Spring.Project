package com.bbs.vo;

public class Boarder {

	private int boarder_id;
	private String wirter;
	private String title;
	private String contents;
	private String reg_date;
	private int likey;
	private int available;
	
	public Boarder() {}

	public Boarder(int boarder_id, String wirter, String title, String contents, String reg_date, int likey, int available) {
		this.boarder_id = boarder_id;
		this.wirter = wirter;
		this.title = title;
		this.contents = contents;
		this.reg_date = reg_date;
		this.likey = likey;
		this.available = available;
	}

	public int getBoarder_id() {
		return boarder_id;
	}

	public void setBoarder_id(int boarder_id) {
		this.boarder_id = boarder_id;
	}

	public String getWirter() {
		return wirter;
	}

	public void setWirter(String wirter) {
		this.wirter = wirter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public int getLikey() {
		return likey;
	}

	public void setLikey(int likey) {
		this.likey = likey;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
}