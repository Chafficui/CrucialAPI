package io.github.chafficui.crucialAPI.utils.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.chafficui.crucialAPI.exceptions.CrucialException;
import io.github.chafficui.crucialAPI.utils.Server;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Updater {

    private static final String USER_AGENT = "Updater by Chaffic";
    // Direct download link
    private String downloadLink;
    // Provided plugin
    private final Plugin plugin;
    // The folder where update will be downloaded
    private final File updateFolder;
    // The plugin file
    private final File file;
    // ID of a project
    private final int id;
    // return a page
    private int page = 1;
    // Set the update type
    private final UpdateType updateType;
    // Get the outcome result
    // If next page is empty set it to true, and get info from previous page.
    private boolean emptyPage;
    // If true updater is going to log progress to the console.
    private final boolean logger;

    private static final String DOWNLOAD = "/download";
    private static final String VERSIONS = "/versions";
    private static final String PAGE = "?page=";
    private static final String API_RESOURCE = "https://api.spiget.org/v2/resources/";

    public Updater(Plugin plugin, int id, File file, UpdateType updateType, boolean logger)
    {
        this.plugin = plugin;
        this.updateFolder = plugin.getServer().getUpdateFolderFile();
        this.id = id;
        this.file = file;
        this.updateType = updateType;
        this.logger = logger;

        downloadLink = API_RESOURCE + id;

        // Updater thread
        Thread thread = new Thread(new UpdaterRunnable());
        thread.start();
    }

    public enum UpdateType
    {
        // Checks only the version
        VERSION_CHECK,
        // Downloads without checking the version
        DOWNLOAD,
        // If updater finds new version automatically it downloads it.
        CHECK_DOWNLOAD

    }

    /**
     * Check if id of resource is valid
     *
     * @param link link of the resource
     * @return true if id of resource is valid
     */
    private boolean checkResource(String link)
    {
        try
        {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", USER_AGENT);

            int code = connection.getResponseCode();

            if(code != 200)
            {
                connection.disconnect();
                return false;
            }
            connection.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Checks if there is any update available.
     */
    private void checkUpdate()
    {
        try
        {
            String page = Integer.toString(this.page);

            URL url = new URL(API_RESOURCE+id+VERSIONS+PAGE+page);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", USER_AGENT);

            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            JsonElement element = new JsonParser().parse(reader);
            JsonArray jsonArray = element.getAsJsonArray();

            if(jsonArray.size() == 10 && !emptyPage)
            {
                connection.disconnect();
                this.page++;
                checkUpdate();
            }
            else if(jsonArray.size() == 0)
            {
                emptyPage = true;
                this.page--;
                checkUpdate();
            }
            else if(jsonArray.size() < 10)
            {
                element = jsonArray.get(jsonArray.size()-1);

                JsonObject object = element.getAsJsonObject();
                element = object.get("name");
                // Version returned from spigot
                String version = element.toString().replaceAll("\"", "").replace("v", "");
                if(logger)
                    Server.log("Checking for update...");
                if(shouldUpdate(version, plugin.getDescription().getVersion()) && updateType == UpdateType.VERSION_CHECK)
                {
                    if(logger)
                        Server.log("Update found!");
                }
                else if(updateType == UpdateType.DOWNLOAD)
                {
                    if(logger)
                        Server.log("Downloading update...");
                    download();
                }
                else if(updateType == UpdateType.CHECK_DOWNLOAD)
                {
                    if(shouldUpdate(version, plugin.getDescription().getVersion()))
                    {
                        if(logger)
                            Server.log("Update found, downloading now...");
                        download();
                    }
                    else
                    {
                        if(logger)
                            Server.log("No update found.");
                    }
                }
                else
                {
                    if(logger)
                        Server.log("No update found.");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks if plugin should be updated
     * @param newVersion remote version
     * @param oldVersion current version
     */
    private boolean shouldUpdate(String newVersion, String oldVersion)
    {
        return !newVersion.equalsIgnoreCase(oldVersion);
    }

    /**
     * Downloads the file
     */
    private void download() throws CrucialException {
        BufferedInputStream in = null;
        FileOutputStream fout = null;

        try
        {
            URL url = new URL(downloadLink);
            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(new File(updateFolder, file.getName()));
            final byte[] data = new byte[4096];
            int count;
            while ((count = in.read(data, 0, 4096)) != -1) {
                fout.write(data, 0, count);
            }
            if(logger)
                Server.log("Update dowloaded. Reload to use new Version.");
        }
        catch (Exception e)
        {
            if(logger)
                throw new CrucialException(8);
        }
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                Server.error(e.getLocalizedMessage());
            }
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (final IOException e) {
                Server.error(e.getLocalizedMessage());
            }
        }
    }

    public class UpdaterRunnable implements Runnable
    {

        public void run() {
            if(checkResource(downloadLink))
            {
                downloadLink = downloadLink + DOWNLOAD;
                checkUpdate();
            }
        }
    }
}
