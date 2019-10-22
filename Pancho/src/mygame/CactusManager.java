/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public class CactusManager extends AbstractAppState {

  private SimpleApplication app;
  private AppStateManager   stateManager;
  private AssetManager      assetManager;
  public  Node              cactusNode;
  private Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    cactusNode      = new Node();
    player            = stateManager.getState(PlayerManager.class).player;
    this.app.getRootNode().attachChild(cactusNode);
    cactusNode.setLocalTranslation(1,4.9f,-.1f);
    initCactus();
    }
  
  private void initCactus() {
    Node cactus        = (Node) assetManager.loadModel("Models/Cactus.j3o");
    Material cactusMat = stateManager.getApplication().getAssetManager().loadMaterial("Materials/Cactus.j3m");
    cactus.setMaterial(cactusMat);
    cactusNode.attachChild(cactus);
    cactus.rotate(0,40,0);
    resetCactus();
    }
  
  public void resetCactus(){
    cactusNode.getChild(0).setLocalTranslation(cactusNode.getChild(0).getLocalTranslation().addLocal(4.9f,0,0));  
    }
  
  @Override
  public void update(float tpf) {
    
    float difficulty = player.cactusCount;
    
    if (difficulty < 3) {
      difficulty = 3;
      }
    
    if (difficulty > 8) {
      difficulty = 8;
      }
    
    if (!player.isDead)
    cactusNode.getChild(0).setLocalTranslation(cactusNode.getChild(0).getLocalTranslation().addLocal(-difficulty*tpf,0,0));   
    
    if (cactusNode.getChild(0).getLocalTranslation().x < -7) {
      cactusNode.getChild(0).setLocalTranslation(5,0,0);
      player.cactusCount++;
      }
    
    }
  
  }
