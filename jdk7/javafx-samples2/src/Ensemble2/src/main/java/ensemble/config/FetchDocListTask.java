package ensemble.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import javafx.concurrent.Task;

/**
 * Description:  Background task to fetch the all classes documentation page from a URL
 * Description:  从URL获取所有类文档页面的后台任务
 * Author: LuDaShi
 * Date: 2021-01-05-16:55
 * UpdateDate: 2021-01-05-16:55
 * FileName: FetchDocListTask
 * Version: 0.0.0.1
 * Since: 0.0.0.1
 */

public class FetchDocListTask extends Task<String> {

    private final String docsDirUrl;

    public FetchDocListTask(String docsDirUrl) {
        this.docsDirUrl = docsDirUrl;
    }

    @Override
    protected String call() throws Exception {
        System.out.println("---- FetchDocListTask  docsUrl = " + docsDirUrl);
        StringBuilder builder = new StringBuilder();
        try {
            URI uri = new URI(docsDirUrl + "allclasses-frame.html");
            URL url = uri.toURL();
            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(5000); //set timeout to 5 secs
            InputStream in = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
            reader.close();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}