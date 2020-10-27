package main.firefighters;

import main.api.CityNode;
import main.api.Building;

import main.api.Firefighter;
import main.impls.BuildingImpl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FirefighterImpl implements Firefighter {

  private Building building;
  private int distanceTraveled;
  private boolean isDispatched;

  public FirefighterImpl(Building building) {
    this.building = building;
    this.distanceTraveled = 0;
    this.isDispatched = false;
  }

  @Override
  public CityNode getLocation() {
    return this.building.getLocation();
  }

  @Override
  public int distanceTraveled() {
    return this.distanceTraveled;
  }

  @Override
  public void setLocation(CityNode cityNode, int distance) {
    this.building = new BuildingImpl(cityNode);
    this.distanceTraveled+=distance;
  }


}
