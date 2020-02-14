package mainInput;

import connector.api.ElasticCaller;
import connector.apiData.DataClass;
import connector.apiData.Mapping;
import connector.apiData.DataLocationClass;
import connector.apiData.SearchQuery;
import connector.record.Watcher;


public class Main {

    public static void main( String[] args ) {

        Watcher watcherGerrit = new Watcher();
        DataClass inputDataGerit = new DataClass(  "gerrit_index_java", true,"testingEKgerrit" , Mapping.gerritMapFunction(), DataLocationClass.geritDataLocation, true  );
        ElasticCaller mainGerritInput = new ElasticCaller( inputDataGerit.index, inputDataGerit.checkIndex, watcherGerrit );

        mainGerritInput.elasticMapping( inputDataGerit.mapping );
        mainGerritInput.elasticSend( inputDataGerit.dataLocation, inputDataGerit.docName );
        mainGerritInput.elasticRequestData(  "testingEKgerrit_4"  );
        mainGerritInput.elasticQuery( SearchQuery.someGerritQuery() );

        mainGerritInput.elasticDeleteData( inputDataGerit.deleteControl, "testingEKgerrit_4" );

        watcherGerrit.report();



        Watcher watcherRandom = new Watcher();
        DataClass inputRandomData = new DataClass(  "random_index_java", true,"testingEKrandom" , Mapping.randomMapFunction(), DataLocationClass.randomDataLocation, true  );
        ElasticCaller mainRandomInput = new ElasticCaller( inputRandomData.index, inputRandomData.checkIndex, watcherRandom );

        mainRandomInput.elasticMapping( inputRandomData.mapping );
        mainRandomInput.elasticSend( inputRandomData.dataLocation, inputRandomData.docName );
        mainRandomInput.elasticRequestData(  "testingEKrandom_4"  );
        mainRandomInput.elasticQuery( SearchQuery.someRandomQuery() );

        mainRandomInput.elasticDeleteData( inputRandomData.deleteControl, inputRandomData.docName );

        watcherRandom.report();

    }

}