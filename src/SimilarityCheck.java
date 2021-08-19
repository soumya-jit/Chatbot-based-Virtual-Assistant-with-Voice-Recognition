

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paralleldots.paralleldots.App;
import java.util.HashMap;
import java.util.Map;


public class SimilarityCheck {
    
    public static Map<String, Object> jsonToMap(String str)   //convert the json objeect to Map 
    {
        Map<String, Object> map=new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
        return map;
    }
    
    public static Double probability(String s1, String s2)
    {
        Double d=0.00;
        try{
            App pd = new App("HoCuSYlXHAWDxjcoF7kEuWJbApwU08nCurdyJLn959o");

            String similarity = pd.similarity(s1, s2);
            Map<String, Object> respMap=jsonToMap(similarity.toString());
            d=Double.parseDouble(respMap.get("similarity_score").toString());
        }
        catch(Exception e)
        {
            try{
                App pd = new App("ZaHeSjyUl8FFZxwBJv4GLOlrQro4lzjTz39X2oHH6Pc");

                String similarity = pd.similarity(s1, s2);
                Map<String, Object> respMap=jsonToMap(similarity.toString());
                d=Double.parseDouble(respMap.get("similarity_score").toString());
            }
            catch(Exception e2)
            {
                try{
                    App pd = new App("Q9bY4awzbCwjcNTaiij1J0p9sHZQ15DjjDfd4VajnE4");

                    String similarity = pd.similarity(s1, s2);
                    Map<String, Object> respMap=jsonToMap(similarity.toString());
                    d=Double.parseDouble(respMap.get("similarity_score").toString());
                }
                catch(Exception e3)
                {
                    try{
                        App pd = new App("HoCuSYlXHAWDxjcoF7kEuWJbApwU08nCurdyJLn959o");

                        String similarity = pd.similarity(s1, s2);
                        Map<String, Object> respMap=jsonToMap(similarity.toString());
                        d=Double.parseDouble(respMap.get("similarity_score").toString());
                    }
                    catch(Exception e4)
                    {
                        return d;
                    }
                    //7raV0ZmibSkT4ymBwR8YXK03VZg4BRpx7asf8e7LNF0
                }
            }
        }
        return d;
    }
    
    public static void main(String[] args) {
        System.out.println("probability is: "+probability("item number", "product number"));
    }
    
}
