package az.tezapp.seller.server.model;

public class AppProperties {

    private String resourcesWebName;

    private String resourcesPath;

    public AppProperties(String resourcesWebName, String resourcesPath) {
        super();
        this.resourcesWebName = resourcesWebName;
        this.resourcesPath = resourcesPath;
    }

    public String getResourcesPath() {
        return resourcesPath;
    }

    public String getResourcesWebName() {
        return resourcesWebName;
    }

}
