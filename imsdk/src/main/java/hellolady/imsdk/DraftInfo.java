package hellolady.imsdk;

import org.java_websocket.drafts.Draft;

/**
 * 项目名称：HeartPro
 * 类描述： 草案类型
 * 创建人：Jiamin.Sun
 * 创建时间：5/7/2016 8:24 PM
 * 修改人：Jiamin.Sun
 * 修改时间：5/7/2016 8:24 PM
 * 修改备注：
 */
public class DraftInfo {


    private  String draftName;



    private  Draft draft;

    public DraftInfo(String draftName, Draft draft) {
        this.draftName = draftName;
        this.draft = draft;

    }

    @Override
    public String toString() {
        return draftName;
    }

    public String getDraftName() {
        return draftName;
    }

    public void setDraftName(String draftName) {
        this.draftName = draftName;
    }
    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }
}
