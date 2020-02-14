package connector.apiData;


public class Mapping {

    public static String gerritMapFunction() {

    return         " {                                          "
                +  " \"properties\": {                          "
                +  "     \"_number\": {                         "
                +  "         \"type\": \"long\"                 "
                +  "  },                                        "
                +  "  \"branch\": {                             "
                +  "    \"type\": \"text\",                     "
                +  "        \"fields\": {                       "
                +  "            \"keyword\": {                  "
                +  "                \"type\": \"keyword\",      "
                +  "                \"ignore_above\": 256       "
                +  "            }                               "
                +  "        }                                   "
                +  "    },                                      "
                +  " \"change_id\": {                           "
                +  "    \"type\": \"text\",                     "
                +  "        \"fields\": {                       "
                +  "            \"keyword\": {                  "
                +  "                \"type\": \"keyword\",      "
                +  "                \"ignore_above\": 256       "
                +  "            }                               "
                +  "        }                                   "
                +  "    },                                      "
                +  " \"deletions\": {                           "
                +  "    \"type\": \"integer\"                   "
                +  "            }                               "
                +  " \"name\": {                                "
                +  "    \"type\": \"integer\"                   "
                +  "            }                               "
                +  "        }                                   "
                +  "    }                                       "
                ;

    }

    public static String randomMapFunction() {

        return     " {                                          "
                +  " \"properties\": {                          "
                +  " \"age\": {                                 "
                +  " \"type\": \"integer\"                      "
                +  "           },                               "
                +  " \"email\": {                               "
                +  " \"type\": \"keyword\"                      "
                +  "            },                              "
                +  " \"name\": {                                "
                +  " \"type\": \"keyword\"                      "
                +  "            }                               "
                +  "        }                                   "
                +  "    }                                       "
                ;
    }

}
