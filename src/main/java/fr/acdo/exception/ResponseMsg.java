package fr.acdo.exception;

public class ResponseMsg {

	private String msgError;

	public ResponseMsg(String msgError) {
		super();
		this.msgError = msgError;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

}
