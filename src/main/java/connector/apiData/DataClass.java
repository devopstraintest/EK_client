package connector.apiData;


public class DataClass {

    public String index;
    public String mapping;
    public String docName;
    public boolean checkIndex;
    public String dataLocation;
    public boolean deleteControl;

    public DataClass( String index, boolean checkIndex, String docName, String mapping, String dataLocation, boolean deleteControl ){

        this.index = index;
        this.checkIndex = checkIndex;
        this.docName = docName;
        this.mapping = mapping;
        this.dataLocation = dataLocation;
        this.deleteControl = deleteControl;
    }

}
