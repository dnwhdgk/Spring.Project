package com.bbs.vo;

public class Reply {
	
	private int reply_id;
	private int boarder_id;
	private String writer;
	private int parent_id;
	private String contents;
	private String reg_date;
	
	public Reply() {}

	public Reply(int reply_id, int boarder_id, String writer, int parent_id, String contents, String reg_date) {
		this.reply_id = reply_id;
		this.boarder_id = boarder_id;
		this.writer = writer;
		this.parent_id = parent_id;
		this.contents = contents;
		this.reg_date = reg_date;
	}

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getBoarder_id() {
		return boarder_id;
	}

	public void setBoarder_id(int boarder_id) {
		this.boarder_id = boarder_id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
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
	
}