package iohandler;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by admin on 12/8/2016.
 */

public class MyFileHandler {

    public static String readFile(Context mContext, String file_name) throws IOException{
        StringBuilder content = null;
        BufferedReader reader = null;
        try {
// Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(file_name);
            reader = new BufferedReader(new InputStreamReader(in));
            content = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } finally {
            if (reader != null)
                reader.close();
        }
        return content.toString();
    }

    public static void saveFile(Context context, String file_name, String data){
        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(file_name, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(data);
        } catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}
