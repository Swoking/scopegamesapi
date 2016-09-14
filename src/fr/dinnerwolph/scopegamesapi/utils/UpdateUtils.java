package fr.dinnerwolph.scopegamesapi.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.spigotmc.RestartCommand;

public class UpdateUtils
extends Thread {
    private URL url;
    private Plugin plugin;
    private Runtime runtime;

    public UpdateUtils(Plugin plugin) throws IOException {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null.");
        }
        this.url = new URL("http://dinnerwolph.scopegames.fr/APIUpdate/" + plugin.getDescription().getFullName());
        this.plugin = plugin;
        this.run();
    }

    @Override
    public void run() {
        HttpURLConnection connection = null;
        try {
            String version;
            connection = (HttpURLConnection)this.url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String e11 = "";
            String line = null;
            while ((line = reader.readLine()) != null) {
                e11 = e11 + line;
            }
            reader.close();
            JSONObject json = null;
            try {
                json = (JSONObject)new JSONParser().parse(e11);
            }
            catch (ParseException exception) {
                // empty catch block
            }
            String currentVersion = null;
            if (json != null && json.containsKey((Object)"version") && (version = (String)json.get((Object)"version")) != null && !version.isEmpty()) {
                currentVersion = version;
            }
            if (currentVersion == null) {
                return;
            }
            if (!currentVersion.equals(this.plugin.getDescription().getVersion())) {
                Bukkit.broadcastMessage((String)("Une mise \u00e0 jour du plugins " + this.plugin.getDescription().getName() + " est en cours, le serveur vas redemarrer"));
                Thread.sleep(300);
                File file = new File("./plugins");
                this.runtime = Runtime.getRuntime();
                this.runtime.exec("rm -f " + this.plugin.getDescription().getName() + ".jar", null, file);
                this.runtime.exec("wget http://dinnerwolph.scopegames.fr/APIUpdate/" + this.plugin.getDescription().getName() + ".jar", null, file);
                Thread.sleep(5000);
                RestartCommand.restart();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}