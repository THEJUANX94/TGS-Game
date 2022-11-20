package main;

import entity.Entity;

public class CollicionChecker {

    private GamePanel gp;

    public CollicionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWoldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWoldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
            break;

            case "down":
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
            break;
            
            case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
            break;
            case "right":
            entityRightCol = (entityRightWoldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
            break;
        }  
    }

    public int checkObject(Entity entity, boolean player){
        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){

                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                //get the object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entity.direction){
                    case"up":
                    entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("up collision");
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true; 
                            }
                            if(player == true){
                                index = i;
                            }
                        }   
                        break;
                    case "down":
                    entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("down collision");
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true; 
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                    entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("left collision");
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true; 
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        if(entity.solidArea.intersects(gp.obj[i].solidArea)){
                            System.out.println("right collision");
                            if(gp.obj[i].collision == true){
                                entity.collisionOn = true; 
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                    entity.solidArea.x += entity.speed;
                        break;
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }
    
}
