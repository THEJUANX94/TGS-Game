package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    private GamePanel gp;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveCofig() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Config.txt"));

            if (gp.fullScreenOn) {
                bw.write("On");
            }
            if (!gp.fullScreenOn) {
                bw.write("Off");
            }
            bw.newLine();

            bw.write(String.valueOf(gp.music.volumeScale));
            bw.newLine();

            bw.write(String.valueOf(gp.se.volumeScale));
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Config.txt"));

            String temp = br.readLine();

            if (temp.equals("On")) {
                gp.fullScreenOn = true;
            }

            if (temp.equals("Off")) {
                gp.fullScreenOn = false;
            }

            temp = br.readLine();
            gp.music.volumeScale = Integer.parseInt(temp);

            temp = br.readLine();
            gp.se.volumeScale = Integer.parseInt(temp);

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
