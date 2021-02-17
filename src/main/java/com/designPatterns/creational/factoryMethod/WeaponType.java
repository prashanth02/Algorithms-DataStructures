
package com.designPatterns.creational.factoryMethod;

/**
 * WeaponType enumeration.
 */
public enum WeaponType {

  SHORT_SWORD("short sword"), SPEAR("spear"), AXE("axe"), UNDEFINED("");

  private final String title;

  WeaponType(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return title;
  }
}