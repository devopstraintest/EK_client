package connector.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connector.apiData.DataLocationClass;
import org.json.JSONObject;
import java.io.*;


public class ElasticSearchConnector {

    private final String index;
    private final String url = ElasticSearchParams.ParamsUrls.URL.getParamsUrls();
    private String get = ElasticSearchParams.ParamsMethods.XGET.getParamsMethods();
    private String put = ElasticSearchParams.ParamsMethods.XPUT.getParamsMethods();
    private String post = ElasticSearchParams.ParamsMethods.XPOST.getParamsMethods();
    private String delete = ElasticSearchParams.ParamsMethods.XDELETE.getParamsMethods();
    private String jsoncontent = ElasticSearchParams.Contents.JSON.getContents();
    private String mapp = ElasticSearchParams.Contents.MAPPING.getContents();
    private String response = ElasticSearchParams.Contents.RESPONSE.getContents();
    private String doc = ElasticSearchParams.Contents.DOC.getContents();
    private String update = ElasticSearchParams.Contents.UPDATE.getContents();
    private String search = ElasticSearchParams.Contents.SEARCH.getContents();

    private String mapping;
    private String query;


    public ElasticSearchConnector( String index, Boolean check ) {

        System.out.println( "[CONSTRUCTOR]" );
        this.index = index.toLowerCase();
        this.query = this.url + "/" + this.index;
        this.put = put + this.query;
        this.get = get + this.query;
        this.post = post + this.query;

        if ( check ) {
            String cleanStatus = checkStatus().replaceAll( "\r", "" ).replaceAll( "\n", "" );
            if (!cleanStatus.equals( "200" )) {
                String rsp = process( this.put );
                System.out.println( "Index has been created! " + rsp );
            }
            else System.out.println( "Index exists!" );
        }
        System.out.println( "The variables have been set up.\n[CONSTRUCTOR__has_been_finished]***********\n" );

    }


    public String callElastic( String data, String name ) {

        data = prepareData( data );
        String url = ( name.length() != 0 ) ? this.put + doc + name + data : this.post + doc + data;
        return process( url );

    }


    public void requestDataElastic( String collect ) {

        String url = ( collect.length() == 0 ) ? this.get + search : this.get  + doc + collect;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String rs = process(url);
        String temp = new JSONObject( rs ) .toString(2);

        try {
            FileWriter myWriter = new FileWriter( DataLocationClass.resultData );
            myWriter.write( temp );
            myWriter.close();
            System.out.println( "Request data: Successful." );
        } catch ( IOException e ) {
            System.out.println( "Request data: An error occurred." );
            e.printStackTrace();
        }

    }


    public int queryElastic( String data ) {

        String urlForQuery;
        if ( data.length() == 0 ){
            requestDataElastic( data );
            return 0;
        }
        else {
            urlForQuery = this.post + update + prepareData( data );
        }
        String rsp = process( urlForQuery );
        System.out.println( "\nQuery data response: " + rsp );
        return 0;

    }


    public void createMapping( String mapping ) {

        System.out.println( "[MAPPING]" );
        this.mapping = prepareData( mapping );
        String ctx = prepareData( mapping );
        String mappingInString = checkStatus();

        JSONObject json = new JSONObject( mappingInString );

        try{
            if ( json.getJSONObject( this.index ).getJSONObject( "mappings" ).length() == 0 ) {
                this.mapping = ctx;
                String rsp = process( this.put + mapp + this.mapping );
                System.out.println( "Mapping message: " + rsp );
            }
            else System.out.println( "Mapping exists!" );
        }catch ( Exception e ){
            System.out.println( "Exception: " + e );
        }

        System.out.println( "[MAPPING__has_been_finished]***********\n" );

    }


    String checkStatus() {

        String ctx = ( this.mapping != null) ? mapp : response;
        String rsp = process( this.get + ctx );
        System.out.println( "Check status: " + rsp );
        return rsp;

    }


    String prepareData( String dataCorrection ) {

        String tmp = "'" + dataCorrection + "'";
        return this.jsoncontent + tmp;

    }


    public static String process( String query ) {

        String[] command = { "/bin/bash", "-c", query };
        ProcessBuilder process = new ProcessBuilder( command );
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
            StringBuilder builder = new StringBuilder();
            String line = null;
            while (( line = reader.readLine() ) != null) {
                builder.append( line );
                builder.append( System.getProperty( "line.separator" ) );
            }
            String result = builder.toString();
            return result;

        } catch ( IOException e ) {
            e.printStackTrace();
            return "error";
        }

    }


    public void deleteData( boolean monitor, String name ){

        System.out.println( "[DELETING]" );
        String temp = delete + this.query;
        String url = ( name.length() == 0 ) ? temp : temp + doc + name;
        if( monitor ) {
            process( url );
            System.out.println( "Deleting has finished it's work at address: " + url + "\n" );
        }
        else System.out.println( "Nothing has happened!\n" );
        System.out.println( "[DELETING__has_been_finished]***********" );

    }

}
