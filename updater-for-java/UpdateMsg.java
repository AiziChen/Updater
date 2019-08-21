/**
 * 更新Record
 * @author Quanyec
 */
public class UpdateMsg {
    private String binUrl;
    private String msgUrl;

    public void setBinUrl(String binUrl) {
        this.binUrl = binUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getBinUrl() {
        return this.binUrl;
    }

    public String getMsgUrl() {
        return this.msgUrl;
    }

    @Override
    public String toString() {
        return "UpdateMsg(" + binUrl + ", " + msgUrl + ")";
    }
}