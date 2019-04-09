package com.example.adstod;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/*
The following example makes use of JSONObject and JSONArray where JSONObject is a java.util.
Map and JSONArray is a java.util.List, so you can access them with standard operations of Map or List.
https://www.tutorialspoint.com/how-to-parse-json-in-java
*/
public class JsonDecode {
    //theQnA[0] = is the Question, theQnA[1-n] are the Answer Options
    private String[] theQnA;
    JSONParser parser = new JSONParser();

        public String[] QuestionParser(JSONObject QnA){
            int i = 0;
            String jstring = QnA.toString();
            Object obj = QnA;

            JSONArray array = (JSONArray)obj;
            JSONObject obj2 = (JSONObject)array.get(1);
            while(theQnA[i] != "") {
                theQnA[0] = obj2.toString();
                i++;
            }


           // while(QnA.hasNext())
           // theQnA = QnA.Answers[0];


            return theQnA;
        }

  /*      public static void main(String[] args) {
            JSONParser parser = new JSONParser();
            String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
            /*
[0,
    {"1":
        {"2":
            {"3":
                {"4":
                    [5,
                        {"6":7
                        }
                     ]
                }
            }
        }
    }
]

             {
   "firstName": "John", "lastName": "Smith", "age": 25,
   "phoneNumber": [
       { "type": "home", "number": "212 555-1234" },
       { "type": "fax", "number": "646 555-4567" }
    ]
 }

{
    "Question": "Hvað ertu gamall?", "Answers": [
        { "Answeroption1": "Mjög sammála", "Answeroption2": "Frekar sammála",
          "Answeroption3": "Hvorki né", "Answeroption4": "Frekar ósammála",
          "Answeroption5": "Mjög ósammála" }
        ]
}
            */

   /*         try{
                Object obj = parser.parse(s);
                JSONArray array = (JSONArray)obj;

                System.out.println("The 2nd element of array");
                System.out.println(array.get(1));
                System.out.println();

                JSONObject obj2 = (JSONObject)array.get(1);
                System.out.println("Field \"1\"");
                System.out.println(obj2.get("1"));

                s = "{}";
                obj = parser.parse(s);
                System.out.println(obj);

                s = "[5,]";
                obj = parser.parse(s);
                System.out.println(obj);

                s = "[5,,2]";
                obj = parser.parse(s);
                System.out.println(obj);
            } catch(ParseException pe) {

                System.out.println("position: " + pe.getPosition());
                System.out.println(pe);
            }
        }
        */
    }
