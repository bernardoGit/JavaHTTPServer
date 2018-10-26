import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTTPServer {

	
	
	private HTTPDispatcher dispatcher;

	public HTTPServer() {
		this.dispatcher = new HTTPDispatcher();
	}
	
	public void start() throws IOException {	
		ServerSocket srvSocket = new ServerSocket(8070);
		while(true) {
			Socket socket = srvSocket.accept();
			System.out.println(socket.hashCode());
			dispatch(socket);
			socket.close();
		}
//		srvSocket.close();
	}

	private void dispatch(Socket socket) throws IOException {
		HTTPRequest request = new HTTPRequest(socket);
		System.out.println(request);
		HTTPResponse response = this.dispatcher.dispatch(request);
		socket.getOutputStream().write(response.getContent().getBytes());
	}

	
}
