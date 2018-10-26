import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HTTPRequest {

	private String headers;	
	private String method;
	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	private String path;
	
	public HTTPRequest(Socket socket) {
		this.headers = "";
		try {
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			int c;
			boolean exit = false;
			while(!exit) {
				c = isr.read();
				this.headers += (char)c;
				if(this.headers.contains("\r\n\r\n")){
					this.headers = this.headers.replaceAll("(\\r|\\n)", " - ");
					exit = true;
				}
			}
			System.out.println(this.headers);
			String[] splitedHeaders = this.headers.split(" ");
			this.method = splitedHeaders[0];
			this.path = splitedHeaders[1];
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("HTTPRequest ");
		sb.append(this.method);
		sb.append(" ");
		sb.append(this.path);
		return sb.toString();
	}
	
	
	
	
	
	
}
