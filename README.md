# CrucialAPI
[![](https://jitpack.io/v/Chafficui/CrucialAPI.svg)](https://jitpack.io/#Chafficui/CrucialAPI)

# CrucialAPI

CrucialAPI is an open-source, easy-to-use Spigot API that aims to simplify plugin development.

> Note: If you have any suggestions, questions, or feedback for this wiki, please use [Discussions](https://github.com/Chafficui/CrucialAPI/discussions).

## Setup

> **Attention!** Do not forget to add CrucialAPI as a dependency in your "plugin.yml" file!

### Maven

```XML
<dependency>
  <groupId>io.github.chafficui</groupId>
  <artifactId>CrucialAPI</artifactId>
  <version>1.2</version>
</dependency>
```

### Gradle

```Gradle
implementation 'io.github.chafficui:CrucialAPI:1.2'
```

## Examples

```Java
public class Main extends JavaPlugin {
    private final String CrucialAPIVersion = "1.2";

    /** Auto-download CrucialAPI */
    @Override
    public void onLoad(){
        if(getServer().getPluginManager().getPlugin("CrucialAPI") == null){
            try {
                URL website = new URL("https://github.com/Chafficui/CrucialAPI/releases/download/v" + CrucialAPIVersion + "/CrucialAPI-v" + CrucialAPIVersion + ".jar");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("plugins/CrucialAPI.jar");
                fos.getChannel().transferFrom(rbc, 0L, Long.MAX_VALUE);
                Bukkit.getPluginManager().loadPlugin(new File("plugins/CrucialAPI.jar"));
            } catch (IOException | InvalidDescriptionException | org.bukkit.plugin.InvalidPluginException e) {
                e.printStackTrace();
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
    }

    /** Auto-update CrucialAPI */
    @Override
    public void onEnable(){
        if(Server.checkVersion(new String[]{"1.16", "1.15"})){
            Crucial.getVersion(CrucialAPIVersion, this);
        } else {
            //CrucialAPI only supports 1.16 and 1.15
            Bukkit.getPluginManager().disablePlugin(this);
        }
        new CrucialItem("Super shovel", Material.DIAMOND_SHOVEL, "item").setCrafting(new String[]{"Air", "AIR", "AIR", "DIAMOND", "DIAMOND", "DIAMOND", "AIR", "AIR", "AIR"});
    }
}
```

## Download

You can get the latest stable release build here: [Stable Build](https://github.com/Chafficui/CrucialAPI/releases/latest)  
If you want the most up-to-date builds, you can find them here: [Releases](https://github.com/Chafficui/CrucialAPI/releases)

## Documentation

[Documentation](https://www.javadoc.io/doc/io.github.chafficui/CrucialAPI/latest/)  
Currently, some parts of CrucialAPI lack proper documentation. However, we are working hard to improve it!

## Getting Help

If you need help or want to chat with the CAPI team or other developers, you can join the [Official ChafficPlugins Discord Server](https://discord.gg/ARxYx4Z).  
Alternatively, you can use the [Discussions](https://github.com/Chafficui/CrucialAPI/discussions) section.

## Dependencies

This project requires Spigot 1.15 or higher.

Here are some additional information about LTS versions:

| Version | Supported          |
| ------- | ------------------ |
| 2.2.x   | :white_check_mark: |
| 2.1.x   | :x:                |
| 2.0.x   | :x:                |
| 1.x.x   | :x:                |
