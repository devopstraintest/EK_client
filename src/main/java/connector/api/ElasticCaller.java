package connector.api;


import connector.engine.ElasticSearchConnector;
import org.springframework.util.StopWatch;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ElasticCaller {

    ElasticSearchConnector mainObject;
    StopWatch timer;

    public ElasticCaller( String index, Boolean check, StopWatch timer) {

        mainObject = new ElasticSearchConnector( index, check );
        this.timer = timer;

    }


    public void elasticMapping( String Mapping ) {

        this.timer.start( "Method: createMapping" );
        mainObject.createMapping( Mapping );
        this.timer.stop();

    }


    public void elasticSend( String Data, String docName ) {

        this.timer.start( "Method: callElastic" );
        try ( BufferedReader br = new BufferedReader( new FileReader( Data ) ) ) {
            String line;
            int counter = 0;
            String localNameGerrit = "";
            System.out.println( "Sending data to ELASTICSEARCH..." );
            while ( ( line = br.readLine() ) != null ) {
                if ( docName.length() != 0 ) {
                    localNameGerrit = docName + "_"  + counter;
                    counter++;
                }
                else localNameGerrit = docName;
                mainObject.callElastic( line, localNameGerrit );
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        this.timer.stop();

    }


    public void elasticRequestData( String collect ) {

        this.timer.start( "Method: elasticRequestData" );
        mainObject.requestDataElastic( collect );
        this.timer.stop();

    }

    public void elasticQuery( String query ) {

        this.timer.start("Method: queryElastic" );
        mainObject.queryElastic( query );
        this.timer.stop();

    }

    public void elasticDeleteData( boolean monitor, String name ){

        this.timer.start( "Method: deleteData" );
        mainObject.deleteData( monitor, name );
        this.timer.stop();

    }
}
