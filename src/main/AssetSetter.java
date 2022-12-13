package main;

import entity.NPC_OldMan;
import entity.NPC_OldMan2;
import entity.NPC_OldMan3;
import entity.NPC_OldMan4;
import entity.NPC_OldMan5;
import entity.NPC_OldMan6;

public class AssetSetter {

    public GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

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