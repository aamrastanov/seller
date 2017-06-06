package az.tezapp.seller.server.manager;

import org.springframework.util.StringUtils;

import az.tezapp.seller.server.exception.KeyAccessDeniedException;

public class SecurityManager {	

	private static String secret = "electrika";
	private static String key = "4#lk09_fg5s345k7";
	
	public static String getValid(String encrupted) throws KeyAccessDeniedException{
		if (StringUtils.isEmpty(encrupted)){
			throw new KeyAccessDeniedException();
		}
		String decrypted;
		try{
			decrypted = AESAlghorithm.decrypt(encrupted, key);
		}	
		catch(Exception e){
			throw new KeyAccessDeniedException();
		}
		if (decrypted.startsWith(secret)){
			return decrypted.substring(secret.length());
		}
		else{
			throw new KeyAccessDeniedException();
		}		
	}
		
}
