package me.hasenzahn1.breakout.map;

import me.hasenzahn1.breakout.map.bricks.Brick;
import me.hasenzahn1.breakout.map.bricks.PowerupBrick;
import me.hasenzahn1.breakout.map.bricks.UnbreakableBrick;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MapLoader {

    public static ArrayList<String> getMaps(){
        ArrayList<String> maps = new ArrayList<>();
        URL folderURL = MapLoader.class.getClassLoader().getResource("maps");
        String path = folderURL.getFile().replace("%20"," ");
        File folder = new File(path);
        System.out.println(Arrays.stream(folder.listFiles()).map((f) -> f.getName()).collect(Collectors.toList()));
        File[] mapFiles = folder.listFiles((dir,name) -> name.endsWith(".blf"));
        for(int i = 0; i < mapFiles.length; i++){
            maps.add(mapFiles[i].getName());
        }
        return maps;
    }

    public static Map loadMap(String name){
        String path = "maps/" + name;
        System.out.println(path);
        InputStream inputStream = MapLoader.class.getClassLoader().getResourceAsStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        Map map = new Map(name);
        int y = 0;
        for(String zeile : bufferedReader.lines().toList()){
            String[] brickData = zeile.split(" ");
            for(int x = 0; x < brickData.length; x++) {
                Brick brick = MapLoader.createBrick(brickData[x]);
                map.setBrick(x,y,brick);
            }
            y++;
        }
        return map;
    }

    private static Brick createBrick(String brickData){
        int data = Integer.parseInt(brickData);

        //data
        int color = data >> 4;
        data = data - (color << 4);
        int unbreakable = data >> 3;
        data = data - (unbreakable << 3);
        int powerup = data >> 2;
        data = data - (powerup << 2);
        int health = data;

        //do stuff with data
        if(powerup == 1 && unbreakable == 1){
            return null;
        } else if (powerup == 1) {
            return new PowerupBrick(health,color);
        } else if (unbreakable == 1){
            return new UnbreakableBrick(0,color);
        } else {
            return new PowerupBrick(health, color); // BRICK
        }


    }


}
