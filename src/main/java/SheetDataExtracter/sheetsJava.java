package SheetDataExtracter;

import additionalScenes.Alert;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import constants.Global;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class sheetsJava {
    private static Sheets sheetsService;
    private static String aplication_name="Google sheets";
    private static String SPREADSHEET_ID= Global.getGoogleSheet_ID();//the id is between d/ and /edit


    private static Credential authorize() throws GeneralSecurityException, IOException {
        InputStream in= sheetsJava.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets=GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),clientSecrets,scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        Credential credential =new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");


        return credential;
    }

    public static Sheets getSheetsService() throws IOException, GeneralSecurityException {
        Credential credential= authorize();

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),credential)
                .setApplicationName(aplication_name)
                .build();

    }
    /*public static List<List<Object>> getValuesTest() throws GeneralSecurityException, IOException {
        InputStream in= sheetsJava.class.getResourceAsStream("/credentials.json");
        GoogleClientSecrets clientSecrets=GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(),new InputStreamReader(in));

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),clientSecrets,scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .setAccessType("offline")
                .build();

        Credential credential =new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("user");

        List<List<Object>> values;
        sheetsService= new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance(),credential)
                .setApplicationName(aplication_name)
                .build();
        String range="sheet1!A1:D5";

        ValueRange response=sheetsService.spreadsheets().values()
                .get(Global.getGoogleSheet_ID(),range)
                .execute();
        System.out.println(SPREADSHEET_ID);
        values= response.getValues();

        return values;
    }*/

    public static List<List<Object>> getValues() throws IOException, GeneralSecurityException {
        List<List<Object>> values = null;
        sheetsService=getSheetsService();
        String range="sheet1!A1:D5";

        try {
            ValueRange response=sheetsService.spreadsheets().values()
                    .get(Global.getGoogleSheet_ID(),range)
                    .execute();
            System.out.println(SPREADSHEET_ID);
            values= response.getValues();
        }catch (Exception e){
            Alert.display("Error","The table was not found!");
        }

        return values;
    }
}
