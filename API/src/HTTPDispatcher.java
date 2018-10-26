import java.util.HashMap;
import java.util.Map;

public class HTTPDispatcher {

	Map<String,Map<String,String>> routes;//method => path => impl
	private HTTPGETController getCtr;
	
	public HTTPDispatcher() {
		Map<String, String> paths = new HashMap<String, String>();
		paths.put("/trololo", "trololoRoute");
		paths.put("/trululu", "trululuRoute");
		paths.put("/", "blankRoute");
		Map<String,Map<String,String>> currentRouting = new HashMap<String,Map<String,String>>();
		currentRouting.put("GET", paths);
		this.routes = currentRouting;
		
		this.getCtr = new HTTPGETController();
	}
	
	public HTTPResponse dispatch(HTTPRequest request) {
		HTTPResponse response = new HTTPResponse();
		if(request.getMethod().equals("GET")) {
			if(this.routes.get("GET").containsKey(request.getPath())){
				switch(this.routes.get("GET").get(request.getPath())) {
				case "trololoRoute" :
					response.setContent(this.getCtr.getTrololo());
					break;
				case "trululuRoute" :
					response.setContent(this.getCtr.getTrululu());
					break;
				case "blankRoute" :
					response.setContent(this.getCtr.get());
					break;
				default :
					response.setContent("404 not found");
					break;
				}
			}else {
				response.setContent("404 not found");
			}
		}else {
			response.setContent("404 not found");
		}
		return response;
	}
	
}
