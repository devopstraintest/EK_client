package connector.apiData;


public class SearchQuery {

    public static String someGerritQuery() {

        return     " {                                                  "
                +  " \"script\": {                                      "
                +  "    \"source\" : \"ctx._source.deletions=0;\",      "
                +  "    \"lang\" : \"painless\"                         "
                +  "  },                                                "
                +  " \"query\": {                                       "
                +  "       \"term\":  {                                 "
                +  "            \"test\": 1                             "
                +  "            }                                       "
                +  "        }                                           "
                +  "    }                                               "
                ;
    }


    public static String someRandomQuery() {

        return     " {                                                  "
                +  " \"script\": {                                      "
                +  "    \"source\" : \"ctx._source.age=30;\",           "
                +  "    \"lang\" : \"painless\"                         "
                +  "  },                                                "
                +  " \"query\": {                                       "
                +  "       \"term\":  {                                 "
                +  "            \"name\": \"Antonio\"                   "
                +  "            }                                       "
                +  "        }                                           "
                +  "    }                                               "
                ;
    }

}