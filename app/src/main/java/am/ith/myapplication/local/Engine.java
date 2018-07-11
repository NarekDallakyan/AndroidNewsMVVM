package am.ith.myapplication.local;

import java.util.List;

import am.ith.myapplication.local.entity.SaveColorModel;
import am.ith.myapplication.model.AppResponse;

public class Engine {
    private static Engine engine=null;
    private List<SaveColorModel> saveColorModelList;

    public List<SaveColorModel> getSaveColorModelList() {
        return saveColorModelList;
    }

    public void setSaveColorModelList(List<SaveColorModel> saveColorModelList) {
        this.saveColorModelList = saveColorModelList;
    }

    private List<AppResponse.Metadatum> appResponse;
    private AppResponse.Metadatum metadata;

    public AppResponse.Metadatum getMetadata() {
        return metadata;
    }

    public void setMetadata(AppResponse.Metadatum metadata) {
        this.metadata = metadata;
    }

    public List<AppResponse.Metadatum> getAppResponse() {
        return appResponse;
    }

    public void setAppResponse(List<AppResponse.Metadatum> appResponse) {
        this.appResponse = appResponse;
    }

    private Engine() {
    }
    public static Engine getInstance(){
        return (engine==null)?engine=new Engine():engine;
    }
}

