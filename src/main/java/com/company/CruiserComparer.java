package com.company;

import java.util.Comparator;

public class CruiserComparer implements Comparator<Vehicle> {
    @Override
    public int compare(Vehicle x, Vehicle y) {
        if (x instanceof WarCruiser && y instanceof Cruiser)
        {
            return -1;
        }
        if (x instanceof Cruiser && y instanceof WarCruiser)
        {
            return 1;
        }
        if (x instanceof Cruiser && y instanceof Cruiser) {
            return ComparerCruiser((Cruiser)x, (Cruiser)y);
        }
        if (x instanceof WarCruiser && y instanceof WarCruiser) {
            return ComparerWarCruiser((WarCruiser) x, (WarCruiser) y);
        }
        return 0;
    }

    private int ComparerCruiser(Cruiser x, Cruiser y) {
        if (x.MaxSpeed != y.MaxSpeed) {
            return Integer.compare(x.MaxSpeed, y.MaxSpeed);
        }
        if (x.Weight != y.Weight) {
            return Integer.compare((int)x.Weight, (int)y.Weight);
        }
        if (x.MainColor != y.MainColor) {
            return Integer.compare(x.MainColor.getRGB(), y.MainColor.getRGB());
        }
        return 0;
    }

    private int ComparerWarCruiser(WarCruiser x, WarCruiser y) {
        int result = ComparerCruiser(x, y);
        if (result != 0) {
            return result;
        }
        if (x.getDopColor() != y.getDopColor()) {
            return Integer.compare(x.getDopColor().getRGB(), y.getDopColor().getRGB());
        }
        if (x.isHelicopterStation() != y.isHelicopterStation()) {
            if (x.isHelicopterStation()) return 1;
            else return -1;
        }
        if (x.isArtillery() != y.isArtillery()) {
            if (x.isArtillery()) return 1;
            else return -1;
        }
        if (x.isLocator() != y.isLocator()) {
            if (x.isLocator()) return 1;
            else return -1;
        }
        if (x.getWeaponID() != y.getWeaponID()) {
            return Integer.compare(x.getWeaponID(), y.getWeaponID());
        }
        if (x.getWeaponCount() != y.getWeaponCount()) {
            return Integer.compare(x.getWeaponCount(), y.getWeaponCount());
        }
        return 0;
    }
}
