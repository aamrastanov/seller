package az.tezapp.seller.server.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import az.tezapp.seller.server.model.AppProperties;

@Component
public class ResourcesManager {
	
	@Autowired
	private AppProperties appProperties;
	
	private static SimpleDateFormat dateFormatter = new SimpleDateFormat("/yyyy/MM/dd/");
	
	private static Pattern filePattern = Pattern.compile("\\..{0,5}$");
	
	public String getWebUri(String fileName){		
		return appProperties.getResourcesWebName() + dateFormatter.format(new Date()) + fileName;
	}
	
	public String getLocalResoucesPath(){
		return appProperties.getResourcesPath();
	}
	
	public String generateUniqueFileName(String prefix, String originalFileName){
		String fileExtends;
		Matcher mt = filePattern.matcher(originalFileName); 
		if (mt.find()){
			fileExtends = mt.group();
		}
		else{
			fileExtends = "";
		}
		String uuidValue = UUID.randomUUID().toString();
		return prefix + uuidValue.substring(0, uuidValue.indexOf("-")) + fileExtends;
	}

}
