package connector.engine;


public class ElasticSearchParams {

    enum ParamsUrls {

        URL( "http://localhost:9200" );

        private String loc;

        ParamsUrls( String loc ) {
            this.loc = loc;
        }

        public String getParamsUrls() {
            return this.loc;
        }

    }

    enum ParamsMethods {

        XGET( "curl -XGET " ),
        XPUT( "curl -XPUT " ),
        XPOST( "curl -XPOST " ),
        XDELETE( "curl -XDELETE " );

        private String method;

        ParamsMethods( String method ) {
            this.method = method;
        }

        public String getParamsMethods() {
            return this.method;
        }

    }

    enum Contents {

        JSON( " -H 'Content-Type:application/json' -d " ),
        RESPONSE( " --head -so /dev/null -w '%{response_code}'" ),
        UPDATE( "/_update_by_query" ),
        MAPPING( "/_mapping" ),
        SEARCH( "/_search" ),
        DOC( "/_doc/" );

        private String content;

        Contents( String content ) {
            this.content = content;
        }

        public String getContents() {
            return this.content;
        }

    }

}
