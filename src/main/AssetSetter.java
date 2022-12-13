package main;

import entity.NPC_OldMan;
import entity.NPC_OldMan2;
import entity.NPC_OldMan3;
import entity.NPC_OldMan4;
import entity.NPC_OldMan5;
import entity.NPC_OldMan6;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {

    public GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 21;
        gp.npc[0].worldY = gp.tileSize * 21;

        gp.npc[1] = new NPC_OldMan2(gp);
        gp.npc[1].worldX = gp.tileSize * 23;
        gp.npc[1].worldY = gp.tileSize * 12;

        gp.npc[2] = new NPC_OldMan3(gp);
        gp.npc[2].worldX = gp.tileSize * 12;
        gp.npc[2].worldY = gp.tileSize * 23;

        gp.npc[3] = new NPC_OldMan4(gp);
        gp.npc[3].worldX = gp.tileSize * 12;
        gp.npc[3].worldY = gp.tileSize * 23;

        gp.npc[4] = new NPC_OldMan5(gp);
        gp.npc[4].worldX = gp.tileSize * 12;
        gp.npc[4].worldY = gp.tileSize * 12;

        gp.npc[5] = new NPC_OldMan6(gp);
        gp.npc[5].worldX = gp.tileSize * 12;
        gp.npc[5].worldY = gp.tileSize * 12;
    }
}