package main.firefighters;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import main.api.City;
import main.api.CityNode;
import main.api.FireDispatch;
import main.api.Firefighter;
import main.api.exceptions.NoFireFoundException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FireDispatchImpl implements FireDispatch {

  private City city;
  private List<Firefighter> fireFighters;

  protected final Logger myLogger = Logger.getLogger("FireDispatchImpl");

  public FireDispatchImpl(City city) {
    this.city = city;
  }

  @Override
  public void setFirefighters(int numFirefighters) {
    this.fireFighters = new ArrayList<>(numFirefighters);
    for(int i = 0; i < numFirefighters; i++){
      this.fireFighters.add(new FirefighterImpl(this.city.getFireStation()));
    }
  }

  @Override
  public List<Firefighter> getFirefighters() {
    return fireFighters;
  }

  @Override
  public void dispatchFirefighers(CityNode... burningBuildings) {

    for(CityNode node : burningBuildings) {
      Firefighter closestFighter = null;
      int max = Integer.MAX_VALUE;
      if(fireFighters.size() > 0){
        for(Firefighter fireFighter: fireFighters) {

          int distance = calculateFirefighterDistance(node, fireFighter.getLocation());
          if(distance < max ) {
            closestFighter = fireFighter;
            max = distance;
          }
        }
        try {
          this.city.getBuilding(node).extinguishFire();
          closestFighter.setLocation(node, max);

        } catch (NoFireFoundException e){
          myLogger.warning("Unable to find fire in building");

        }
      }
    }
  }

  /**
   * Returns the distance between the city node and the fire fighter
   * The city node
   * @param cityNode
   * The fire fighter
   * @param fireFighter
   *
   * @return the taxicab distance between points
   */
  public int calculateFirefighterDistance(
          CityNode cityNode,
          CityNode fireFighter) {
    return (Math.abs(cityNode.getX() - fireFighter.getX())) + (Math.abs(cityNode.getY() - fireFighter.getY()));
  }
}
