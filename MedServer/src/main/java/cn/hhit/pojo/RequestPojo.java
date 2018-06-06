package cn.hhit.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class RequestPojo {
	private String CharacterEncoding;
	private int ContentLength;
	private String LocalAddr;
	private String LocalName;
	private int LocalPort;
	private String RemoteAddr;
	private int RemotePort;
	private String Protocol;
	private String ServerName;
	private int ServerPort;
	private String RequestURI;
	private String RequestedSessionId;
	private String RemoteUser;
	private String Param;
	private String date;

	public RequestPojo() {
		super();
	}
	
	public RequestPojo(String characterEncoding, int contentLength, String localAddr, String localName, int localPort,
			String remoteAddr, int remotePort, String protocol, String serverName, int serverPort, String requestURI,
			String requestedSessionId, String remoteUser, String param,String l) {
		super();
		CharacterEncoding = characterEncoding;
		ContentLength = contentLength;
		LocalAddr = localAddr;
		LocalName = localName;
		LocalPort = localPort;
		RemoteAddr = remoteAddr;
		RemotePort = remotePort;
		Protocol = protocol;
		ServerName = serverName;
		ServerPort = serverPort;
		RequestURI = requestURI;
		RequestedSessionId = requestedSessionId;
		RemoteUser = remoteUser;
		Param = param;
		this.date = l;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getParam() {
		return Param;
	}

	public void setParam(String param) {
		Param = param;
	}

	public String getCharacterEncoding() {
		return CharacterEncoding;
	}
	public void setCharacterEncoding(String characterEncoding) {
		CharacterEncoding = characterEncoding;
	}
	public int getContentLength() {
		return ContentLength;
	}
	public void setContentLength(int contentLength) {
		ContentLength = contentLength;
	}
	public String getLocalAddr() {
		return LocalAddr;
	}
	public void setLocalAddr(String localAddr) {
		LocalAddr = localAddr;
	}
	public String getLocalName() {
		return LocalName;
	}
	public void setLocalName(String localName) {
		LocalName = localName;
	}
	public int getLocalPort() {
		return LocalPort;
	}
	public void setLocalPort(int localPort) {
		LocalPort = localPort;
	}
	public String getRemoteAddr() {
		return RemoteAddr;
	}
	public void setRemoteAddr(String remoteAddr) {
		RemoteAddr = remoteAddr;
	}
	public int getRemotePort() {
		return RemotePort;
	}
	public void setRemotePort(int remotePort) {
		RemotePort = remotePort;
	}
	public String getProtocol() {
		return Protocol;
	}
	public void setProtocol(String protocol) {
		Protocol = protocol;
	}
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public int getServerPort() {
		return ServerPort;
	}
	public void setServerPort(int serverPort) {
		ServerPort = serverPort;
	}
	public String getRequestURI() {
		return RequestURI;
	}
	public void setRequestURI(String requestURI) {
		RequestURI = requestURI;
	}
	public String getRequestedSessionId() {
		return RequestedSessionId;
	}
	public void setRequestedSessionId(String requestedSessionId) {
		RequestedSessionId = requestedSessionId;
	}
	public String getRemoteUser() {
		return RemoteUser;
	}
	public void setRemoteUser(String remoteUser) {
		RemoteUser = remoteUser;
	}
	
	
	
	
}
