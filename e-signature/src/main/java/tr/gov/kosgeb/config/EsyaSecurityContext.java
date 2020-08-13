package tr.gov.kosgeb.config;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import tr.gov.tubitak.uekae.esya.api.common.util.LicenseUtil;
import tr.gov.tubitak.uekae.esya.api.infra.tsclient.TSSettings;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class EsyaSecurityContext {
    private InputStream licenceFile;
    private InputStream policyFile;
    private InputStream sertifikaDeposuFile;
    private int tStampCustomerId;
    private String tStampPass;
    private TSSettings tsSetting;

    @PostConstruct
    public void setValues() throws Exception{
        //TODO : these values will be retrieved from service. For now, these are set from resources
        readFromResources();
    }

    private void readFromResources() throws Exception{
        setLicenceFile(new FileInputStream(ResourceUtils.getFile("classpath:config/lisans.xml")));
        LicenseUtil.setLicenseXml(getLicenceFile());
    }

    public InputStream getLicenceFile() {
        return licenceFile;
    }

    public void setLicenceFile(InputStream licenceFile) {
        this.licenceFile = licenceFile;
    }

    public InputStream getPolicyFile() {
        return policyFile;
    }

    public void setPolicyFile(InputStream policyFile) {
        this.policyFile = policyFile;
    }

    public InputStream getSertifikaDeposuFile() {
        return sertifikaDeposuFile;
    }

    public void setSertifikaDeposuFile(InputStream sertifikaDeposuFile) {
        this.sertifikaDeposuFile = sertifikaDeposuFile;
    }

    public int gettStampCustomerId() {
        return tStampCustomerId;
    }

    public void settStampCustomerId(int tStampCustomerId) {
        this.tStampCustomerId = tStampCustomerId;
    }

    public String gettStampPass() {
        return tStampPass;
    }

    public void settStampPass(String tStampPass) {
        this.tStampPass = tStampPass;
    }

    public TSSettings getTsSetting() {
        return tsSetting;
    }

    public void setTsSetting(TSSettings tsSetting) {
        this.tsSetting = tsSetting;
    }
}
