package ci.gs2e.biblio.business.fact;

import java.util.Locale;

public interface IUsagerBusiness<T, K> extends IUserBusiness<T, K>{

	K emprunterLivre(T request, Locale locale) throws Exception;
}
