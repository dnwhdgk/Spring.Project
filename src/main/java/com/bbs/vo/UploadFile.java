package com.bbs.vo;

public class UploadFile {
	
	private int file_id;
    private int boarder_id;
    private String file_name;
    private String file_realName;
    private int count;
    
    public UploadFile() {}
    
	public UploadFile(int file_id, int boarder_id, String file_name, String file_realName, int count) {
		this.file_id = file_id;
		this.boarder_id = boarder_id;
		this.file_name = file_name;
		this.file_realName = file_realName;
		this.count = count;
	}
	
	public int getFile_id() {
		return file_id;
	}
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	public int getBoarder_id() {
		return boarder_id;
	}
	public void setBoarder_id(int boarder_id) {
		this.boarder_id = boarder_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_realName() {
		return file_realName;
	}
	public void setFile_realName(String file_realName) {
		this.file_realName = file_realName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
    
}