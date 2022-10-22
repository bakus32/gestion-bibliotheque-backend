package ci.gs2e.biblio.business.fact;


import java.util.Locale;

import ci.gs2e.biblio.helper.contrat.IBasicBusiness;


public interface IUserBusiness<T, K> extends IBasicBusiness<T, K>{

	/**
	 * get a List of Object by using T as criteria object.
	 * 
	 * @param T
	 * @return K
	 * 
	 */
	K resetAccount(T request, Locale locale) throws Exception;
	

	K changePassword(T request, Locale locale) throws Exception;
	

	K login(T request, Locale locale) throws Exception;

}

