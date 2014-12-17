package org.l3cache.support;

public class QueryValidator {

	public boolean validate(String query) {
		
		if(query.trim().isEmpty()){
			return false;
		}
		
		if(query.length()>=100){
			return false;
		}
		
		return true;
	}

}
