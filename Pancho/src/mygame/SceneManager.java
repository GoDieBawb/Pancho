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
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Bob
 */
public class SceneManager extends AbstractAppState {

  private SimpleApplication     app;
  private AppStateManager       stateManager;
  private AssetManager          assetManager;
  private Node                  floorNode;
  private Node                  sceneNode;
  private Player                player;
  private float                 boxLength;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app) {
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = this.app.getStateManager();
    this.assetManager = this.app.getAssetManager();
    floorNode         = new Node();
    sceneNode         = new Node();
    player            = stateManager.getState(PlayerManager.class).player;
    this.app.getRootNode().attachChild(sceneNode);
    initGround();
    initSky();
    }
  
  private void initGround() {
    Box box         = new Box(15,5,1);
    Geometry floor1 = new Geometry("Floor One", box);
    Geometry floor2 = new Geometry("Floor Two", box);
    Material mat    = assetManager.loadMaterial("Materials/Sand.j3m");
    floorNode       = new Node();
    boxLength       = box.xExtent;
    floorNode.attachChild(floor1);
    floorNode.attachChild(floor2);
    floor1.setMaterial(mat);
    floor2.setMaterial(mat);
    floor1.getMesh().scaleTextureCoordinates(new Vector2f(3,2f));
    floor2.getMesh().scaleTextureCoordinates(new Vector2f(3,2f));
    sceneNode.attachChild(floorNode);
    floor2.setLocalTranslation(boxLength*2,0,0);
    }

  public void initSky(){
    Box box      = new Box(5,2.5f,1);
    Geometry sky = new Geometry("Floor One", box);
    sky.setMaterial(assetManager.loadMaterial("Materials/Sky.j3m"));
    app.getRootNode().attachChild(sky);
    sky.setLocalTranslation(0,5,-2);
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
      
    if (!player.isDead) {  
      floorNode.getChild("Floor One").setLocalTranslation(floorNode.getChild("Floor One").getLocalTranslation().addLocal(-difficulty*tpf,0,0));
      floorNode.getChild("Floor Two").setLocalTranslation(floorNode.getChild("Floor Two").getLocalTranslation().addLocal(-difficulty*tpf,0,0));  
      }
    
    if (floorNode.getChild("Floor One").getLocalTranslation().x < (-boxLength*2)) {
      floorNode.getChild("Floor One").setLocalTranslation(boxLength*2,0,0);
      }

    if (floorNode.getChild("Floor Two").getLocalTranslation().x < (-boxLength*2)) {
      floorNode.getChild("Floor Two").setLocalTranslation(boxLength*2,0,0);
      }
    
    }
  
  }
