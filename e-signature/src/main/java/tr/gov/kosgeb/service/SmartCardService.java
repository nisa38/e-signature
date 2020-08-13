package tr.gov.kosgeb.service;

import org.springframework.stereotype.Service;
import tr.gov.kosgeb.business.SmartCardManager;
import tr.gov.kosgeb.model.ESignerUserInfo;
import tr.gov.tubitak.uekae.esya.api.asn.x509.ECertificate;
import tr.gov.tubitak.uekae.esya.api.common.ESYAException;
import tr.gov.tubitak.uekae.esya.api.common.crypto.BaseSigner;

@Service
public class SmartCardService {

    public ESignerUserInfo getESignerUserInfo() throws ESYAException {
        ESignerUserInfo response=null;

        ECertificate es = SmartCardManager.getInstance().getSignatureCertificate(false);
        response = new ESignerUserInfo(es.getSubject().getSerialNumberAttribute(),es.getSubject().getCommonNameAttribute(),
                es.getNotBefore(),es.getNotAfter(),es.getIssuer().getCommonNameAttribute(),0);
        return response;
    }

    public ESignerUserInfo loginUser(String pinCode) throws ESYAException {
        ECertificate es = SmartCardManager.getInstance().getSignatureCertificate(false);
        BaseSigner bs = SmartCardManager.getInstance().getSigner(pinCode,es);
        ESignerUserInfo response = new ESignerUserInfo(es.getSubject().getSerialNumberAttribute(),es.getSubject().getCommonNameAttribute(),
                es.getNotBefore(),es.getNotAfter(),es.getIssuer().getCommonNameAttribute(),0);
        return response;
    }

    public void signDocument(String pinCode) throws ESYAException {
        //TODO
    }
}
