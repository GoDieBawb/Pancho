/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.app.Application;
import com.jme3.app.SimpleApplication;
import com.jme3.app.state.AbstractAppState;
import com.jme3.app.state.AppStateManager;
import com.jme3.collision.CollisionResults;
import com.jme3.scene.Node;

/**
 *
 * @author Bob
 */
public class PlayerManager extends AbstractAppState {
  
  private AppStateManager   stateManager;
  private SimpleApplication app;    
  public  Player            player;
  
  @Override
  public void initialize(AppStateManager stateManager, Application app){
    super.initialize(stateManager, app);
    this.app          = (SimpleApplication) app;
    this.stateManager = stateManager;
    player            = new Player(stateManager);
    this.app.getRootNode().attachChild(player);
    player.rotate(0,89,0);
    player.setLocalTranslation(-1,5,0);
    }
  
  @Override
  public void update(float tpf) {
    
    Node cactusNode = (Node) ((Node) stateManager.getState(CactusManager.class).cactusNode).getChild("Collider");
    
    CollisionResults results = new CollisionResults();
    
    cactusNode.collideWith(((Node) player.model.getChild("Collider")).getWorldBound(), results);
    
    if (results.size() > 1) {
     player.die(stateManager);
      }
      
    player.run();
    
    if (player.isJumping) {
      player.setLocalTranslation(player.getLocalTranslation().addLocal(0,4*tpf,0));
      }
    
    else if (player.getLocalTranslation().y > 5) {
      player.setLocalTranslation(player.getLocalTranslation().addLocal(0,-3*tpf,0));
      }
    
    else {
      player.doneJumping = true;
      }
    
    if (player.getLocalTranslation().y > 7f) {
      player.isJumping = false;
      }
      
    }
    
  }
