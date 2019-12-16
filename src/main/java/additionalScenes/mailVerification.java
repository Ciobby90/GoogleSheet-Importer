package additionalScenes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class mailVerification {

    private final String activationKey="670703637ec1b0dadbd6e213ebfbcb84";

    private String mail;

    public mailVerification(String mail) {
        this.mail = mail;
    }

    public boolean checkMail() throws Exception{
        String url="https://apilayer.net/api/check?access_key="+activationKey+"&email="+mail+"&smtp=1&format=1";

        URL urlobj=new URL(url);

        HttpURLConnection con= (HttpURLConnection) urlobj.openConnection();  //here starts the connection

        con.setRequestProperty("User-Agent","Chrome");

        BufferedReader in= new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response=new StringBuffer();

        while ((inputLine=in.readLine())!=null){
            response.append(inputLine);
        }
        in.close();
        //System.out.println(response.toString());
        con.disconnect();                                                   //ends the connection

        JSONObject jsonObject=new JSONObject(response.toString());

        boolean smtp=jsonObject.getBoolean("smtp_check");
        double score= jsonObject.getDouble("score");

        if(smtp && score<=0.8){
            return true;
        }
        else return false;

    }


    public static void main(String[] args){

        mailVerification mail = new mailVerification("gabiciobanu5@gmail.com");
        try {
            System.out.println(mail.checkMail());
        } catch (Exception e){
            System.out.println("error");
        }

    }
}
